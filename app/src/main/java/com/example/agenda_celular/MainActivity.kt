package com.example.agenda_celular

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.agenda_celular.databinding.ActivityMainBinding
import com.example.agenda_celular.viewModel.PersonaViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {
    private lateinit var sAuth : FirebaseAuth
    private val Google_SING_IN = 100
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PersonaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        binding.btnOpenWindow.setOnClickListener {
//            val intent = Intent(this, listPersona::class.java)
//            startActivity(intent)
//        }
        binding.btnOpenWindow.setOnClickListener{
            val intent = Intent(this, listPersona::class.java)
            startActivity(intent)
        }
//  Inicializando
        sAuth= FirebaseAuth.getInstance()
        // boton
        boton()
    }

    private fun boton() {
        binding.btGoogle.setOnClickListener{
            //Configurar google sing in
            val GoogleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleClient = GoogleSignIn.getClient(this, GoogleConf)
            val singIntent = googleClient.signInIntent
            startActivityForResult(singIntent, Google_SING_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Google_SING_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                if (account!= null){
                    Log.d("Tag","firebasegoogleid $account.id")
                    firebaseAuthWithGoogle(account.idToken!!)
                }else{
                    Toast.makeText(this, "Correo no existe", Toast  .LENGTH_LONG).show()
                }

            }catch (e:ApiException){
                Log.w("Tag", "Google sing in failed $e")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        sAuth.signInWithCredential(credential)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    Log.d("Tag", "singInWithCredential:sucess")
                    val user = sAuth.currentUser?.email.toString()
                    login(user)
                }else{
                    Log.w("tag","SingInWithCredential:failure", task.exception)
                    Toast.makeText(this, "no se pude loguear", Toast.LENGTH_LONG).show()
                }
            }


    }
}

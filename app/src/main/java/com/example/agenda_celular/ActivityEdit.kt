package com.example.agenda_celular

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.lifecycle.ViewModelProvider
import com.example.agenda_celular.databinding.ActivityEditBinding
import com.example.agenda_celular.viewModel.PersonaViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActivityEdit : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var viewModel: PersonaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var id = intent.getIntExtra("id", 0)
        var nombre = intent.getStringExtra("nombre").toString()
        var telefono = intent.getStringExtra("telefono").toString()
        var edad = intent.getStringExtra("edad").toString()

        binding.etName.text = Editable.Factory.getInstance().newEditable(nombre)
        binding.etphone.text = Editable.Factory.getInstance().newEditable(telefono)
        binding.etage.text = Editable.Factory.getInstance().newEditable(edad)

        viewModel = ViewModelProvider (this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(PersonaViewModel::class.java)

        binding.ivSave.setOnClickListener{
            GlobalScope.launch {
                Save(
                    PersonaModel(
                        id,
                        binding.etName.text.toString(),
                        binding.etphone.text.toString(),
                        binding.etage.text.toString().toInt()
                    )
                )
            }
        }
    }

    private fun Save(datos: PersonaModel) {
        viewModel.editarPersona(datos)
        this.finish()
    }
}
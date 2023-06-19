package com.example.agenda_celular

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.agenda_celular.databinding.ActivityAgregarBinding
import com.example.agenda_celular.viewModel.PersonaViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActivityAgregar : AppCompatActivity() {

    private lateinit var binding: ActivityAgregarBinding
    private lateinit var viewModel: PersonaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider (this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(PersonaViewModel::class.java)
        binding.ivSave.setOnClickListener{
            GlobalScope.launch {
                Save()
            }
        }

    }



    suspend fun Save() {
        var newPersona= PersonaModel(
            0,
            binding.etName.text.toString(), binding.etphone.text.toString(),
            binding.etage.text.toString().toInt()
        )
        viewModel.insertPersona(newPersona)

        this.finish()
    }
}
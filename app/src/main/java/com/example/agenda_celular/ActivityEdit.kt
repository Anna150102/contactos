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

        var dataPersonaSelect = listPersona.personaSelect
        val id = intent.getIntExtra("id", 0)
        val name = intent.getStringExtra("name").toString()
        val phone = intent.getStringExtra("phone").toString()
        val age = intent.getStringExtra("age").toString()

        binding.etName.text = Editable.Factory.getInstance().newEditable(name)
        binding.etphone.text = Editable.Factory.getInstance().newEditable(phone)
        binding.etage.text = Editable.Factory.getInstance().newEditable(age)

        viewModel = ViewModelProvider (this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(PersonaViewModel::class.java)

        binding.ivSave.setOnClickListener{
            GlobalScope.launch {
                dataPersonaSelect.name = binding.etName.text.toString()
                dataPersonaSelect.phone = binding.etphone.text.toString()
                dataPersonaSelect.age = binding.etage.text.toString()
                EditInfoPersona(dataPersonaSelect)
            }
        }
    }

    private fun EditInfoPersona(persona: PersonaModel) {
        viewModel.editarPersona(persona)
        this.finish()
    }
}
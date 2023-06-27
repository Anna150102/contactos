package com.example.agenda_celular

import PersonaAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agenda_celular.databinding.ActivityListPersonaBinding
import com.example.agenda_celular.viewModel.PersonaViewModel

class listPersona : AppCompatActivity() {
    private lateinit var binding: ActivityListPersonaBinding
    private lateinit var viewModel: PersonaViewModel
    private lateinit var personaAdapter : PersonaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPersonaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvRecycler.layoutManager=LinearLayoutManager(this)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(PersonaViewModel::class.java)

       observeEvents()

        binding.imAdd.setOnClickListener{
            val intent = Intent(this, ActivityAgregar:: class.java)
            startActivity(intent)
        }

        binding.btlog.setOnClickListener{logout()}

    }

    companion object{
        var isEdit = false
        lateinit var personaSelect : PersonaModel
    }
    private fun observeEvents() {
      viewModel.listPersona.observe(this, Observer { list ->
          list?.let{
              personaAdapter = PersonaAdapter(it, viewModel)
              binding.rvRecycler.adapter = personaAdapter
              personaAdapter.notifyDataSetChanged()
          }
      })

    }

    override fun onRestart() {
        super.onRestart()
    }
}
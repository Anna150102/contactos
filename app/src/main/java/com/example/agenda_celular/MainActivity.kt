package com.example.agenda_celular

import PersonaAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agenda_celular.databinding.ActivityMainBinding
import com.example.agenda_celular.viewModel.PersonaViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PersonaViewModel
    private lateinit var adapterPersona: PersonaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvRecycler.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).
        get(PersonaViewModel::class.java)
observeEvents()
        GlobalScope.launch {
            viewModel.EliminarTodos()
            var dataa = PersonaModel (0, "Anna Isabel", "87981240",21)
            viewModel.insertPersona(dataa)
        }

    }
    private fun observeEvents() {
        viewModel.listPersona.observe(this, Observer { list ->
            list?.let {
                adapterPersona = PersonaAdapter(it)
                binding.rvRecycler.adapter = adapterPersona
                adapterPersona.notifyDataSetChanged()
            }
        })

    }
}

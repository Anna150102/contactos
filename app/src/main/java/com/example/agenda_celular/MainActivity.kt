package com.example.agenda_celular

import PersonaAdapter
import android.content.Intent
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
    }
    }

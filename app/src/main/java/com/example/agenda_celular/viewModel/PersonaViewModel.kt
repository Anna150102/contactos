package com.example.agenda_celular.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.agenda_celular.PersonaModel
import com.example.agenda_celular.repository.PersonaRepository
import com.example.agenda_celular.room.RoomDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonaViewModel(application: Application) : AndroidViewModel(application) {
    val listPersona  : LiveData<List<PersonaModel>>
    val repository : PersonaRepository

    init {
        val PersonaDao = RoomDb.getDatabase(application).PersonaDao()
        repository = PersonaRepository(PersonaDao)
        listPersona = repository.ObtenerTodoPersona()
    }
    fun insertPersona(persona: PersonaModel) =
        viewModelScope.launch(Dispatchers.IO) { repository.guardarPersona(persona) }

    fun deletePersona (persona: PersonaModel) =
        viewModelScope.launch(Dispatchers.IO) { repository.eliminarPersona(persona)
        }
    fun EliminarTodos ()=
        viewModelScope.launch (Dispatchers.IO){repository.eliminarTodo()}

    fun editarPersona (persona: PersonaModel) =
        viewModelScope.launch(Dispatchers.IO) { repository.editarPersona(persona) }
}
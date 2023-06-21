package com.example.agenda_celular.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.agenda_celular.PersonaModel
import com.example.agenda_celular.repository.PersonaRepository
import com.example.agenda_celular.room.RoomDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonaViewModel(application: Application) : AndroidViewModel(application) {
    val listPersona  : MutableLiveData<List<PersonaModel>> = MutableLiveData()
    val repository : PersonaRepository

    init {
        repository = PersonaRepository()
     GetPersonaFirestone()
    }
    fun insertPersona(persona: PersonaModel) =
        viewModelScope.launch(Dispatchers.IO) { repository.guardarPersona(persona) }

    fun deletePersona (persona: PersonaModel) =
        viewModelScope.launch(Dispatchers.IO) { repository.eliminarPersona(persona)
        }


    fun editarPersona (persona: PersonaModel) =
        viewModelScope.launch(Dispatchers.IO) { repository.editarPersona(persona) }

    fun GetPersonaFirestone(){
        viewModelScope.launch(Dispatchers.IO){
            var listData = repository.GetAllPersonaFirestone()
            listPersona.postValue(listData)
        }
    }
}
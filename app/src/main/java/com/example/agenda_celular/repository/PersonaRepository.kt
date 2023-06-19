package com.example.agenda_celular.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.agenda_celular.PersonaModel
import com.example.agenda_celular.room.PersonaDao

class PersonaRepository constructor(
    private val PersonaDao: PersonaDao
)
{
    fun ObtenerTodoPersona(): LiveData<List<PersonaModel>> = PersonaDao.obtenerPersona().asLiveData()

    suspend fun guardarPersona(persona: PersonaModel){


        PersonaDao.guardarPersona(persona)
    }

    suspend fun eliminarPersona (persona: PersonaModel){
        PersonaDao.eliminarPersona(persona)
    }

    suspend fun eliminarTodo (){
        PersonaDao.eliminarTodosPersona()
    }

    suspend fun editarPersona(persona: PersonaModel){
        PersonaDao.actualizarPersona(persona)
    }
}
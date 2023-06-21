package com.example.agenda_celular.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.agenda_celular.FirebaseAgenda.AgendaFirebase
import com.example.agenda_celular.FirebaseAgenda.AgendaFirebase.CreatePersona
import com.example.agenda_celular.FirebaseAgenda.AgendaFirebase.getPersona
import com.example.agenda_celular.PersonaModel
import com.example.agenda_celular.room.PersonaDao

class PersonaRepository
{
   suspend fun GetAllPersonaFirestone(): List<PersonaModel> = getPersona()

    suspend fun guardarPersona(persona: PersonaModel){
    CreatePersona(persona)
    }

    suspend fun eliminarPersona (persona: PersonaModel){
     AgendaFirebase.deletePersona(persona)
    }



    suspend fun editarPersona(persona: PersonaModel){
    AgendaFirebase.UpdatePersona(persona)
    }
}
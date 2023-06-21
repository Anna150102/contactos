package com.example.agenda_celular.FirebaseAgenda

import android.util.Log
import com.example.agenda_celular.PersonaModel
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import java.lang.Exception

object AgendaFirebase {
    suspend fun getPersona (): List<PersonaModel> = coroutineScope {
      val db = Firebase.firestore
        val listapersona = mutableListOf<PersonaModel>()
    try {
        val querySnapshot = db.collection("persona").get().await()
        listapersona.addAll(GetAllListPersona(querySnapshot))
    }catch (e: Exception){
        Log.e("Error","Error document",e )
    }
        listapersona
    }

    suspend fun CreatePersona(persona: PersonaModel) = coroutineScope {
        val db = Firebase.firestore
        db.collection("persona").add(persona).await()
    }

    suspend fun UpdatePersona(persona: PersonaModel)= coroutineScope {
        val db = Firebase.firestore
        val newPersonaData= hashMapOf<String, Any>(
            "name" to persona.name,
            "phone" to persona.phone,
            "age" to persona.age
        )
        db.collection("persona").document(persona.idFirestore!!).update(newPersonaData).await()
    }

    suspend fun deletePersona(persona: PersonaModel)= coroutineScope {
        val db = Firebase.firestore
        db.collection("persona").document(persona.idFirestore!!).delete()
    }
    suspend fun GetAllListPersona(querySnapshot: QuerySnapshot): List<PersonaModel>{
        val listPersona = mutableListOf<PersonaModel>()

        for (document in querySnapshot.documents){

            val personaData = document.data
            val personaModelo = personaData?.let {
                ConvertPersonaModel(it, document.id)
            }
            listPersona.add(personaModelo!!)
        }

        return listPersona
    }
    fun ConvertPersonaModel(personaData: Map<String, Any>, id: String ):PersonaModel{
        val name = personaData["name"] as String
        val phone = personaData ["phone"] as String
        val age = personaData["age"] as String

        return PersonaModel(
            0,
            name,
            phone,
            age,
            id
        )


    }

}
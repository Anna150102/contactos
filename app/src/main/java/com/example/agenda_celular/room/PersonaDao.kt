package com.example.agenda_celular.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.agenda_celular.PersonaModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonaDao {
    @Query("SELECT * from persona")
    fun obtenerPersona(): Flow<List<PersonaModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun guardarPersona(personaModel: PersonaModel)

    @Update
    fun actualizarPersona(personaModel: PersonaModel)

    @Query("DELETE FROM persona")
    suspend fun eliminarTodosPersona()

    @Delete
    suspend fun eliminarPersona(personaModel: PersonaModel)
}
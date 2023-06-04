package com.example.agenda_celular


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "persona")
data class PersonaModel(
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "nombre")  val name: String,
    @ColumnInfo(name = "telefono")  val phone: String,
    @ColumnInfo(name = "edad")  val age: Int,

)
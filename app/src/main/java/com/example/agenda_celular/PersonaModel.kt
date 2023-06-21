package com.example.agenda_celular


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "persona")
data class PersonaModel(
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name")  var name: String,
    @ColumnInfo(name ="phone")  var phone: String,
    @ColumnInfo(name = "age")  var age: String,
val idFirestore:String ?
)
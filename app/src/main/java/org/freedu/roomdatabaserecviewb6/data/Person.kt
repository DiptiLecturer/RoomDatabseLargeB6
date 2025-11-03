package org.freedu.roomdatabaserecviewb6.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id :Int =0,
    val name :String,
    val email :String
)

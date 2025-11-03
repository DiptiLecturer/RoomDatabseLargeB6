package org.freedu.roomdatabaserecviewb6.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: Person)

    @Update
    suspend fun update(person: Person)

    @Upsert
    suspend fun upsert(person: Person)

    @Delete
    suspend fun delete(person: Person)

    @Query("SELECT * FROM person_table order by id DESC")
    fun getAllPersons(): LiveData<List<Person>>
}
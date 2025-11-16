package org.freedu.roomdatabaserecviewb6.data

import androidx.lifecycle.LiveData

class PersonRepository(private val dao: PersonDao) {
    val allPersons = dao.getAllPersons()

    suspend fun insert(person: Person) = dao.insert(person)

    suspend fun update(person: Person) = dao.update(person)

    suspend fun delete(person: Person) = dao.delete(person)

    fun searchPersons(query:String) : LiveData<List<Person>>{
        return dao.searchPersons("%$query%")
    }

}
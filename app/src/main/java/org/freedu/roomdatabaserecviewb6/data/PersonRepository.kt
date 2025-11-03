package org.freedu.roomdatabaserecviewb6.data

class PersonRepository(private val dao: PersonDao) {
    val allPersons = dao.getAllPersons()

    suspend fun insert(person: Person) = dao.insert(person)

    suspend fun update(person: Person) = dao.update(person)

    suspend fun delete(person: Person) = dao.delete(person)
}
package org.freedu.roomdatabaserecviewb6.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import kotlinx.coroutines.launch
import org.freedu.roomdatabaserecviewb6.data.Person
import org.freedu.roomdatabaserecviewb6.data.PersonDatabase
import org.freedu.roomdatabaserecviewb6.data.PersonRepository


class PersonViewModel(application: Application): AndroidViewModel(application) {


    private val repository: PersonRepository
    val allPersons: LiveData<List<Person>>
    init {
        val dao = PersonDatabase.getDatabase(application).personDao()
        repository = PersonRepository(dao)
        allPersons = repository.allPersons
    }

    fun insert(person: Person) = viewModelScope.launch {
        repository.insert(person)
    }

    fun update(person: Person) = viewModelScope.launch {
        repository.update(person)
    }

    fun delete(person: Person) = viewModelScope.launch {
        repository.delete(person)
    }

    fun searchPersons(query: String): LiveData<List<Person>>{
        return repository.searchPersons(query)

    }



}
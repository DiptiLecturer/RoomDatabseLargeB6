package org.freedu.roomdatabaserecviewb6.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.freedu.roomdatabaserecviewb6.data.Person
import org.freedu.roomdatabaserecviewb6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonAdapter
    private val viewModel: PersonViewModel by viewModels()
    private var editPerson: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeData()
        binding.btnAdd.setOnClickListener { saveOrUpdate() }
        // Search
        searchView()

    }

    private fun searchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText.orEmpty())
                updateCounter(adapter.itemCount)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter(query.orEmpty())
                updateCounter(adapter.itemCount)
                return true
            }
        })
    }

    private fun saveOrUpdate() {
        val name = binding.editName.text.toString().trim()
        val email = binding.editEmail.text.toString().trim()

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Name and Email are required", Toast.LENGTH_SHORT).show()
            return
        }

        if (editPerson == null) {
            viewModel.insert(Person(0, name, email))
            Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show()
        } else {
            val updated = editPerson!!.copy(name = name, email = email)
            viewModel.update(updated)
            editPerson = null
            binding.btnAdd.text = "Add"
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
        }

        clearFields()
    }

    private fun clearFields() {
        binding.editName.text.clear()
        binding.editEmail.text.clear()
    }

    private fun observeData() {
        viewModel.allPersons.observe(this) { persons ->
            adapter.updateList(persons)
            updateCounter(persons.size)
        }
    }

    private fun setupRecyclerView() {
        adapter = PersonAdapter(
            onEdit = { startEdit(it) },
            onDelete = { viewModel.delete(it) }
        )
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            this.adapter = this@MainActivity.adapter
        }
    }

    private fun startEdit(person: Person) {
        binding.editName.setText(person.name)
        binding.editEmail.setText(person.email)
        editPerson = person
        binding.btnAdd.text = "Update"
    }

    private fun updateCounter(count: Int) {
        binding.tvCounter.text = "Notes: $count"
    }
}




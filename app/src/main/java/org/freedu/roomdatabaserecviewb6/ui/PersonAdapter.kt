package org.freedu.roomdatabaserecviewb6.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.freedu.roomdatabaserecviewb6.data.Person
import org.freedu.roomdatabaserecviewb6.databinding.ItemPersonBinding

private val colors = listOf(
    0xFFE57373.toInt(), // Red
    0xFF81C784.toInt(), // Green
    0xFF64B5F6.toInt(), // Blue
    0xFFFFB74D.toInt(), // Orange
    0xFFBA68C8.toInt()  // Purple
)
class PersonAdapter(
    private var personList: List<Person>,
    private val onEdit:(Person)->Unit,
    private val onDelete:(Person)->Unit

) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    inner class PersonViewHolder(val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonAdapter.PersonViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PersonViewHolder(binding)

    }

    override fun onBindViewHolder(holder: PersonAdapter.PersonViewHolder, position: Int) {
       val person = personList[position]
       with(holder.binding){
           tvName.text = person.name
           tvEmail.text = person.email
           btnEdit.setOnClickListener {
               onEdit(person)
           }
           btnDelete.setOnClickListener {
               onDelete(person)
           }
           root.setBackgroundColor(colors[position % colors.size])
       }
    }

    override fun getItemCount(): Int = personList.size

    fun updateList(newList:List<Person>){
        personList = newList
        notifyDataSetChanged()
    }
}
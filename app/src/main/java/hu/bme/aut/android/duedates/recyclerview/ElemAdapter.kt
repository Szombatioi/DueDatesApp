package hu.bme.aut.android.duedates.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.duedates.R
import hu.bme.aut.android.duedates.database.DueEntity
import hu.bme.aut.android.duedates.databinding.ListItemBinding

class ElemAdapter(private val listener: ElemClickListener) :
    RecyclerView.Adapter<ElemAdapter.ElemViewHolder>() {

    private val items = mutableListOf<DueEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ElemViewHolder(
        ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ElemViewHolder, position: Int) {
        val item = items[position]

        holder.binding.classText.text = item.className
        holder.binding.subjectText.text = item.subjectName
        holder.binding.dueDate.text = item.dueDate.toString()
        holder.binding.deleteBtn.setImageResource(R.drawable.ic_bin_foreground)
        holder.binding.deleteBtn.setOnClickListener{
            items.removeAt(position)
            listener.onItemChanged(item) //Todo: Működik?
        }

        holder.binding.deleteBtn.setOnClickListener {
            listener.onItemDeleted(item)
        }
    }

    fun addItem(item: DueEntity) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun update(shoppingItems: List<DueEntity>) {
        items.clear()
        items.addAll(shoppingItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    interface ElemClickListener {
        fun onItemChanged(item: DueEntity)
        fun onItemDeleted(item: DueEntity)
    }

    inner class ElemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
}
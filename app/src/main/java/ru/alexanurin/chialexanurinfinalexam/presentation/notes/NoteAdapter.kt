package ru.alexanurin.chialexanurinfinalexam.presentation.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alexanurin.chialexanurinfinalexam.data.database.Notes
import ru.alexanurin.chialexanurinfinalexam.databinding.NoteItemBinding

//  Адаптер принимает лямбду listener для удаления элемента по клику.
class NoteAdapter(private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var noteItems: List<Notes> = listOf()

    fun setItems(items: List<Notes>) {
        this.noteItems = items
        //  Notify any registered observers that the data set has changed.
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = NoteItemBinding.inflate(inflater, parent, false)
        var holder = NoteViewHolder(convertView)
        return holder
    }

    override fun getItemCount(): Int {
        return noteItems.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        //  Каждому экземпляру ViewHolder передаются не только данные, но и listener.
        holder.bind(noteItems[position], listener)
    }

    inner class NoteViewHolder(private val convertView: NoteItemBinding) :
        RecyclerView.ViewHolder(convertView.root) {
        fun bind(data: Notes, listener: (Int) -> Unit) {
            convertView.tvNote.text = data.note
            itemView.setOnClickListener {
                listener.invoke(data.id)
            }
        }
    }
}
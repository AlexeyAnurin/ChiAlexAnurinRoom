package ru.alexanurin.chialexanurinfinalexam.presentation.channel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alexanurin.chialexanurinfinalexam.databinding.ChannelDetailsItemBinding
import ru.alexanurin.chialexanurinfinalexam.data.model.Data

//  Адаптер для отображения подробной информации о выбранном канале.
class ChannelDetailsAdapter() :
    RecyclerView.Adapter<ChannelDetailsAdapter.ChannelDetailsViewHolder>() {

    private var channelDetailsItems: List<Data> = listOf()

    fun setItems(items: List<Data>) {
        this.channelDetailsItems = items
        //  Notify any registered observers that the data set has changed.
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = ChannelDetailsItemBinding.inflate(inflater, parent, false)
        var holder = ChannelDetailsViewHolder(convertView)
        return holder
    }

    override fun getItemCount(): Int {
        return channelDetailsItems.size
    }

    override fun onBindViewHolder(holder: ChannelDetailsViewHolder, position: Int) {
        holder.bind(channelDetailsItems[position])
    }

    inner class ChannelDetailsViewHolder(private val convertView: ChannelDetailsItemBinding) :
        RecyclerView.ViewHolder(convertView.root) {
        fun bind(data: Data) {

            convertView.tvVideoTitle.text = data.title
            convertView.tvVideoDescription.text = data.description
            convertView.tvLikesCount.text = data.countLikes.toString()
        }
    }
}
package ru.alexanurin.chialexanurinroom.channel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alexanurin.chialexanurinroom.databinding.ChannelDetailsItemBinding
import ru.alexanurin.chialexanurinroom.databinding.ChannelItemBinding
import ru.alexanurin.chialexanurinroom.rest.Channel
import ru.alexanurin.chialexanurinroom.rest.Data

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
        //  Каждому экземпляру ViewHolder передаются не только данные, но и listener.
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
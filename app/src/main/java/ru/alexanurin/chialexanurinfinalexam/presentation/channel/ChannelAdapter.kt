package ru.alexanurin.chialexanurinfinalexam.presentation.channel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alexanurin.chialexanurinfinalexam.databinding.ChannelItemBinding
import ru.alexanurin.chialexanurinfinalexam.data.model.ChannelModel

//  Адаптер принимает лямбду listener для отправки запроса о получении информации о канале по клику.
class ChannelAdapter(private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder>() {

    private var channelItems: List<ChannelModel> = listOf()

    fun setItems(items: List<ChannelModel>) {
        this.channelItems = items
        //  Notify any registered observers that the data set has changed.
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = ChannelItemBinding.inflate(inflater, parent, false)
        var holder = ChannelViewHolder(convertView)
        return holder
    }

    override fun getItemCount(): Int {
        return channelItems.size
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        //  Каждому экземпляру ViewHolder передаются не только данные, но и listener.
        holder.bind(channelItems[position], listener)
    }

    inner class ChannelViewHolder(private val convertView: ChannelItemBinding) :
        RecyclerView.ViewHolder(convertView.root) {
        fun bind(data: ChannelModel, listener: (Int) -> Unit) {

            convertView.tvChannelItem.text = data.title

            itemView.setOnClickListener {
                listener.invoke(data.channelId)
            }
        }
    }
}
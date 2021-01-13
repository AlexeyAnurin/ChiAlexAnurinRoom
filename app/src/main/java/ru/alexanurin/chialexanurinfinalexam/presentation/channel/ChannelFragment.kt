package ru.alexanurin.chialexanurinfinalexam.presentation.channel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.alexanurin.chialexanurinfinalexam.R
import ru.alexanurin.chialexanurinfinalexam.databinding.FragmentChannelListBinding

class ChannelFragment : Fragment() {

    //  Dependency injections.
    private val channelViewModel: ChannelViewModel by viewModel()

    //  ViewBinding.
    private lateinit var binding: FragmentChannelListBinding

    //  Экземпляр адаптера c реализацией listener, переданного в качестве аргумента.
    private val channelAdapter = ChannelAdapter {
        val bundle = bundleOf(KEY_CHANNEL_DETAILS to it)
        findNavController().navigate(R.id.action_channelFragment_to_channelDetailsFragment, bundle)
    } // реализация клика - id в bundle и навигация

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChannelListBinding.inflate(inflater, container, false)
        binding.rvChannel.layoutManager = LinearLayoutManager(requireContext())
        binding.rvChannel.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        )
        //  Присвоить адаптер ресайклу.
        binding.rvChannel.adapter = channelAdapter
        binding.toolbarLayout.menu.text = "Profile"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  Livedata. Добавление заметок в список.
        channelViewModel.getChannelsEvent.observe(viewLifecycleOwner, Observer {
            println("List<Channels>: $it")
            channelAdapter.setItems(it)
        })

        //  progress bar. данные с серва
        channelViewModel.loadingEvent.observe(viewLifecycleOwner, {
            binding.pbChannelList.isVisible = it
        })

        binding.toolbarLayout.menu.setOnClickListener {
            findNavController().navigate(R.id.action_channelFragment_to_userProfileFragment)
        }
    }
}
package ru.alexanurin.chialexanurinroom.channel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.alexanurin.chialexanurinroom.NoteAdapter
import ru.alexanurin.chialexanurinroom.databinding.FragmentChannelDetailsListBinding

const val KEY_CHANNEL_DETAILS = "KEY_CHANNEL_DETAILS"

class ChannelDetailsFragment : Fragment() {

    //  ViewBinding.
    private lateinit var binding: FragmentChannelDetailsListBinding

    //  Dependency injections.
    private val channelDetailsViewModel by viewModel<ChannelDetailsViewModel> {
        parametersOf(requireArguments().getInt(KEY_CHANNEL_DETAILS, 0))
    }

    private val channelDetailsAdapter = ChannelDetailsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChannelDetailsListBinding.inflate(inflater, container, false)
        binding.rvChannelDetails.layoutManager = LinearLayoutManager(requireContext())
        binding.rvChannelDetails.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        )
        //  Присвоить адаптер ресайклу.
        binding.rvChannelDetails.adapter = channelDetailsAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val channelDetailsId = arguments?.getInt(KEY_CHANNEL_DETAILS)
        println("channelDetailsId: $channelDetailsId")

        channelDetailsViewModel.getChannelDetails()

        channelDetailsViewModel.getChannelDetailsEvent.observe(viewLifecycleOwner, Observer {
            channelDetailsAdapter.setItems(it.channelData.video.data)
            binding.pbChannelDetailsList.isVisible = false
        })

        binding.pbChannelDetailsList.isVisible = true
    }
}
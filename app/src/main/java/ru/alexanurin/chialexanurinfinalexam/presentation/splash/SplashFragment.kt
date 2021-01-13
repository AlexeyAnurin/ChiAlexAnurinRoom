package ru.alexanurin.chialexanurinfinalexam.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.alexanurin.chialexanurinfinalexam.R


class SplashFragment : Fragment() {

    private val splashViewModel by viewModel<SplashViewModel>() //  DI

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        splashViewModel.toLogInScreenEvent.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        })

        splashViewModel.toChannelScreenEvent.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_splashFragment_to_channelFragment)
        })
    }
}

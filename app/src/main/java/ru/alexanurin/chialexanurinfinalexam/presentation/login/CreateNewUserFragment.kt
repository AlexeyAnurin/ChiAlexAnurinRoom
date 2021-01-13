package ru.alexanurin.chialexanurinfinalexam.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.alexanurin.chialexanurinfinalexam.R
import ru.alexanurin.chialexanurinfinalexam.databinding.FragmentCreateNewUserBinding
import ru.alexanurin.chialexanurinfinalexam.presentation.login.model.UserModel
import ru.alexanurin.chialexanurinfinalexam.util.hideKeyBoard

class CreateNewUserFragment : Fragment() {

    //  Экз VM
    private val createNewUserViewModel by viewModel<CreateNewUserViewModel>()

    private lateinit var binding: FragmentCreateNewUserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateNewUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreateNewUser.setOnClickListener {
            val newUser = UserModel(
                userName = binding.etCreateNewUserName.text.toString(),
                email = binding.etCreateNewUserEmail.text.toString(),
                password = binding.etCreateNewUserPassword.text.toString()
            )
            createNewUserViewModel.createNewUser(newUser)
            it.hideKeyBoard()
        }

        //  тост с кодом ошибки.
        createNewUserViewModel.errorEvent.observe(viewLifecycleOwner, Observer {
            it?.let { Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show() }
        })

        //  навигация к списку каналов на ChannelFragment.
        createNewUserViewModel.toChannelScreenEvent.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_createNewUserFragment_to_channelFragment)
        })

        //  подписка на lottie
        createNewUserViewModel.loadingEvent.observe(viewLifecycleOwner, {
            binding.pbLoading.isVisible = it
        })
    }
}
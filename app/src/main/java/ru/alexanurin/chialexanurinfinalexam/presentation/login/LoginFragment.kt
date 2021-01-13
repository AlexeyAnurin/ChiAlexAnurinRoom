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
import ru.alexanurin.chialexanurinfinalexam.databinding.FragmentLoginBinding
import ru.alexanurin.chialexanurinfinalexam.data.model.LoginUser
import ru.alexanurin.chialexanurinfinalexam.presentation.login.model.LoginUserModel
import ru.alexanurin.chialexanurinfinalexam.util.hideKeyBoard

class LoginFragment : Fragment() {

    // экз VM
    private val loginViewModel by viewModel<LoginViewModel>()

    //  экз Binding
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  LogIn button. Conditions for navigate to ChannelFragment:
        // 2 text fields are not empty (if not - show toast wich field is empty or filled incorrectly),
        // user exist - answer from server.
        binding.btnLogIn.setOnClickListener {
            //  if(binding.etLogInUserEmail.text.toString().trim().isNotEmpty() && binding.etLogInUserPassword.text.toString().trim().isNotEmpty()){
            val loginUser = LoginUserModel(
            //   loginUser.email = binding.etLogInUserEmail.text.toString()
            email = "Alex2@gmail.com",
            //   loginUser.password = binding.etLogInUserPassword.text.toString()
            password = "Alex2Alex2"
            )
            loginViewModel.loginUser(loginUser)
            it.hideKeyBoard()
            //  }
        }

        //  navigate to CreateNewUserFragment.
        binding.btnSignUp.setOnClickListener {
            it.hideKeyBoard()
            findNavController().navigate(R.id.action_loginFragment_to_createUserFragment)
        }

        loginViewModel.toChannelScreenEvent.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_loginFragment_to_channelFragment)
        })

        loginViewModel.errorEvent.observe(viewLifecycleOwner, Observer {
            it?.let { Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show() }
        })

        //  подписка на lottie
        loginViewModel.loadingEvent.observe(viewLifecycleOwner, {
            binding.pbLoading.isVisible = it
        })

    }
}
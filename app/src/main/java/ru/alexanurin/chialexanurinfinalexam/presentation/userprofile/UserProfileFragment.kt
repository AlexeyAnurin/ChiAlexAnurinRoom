package ru.alexanurin.chialexanurinfinalexam.presentation.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import ru.alexanurin.chialexanurinfinalexam.R
import ru.alexanurin.chialexanurinfinalexam.databinding.FragmentUserProfileBinding

@KoinApiExtension
class UserProfileFragment : Fragment() {

    private val userProfileViewModel: UserProfileViewModel by viewModel() //  DI

    //  ViewBinding.
    private lateinit var binding: FragmentUserProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarLayout.menu.setOnClickListener {
            userProfileViewModel.logOut()
        }

        binding.toolbarLayout.menu.text = getString(R.string.auth_logout)

        userProfileViewModel.getUserInfoEvent.observe(viewLifecycleOwner, Observer {
            binding.tvUserName.text = it.userInfo.user.userName
            binding.tvEmail.text = it.userInfo.user.email
            binding.tvUserId.text = it.userInfo.user.id.toString()

            binding.contentLayout.visibility = View.VISIBLE
        })

        userProfileViewModel.toLoginScreenEvent.observe(viewLifecycleOwner, Observer {
           findNavController().navigate(R.id.action_userProfileFragment_to_loginFragment)
        })

        //  подписка на lottie
        userProfileViewModel.loadingEvent.observe(viewLifecycleOwner, {
            binding.pbLoading.isVisible = it
        })
    }
}

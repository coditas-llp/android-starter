package com.coditas.example.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.coditas.example.R
import com.coditas.example.data.dto.User
import com.coditas.example.data.local.AccessTokenSharedPreference
import com.coditas.example.databinding.FragmentLoginBinding
import com.coditas.example.utils.showToast
import com.coditas.resumebuilder.app.data.remote.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var mBinding: FragmentLoginBinding? = null
    private val mLoginViewModel by activityViewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentLoginBinding.inflate(layoutInflater).apply {
            mBinding = this
            fragment = this@LoginFragment
            viewModel = mLoginViewModel
            lifecycleOwner = this@LoginFragment
        }.root
    }

    fun onClickLogin() {
        mBinding?.run {
            val userEmail = edEmail.text?.toString() ?: ""
            val userPassword = edPassword.text?.toString() ?: ""
            if (validateLoginCredentials(email = userEmail, password = userPassword)) {
                mLoginViewModel.loginUser(
                    User(
                        email = userEmail,
                        password = userPassword
                    )
                ).observe(viewLifecycleOwner) { response ->
                    when (response) {
                        is NetworkResult.Success -> {
                            mLoginViewModel.showProgressbar(boolean = false)
                            val token = response.data?.data?.accessToken
                            val userId = response.data?.data?.userId
                            AccessTokenSharedPreference(requireContext()).saveToken(token)
                            AccessTokenSharedPreference(requireContext()).saveUserId(userId)
                            findNavController().navigate(LoginFragmentDirections.actionLoginScreenToDashboardScreen())
                        }
                        is NetworkResult.Loading -> {
                            mLoginViewModel.showProgressbar(boolean = true)
                        }
                        is NetworkResult.Error -> {
                            mLoginViewModel.showProgressbar(boolean = false)
                            showToast(response.message.toString())
                        }
                    }
                }
            }
        }
    }

    private fun validateLoginCredentials(email: String, password: String): Boolean {
        return if (email.isEmpty() || password.isEmpty()) {
            showToast(getString(R.string.txt_field_cannot_be_empty))
            false
        } else true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}
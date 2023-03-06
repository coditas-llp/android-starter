package com.coditas.example.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.coditas.example.R
import com.coditas.example.data.local.AccessTokenSharedPreference
import com.coditas.example.databinding.FragmentLoginBinding
import com.coditas.example.utils.showSnack
import com.coditas.example.utils.showToast
import com.coditas.resumebuilder.app.data.remote.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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

    fun onClickLogin(view: View) {
        mBinding?.run {
            val userEmail = edEmail.text?.toString() ?: ""
            val userPassword = edPassword.text?.toString() ?: ""
            if (validateLoginCredentials(email = userEmail, password = userPassword)) {
                //TODO: Call your api here, for time being perform long press on 'Login' button to continue navigating screens
                showSnack(view,resources.getString(R.string.txt_long_press_login_button))
            }
        }
    }

    fun login(){
        mLoginViewModel.loginUser(AccessTokenSharedPreference(requireContext()).getUserId()).observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    //TODO: Handle success response here
                }
                is NetworkResult.Loading -> {
                    //TODO: Handle loading response here
                }
                is NetworkResult.Error -> {
                    //TODO: Handle error response here
                }
            }
        }
    }

    fun onLongClickLogin():Boolean{
        findNavController().navigate(LoginFragmentDirections.actionLoginScreenToDashboardScreen())
        return false
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
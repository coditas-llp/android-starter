package com.coditas.example.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.coditas.example.data.local.AccessTokenSharedPreference
import com.coditas.example.databinding.FragmentSplashBinding
import com.coditas.example.utils.AppConstants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private var mBinding: FragmentSplashBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSplashBinding.inflate(layoutInflater).apply {
            mBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            if (AccessTokenSharedPreference(requireContext()).getToken() != null) {
                findNavController().navigate(SplashFragmentDirections.actionSplashScreenToDashboardScreen())
            }else{
                findNavController().navigate(SplashFragmentDirections.actionSplashScreenToLoginScreen())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}
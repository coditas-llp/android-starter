package com.coditas.example.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.coditas.example.R
import com.coditas.example.data.local.AccessTokenSharedPreference
import com.coditas.example.databinding.FragmentDashboardBinding
import com.coditas.example.utils.Logger
import com.coditas.resumebuilder.app.data.remote.NetworkResult
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private var mBinding: FragmentDashboardBinding? = null
    private val mDashboardViewModel by activityViewModels<DashboardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDashboardBinding.inflate(layoutInflater).apply {
            mBinding = this
            fragment = this@DashboardFragment
            viewModel = mDashboardViewModel
            lifecycleOwner = this@DashboardFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUserData()
    }

    private fun getUserData() {
        mDashboardViewModel.getUserInfo(AccessTokenSharedPreference(requireContext()).getUserId())
            .observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        val userId = response.data?.data?.userId
                        val userName = response.data?.data?.name
                        val userEmail = response.data?.data?.email
                        Logger.debug("Response Login -> userName = $userName, userId = $userId, email = $userEmail")
                    }
                    is NetworkResult.Loading -> {

                    }
                    is NetworkResult.Error -> {
                        Toast.makeText(requireContext(), response.message, Toast.LENGTH_LONG).show()
                        logout()
                    }
                }
            }
    }

    fun onClickLogout() {
        MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle(resources.getString(R.string.txt_sign_out_from_app))
            .setMessage(resources.getString(R.string.txt_sign_out_description))
            .setNegativeButton(resources.getString(R.string.txt_cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.txt_log_out)) { _, _ ->
                logout()
            }
            .show()
    }

    private fun logout(){
        AccessTokenSharedPreference(requireContext()).deleteToken()
        findNavController().navigate(DashboardFragmentDirections.actionDashboardScreenToLoginScreen())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}
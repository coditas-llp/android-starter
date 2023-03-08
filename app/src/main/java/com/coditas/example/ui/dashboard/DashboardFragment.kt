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
import com.coditas.example.data.remote.NetworkResult
import com.coditas.example.databinding.FragmentDashboardBinding
import com.coditas.example.utils.InternetConnection
import com.coditas.example.utils.Logger
import com.coditas.example.utils.Logger.logDebug
import com.coditas.example.utils.Logger.logInfo
import com.coditas.example.utils.showToast
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

    //TODO: Call your api using generic response to handle different states of api calls.
    private fun getUserData() {
        mDashboardViewModel.getUserInfo(AccessTokenSharedPreference(requireContext()).getUserId())
            .observe(viewLifecycleOwner) { response ->
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

    fun onClickCheckInternet(){
        showToast(if (InternetConnection().checkConnection(requireContext())){
            resources.getString(R.string.txt_internet_connection_is_available)
        }else{
            resources.getString(R.string.txt_internet_connection_is_unavailable)
        })
    }

    fun onClickShowToast() {
        val toastMessage = mBinding?.edToastMessage?.text
        showToast(
            (if (toastMessage.isNullOrEmpty().not()) {
                toastMessage.toString()
            } else {
                resources.getString(R.string.txt_please_enter_toast_message)
            })
        )
    }

    fun onClickLogInfo() {
        mBinding?.edLogMessage?.text?.let {toastMessage ->
            Logger.logInfo(toastMessage.toString())
        }.also {
            showToast(resources.getString(R.string.txt_please_check_log_message))
        }
    }

    fun onClickLogDebug(){
        mBinding?.edLogMessage?.text?.let { toastMessage ->
            Logger.logDebug(toastMessage.toString())
        }.also {
            showToast(resources.getString(R.string.txt_please_check_log_message))
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
        findNavController().navigate(DashboardFragmentDirections.actionDashboardScreenToLoginScreen())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}
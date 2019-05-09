package com.afeliz.tmdbapp.base

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.afeliz.tmdbapp.hideKeyboard
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment(), OnBackPressedCallback {
    private lateinit var vm: BaseViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, this)

        vm = getViewModel()
        vm.navigationCommands.observe(viewLifecycleOwner,
            Observer {
                    command -> when(command){
                is NavigationCommand.To ->
                    NavHostFragment.findNavController(this@BaseFragment).navigate(command.directions)
                is NavigationCommand.Back ->
                    NavHostFragment.findNavController(this@BaseFragment).navigateUp()
            }
                vm.clearNavigation()
            }
        )

        view?.setOnTouchListener { _, _ ->
            (activity as? Activity)?.let {
                hideKeyboard(it)
            }
            true
        }
    }

    override fun handleOnBackPressed(): Boolean {
        vm.navigateBack()
        return true
    }

    fun showSnackBar(view: View, message: String, length:Int){
        Snackbar.make(view, message, length).show()
    }

    abstract fun getViewModel() : BaseViewModel
}
package com.afeliz.tmdbapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections

open class BaseViewModel : ViewModel() {
    private var _navigationCommands:MutableLiveData<NavigationCommand> = MutableLiveData()

    private val _toolbarTitle = MutableLiveData<String>()
    val toolbarTitle:LiveData<String> get() = _toolbarTitle

    val navigationCommands:LiveData<NavigationCommand>
        get() = _navigationCommands

    protected fun navigate(directions: NavDirections) {
        _navigationCommands.postValue(NavigationCommand.To(directions))
    }

    fun navigateBack(){
        _navigationCommands.postValue(NavigationCommand.Back)
    }

    fun clearNavigation() {
        _navigationCommands = MutableLiveData()
    }

    fun setToolbarTitle(title : String){
        _toolbarTitle.postValue(title)
    }
}
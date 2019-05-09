package com.afeliz.tmdbapp

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.afeliz.tmdbapp.ui.MainActivity

fun MainActivity.showFragment(container:Int, fragment: Fragment){
    supportFragmentManager.beginTransaction().replace(container, fragment).commit()
}

fun MainActivity.showFragmentBackStack(container:Int, fragment: Fragment){
    supportFragmentManager.beginTransaction().addToBackStack(null).replace(container, fragment).commit()
}

fun hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = activity.currentFocus
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
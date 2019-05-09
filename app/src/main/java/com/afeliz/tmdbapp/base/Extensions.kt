package com.afeliz.tmdbapp.base

import androidx.fragment.app.Fragment
import com.afeliz.tmdbapp.ui.MainActivity

fun MainActivity.showFragment(container:Int, fragment: Fragment){
    supportFragmentManager.beginTransaction().replace(container, fragment).commit()
}

fun MainActivity.showFragmentBackStack(container:Int, fragment: Fragment){
    supportFragmentManager.beginTransaction().addToBackStack(null).replace(container, fragment).commit()
}
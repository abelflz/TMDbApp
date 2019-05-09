package com.afeliz.tmdbapp.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.afeliz.tmdbapp.ui.interfaces.FragmentListener
import com.afeliz.tmdbapp.R
import com.afeliz.tmdbapp.showFragment
import com.afeliz.tmdbapp.showFragmentBackStack
import com.afeliz.tmdbapp.ui.fragments.MainFragment
import com.afeliz.tmdbapp.ui.fragments.SecondFragment

class MainActivity : FragmentActivity(), FragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_main_activity)

        showFragment(
            R.id.FrameActivity,
            MainFragment.newInstance()
        )
    }

    override fun onMainToSecond(id: Int, title: String) {
        showFragmentBackStack(
            R.id.FrameActivity,
            SecondFragment.newInstance(id, title)
        )
    }

    override fun onBackPressed() {
        val backStackEntryCount = supportFragmentManager.backStackEntryCount

        if (backStackEntryCount == 0) {
            finish()
            return
        }

        super.onBackPressed()
    }
}
package com.prediction.tobe.presentation.ui

import android.support.v4.view.GravityCompat
import android.view.View
import com.prediction.tobe.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.drawerLayout

class MainActivityLayout : AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {

        drawerLayout {
            id = R.id.drawer
            lparams(width = matchParent, height = matchParent)



            navigationView {
                id = R.id.navView
                fitsSystemWindows = true

            }.lparams(height = matchParent, gravity = GravityCompat.START)
        }

    }


}
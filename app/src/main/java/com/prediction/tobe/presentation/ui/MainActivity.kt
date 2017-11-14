package com.prediction.tobe.presentation.ui

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import com.prediction.tobe.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.support.v4.drawerLayout

class MainActivity : AppCompatActivity() {
    private lateinit var view: MainActivityLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        view = MainActivityLayout()
        view.setContentView(this)
        
        //setSupportActionBar(toolbar)

    }

}

class MainActivityLayout : AnkoComponent<MainActivity> {
    lateinit var drawer: DrawerLayout
    private val actHPadding = R.dimen.activity_h_margin
    private val actVPadding = R.dimen.activity_v_margin

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {

        drawer = drawerLayout {
            id = R.id.drawer
            lparams(width = matchParent, height = matchParent)

            coordinatorLayout {
                themedAppBarLayout(theme = R.style.AppTheme_AppBarOverlay) {
                    toolbar {
                        popupTheme = R.style.AppTheme_PopupOverlay
                        backgroundColor = R.color.colorPrimary
                    }.lparams(width = matchParent, height = android.R.attr.actionBarSize)
                }.lparams(width = matchParent)
            }.lparams(width = matchParent, height = matchParent)

            navHeader(ui)

            navigationView {
                id = R.id.navView
                fitsSystemWindows = true

            }.lparams(height = matchParent, gravity = GravityCompat.START)
        }

        return drawer

    }

    private fun navHeader(ui: AnkoContext<MainActivity>): View = with(ui) {
        verticalLayout(theme = R.style.ThemeOverlay_AppCompat_Dark) {
            lparams(width = matchParent)
            gravity = Gravity.BOTTOM
            backgroundResource = R.drawable.nav_header_bg
            setPadding(dip(actHPadding), dip(actVPadding), dip(actHPadding), dip(actVPadding))

            imageView {
                id = R.id.imgUserAvatar
                imageResource = R.drawable.ic_account_circle_80dp
            }.lparams(width = R.dimen.nav_avatar_size, height = R.dimen.nav_avatar_size) {
                topMargin = dip(40)
            }

            textView {
                id = R.id.txtUserName
                topPadding = dip(R.dimen.nav_header_vertical_spacing)
                textResource = R.string.fish_username
                textAppearance = R.style.Base_TextAppearance_AppCompat_Body1
            }.lparams(width = matchParent)

            textView {
                id = R.id.txtUserEmail
                textResource = R.string.fish_username
            }.lparams(width = matchParent)
        }
    }
}
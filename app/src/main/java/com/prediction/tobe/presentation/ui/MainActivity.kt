package com.prediction.tobe.presentation.ui

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.prediction.tobe.AppToBe
import com.prediction.tobe.R
import com.prediction.tobe.presentation.presenter.MainPresenter
import com.prediction.tobe.session.SessionHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.longToast
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IMainView {
    @Inject
    lateinit var presenter: MainPresenter
    @Inject
    lateinit var session: SessionHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppToBe.di.mainComponent().inject(this)

        initDrawer()

        val headerView = navView.getHeaderView(0)
        val txtHeaderUserName: TextView = headerView.findViewById(R.id.txtUserName)
        val txtHeaderUserEmail: TextView = headerView.findViewById(R.id.txtUserEmail)
        txtHeaderUserName.text = session.user.name
        txtHeaderUserEmail.text = session.user.email
    }

    private fun initDrawer() {
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_log_out -> longToast("Later. No escape now")
            }
            true
        }

    }

}
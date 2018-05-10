package com.prediction.tobe.presentation.ui.main

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.prediction.tobe.AppToBe
import com.prediction.tobe.R
import com.prediction.tobe.presentation.presenter.MainPresenter
import com.prediction.tobe.presentation.ui.predict.edit.PredictEditActivity
import com.prediction.tobe.presentation.ui.predict.list.PredictListFragment
import com.prediction.tobe.helper.session.SessionHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton
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

        presenter.attachView(this)

        val headerView = navView.getHeaderView(0)
        val txtHeaderUserName: TextView = headerView.findViewById(R.id.txtUserName)
        val txtHeaderUserEmail: TextView = headerView.findViewById(R.id.txtUserEmail)
        txtHeaderUserName.text = session.user.name
        txtHeaderUserEmail.text = session.user.email

        // initial fragment loaded
        navigateFromDrawer(NavOptions.LIST_ALL)

        presenter.onViewInit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.nav_add_predict -> startActivity(Intent(this, PredictEditActivity::class.java))
        }

        return true
    }

    private fun initDrawer() {
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_predict_list -> navigateFromDrawer(NavOptions.LIST_ALL)
                R.id.nav_log_out -> alert(getString(R.string.msg_log_out_confirm), getString(R.string.title_log_out_confirm)) {
                    okButton { presenter.logOut() }
                    cancelButton { }
                }
            }
            true
        }

    }

    private fun navigateFromDrawer(opt: NavOptions) {
        val fr: Fragment
        when (opt) {
            NavOptions.LIST_ALL -> fr = PredictListFragment() // todo implement a factory?
        }

        fragmentManager.beginTransaction().add(contentMain.id, fr).commit()
    }

    enum class NavOptions {
        LIST_ALL
    }
}
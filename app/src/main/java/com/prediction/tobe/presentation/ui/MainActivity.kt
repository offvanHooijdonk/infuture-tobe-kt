package com.prediction.tobe.presentation.ui

//import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        MainActivityLayout().setContentView(this)
        //setSupportActionBar(toolbar)

    }

}

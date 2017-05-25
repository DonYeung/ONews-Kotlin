package com.don.onews_kotlin

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var hometabToolbarTextviewTitle: TextView? = null
    private var navigation: BottomNavigationView? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                hometabToolbarTextviewTitle!!.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                hometabToolbarTextviewTitle!!.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                hometabToolbarTextviewTitle!!.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_setting -> {
                hometabToolbarTextviewTitle!!.setText(R.string.title_setting)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hometabToolbarTextviewTitle = findViewById(R.id.hometab_toolbar_textview_title) as TextView
        navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}

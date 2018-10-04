package com.applanticstudio.squarefencing

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.applanticstudio.squarefencing.ui.historical.HistoricalDataFragment
import com.applanticstudio.squarefencing.ui.simulation.SimulationFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val fragment1: Fragment = SimulationFragment()
    private val fragment2: Fragment = HistoricalDataFragment()

    private var active = fragment1

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_data -> {

                supportFragmentManager.beginTransaction().hide(active).show(fragment2).commit()
                active = fragment2
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_simulation -> {

                supportFragmentManager.beginTransaction().hide(active).show(fragment1).commit()
                active = fragment1
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit()
        supportFragmentManager.beginTransaction().add(R.id.main_container, fragment1, "1").commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}

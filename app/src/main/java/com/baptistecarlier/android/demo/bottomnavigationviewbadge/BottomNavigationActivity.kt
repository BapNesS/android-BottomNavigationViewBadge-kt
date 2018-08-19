package com.baptistecarlier.android.demo.bottomnavigationviewbadge

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.widget.FrameLayout
import android.widget.TextView
import com.baptistecarlier.android.demo.bottomnavigationviewbadge.adapter.DemoViewPagerAdapter
import com.baptistecarlier.android.demo.bottomnavigationviewbadge.fragment.OnCallbackReceived
import com.baptistecarlier.android.demo.bottomnavigationviewbadge.model.BottomNavViewModel
import com.baptistecarlier.android.demo.bottomnavigationviewbadge.model.EnumBottomNavigationLayout
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

/*
 * @author Baptiste Carlier @bapness
 */
class BottomNavigationActivity : AppCompatActivity(), OnCallbackReceived {

    lateinit var bottomNavViewModel : BottomNavViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        viewpager.setAdapter(DemoViewPagerAdapter(supportFragmentManager, this))
        // disable pager swiping
        viewpager.setOnTouchListener(OnTouchListener { v, event -> true })

        bottomNavViewModel = ViewModelProviders.of(this).get(BottomNavViewModel::class.java);

        bindNavigationViews()
    }

    private fun bindNavigationViews() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottomNavViewModel.valueMap?.forEach { (position, value) -> bindNavigationView(position, value) }
    }

    private fun bindNavigationView(position: Int = 0, value: Int = 0) {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val mbottomNavigationMenuView = navigation.getChildAt(0) as BottomNavigationMenuView

        // Inflating new view
        val newItem = LayoutInflater.from(this)
                .inflate(EnumBottomNavigationLayout.getLayoutIdFromPosition(position),
                        mbottomNavigationMenuView, false)

        var newItemRedCircle = newItem.findViewById(R.id.view_alert_circle) as FrameLayout
        var newItemCountTextView = newItem.findViewById(R.id.view_alert_count_textview) as TextView

        // Set text and color
        if (0 < value) {
            newItemCountTextView.text = value.toString();
            newItemRedCircle.setVisibility(View.VISIBLE);
        } else {
            newItemCountTextView.text = ""
            newItemRedCircle.setVisibility(View.GONE);
        }

        // Set layout params for the new view
        val view = mbottomNavigationMenuView.getChildAt(position)
        val itemView = view as BottomNavigationItemView
        var currentView = itemView.getChildAt(0) as View;
        newItem.layoutParams = currentView.layoutParams;

        // Remove current icon + badge
        itemView.removeViewAt(0)

        // Adding the new one
        itemView.addView(newItem, 0)

        // Fix state for color
        if (itemView.isSelected) {
            newItem.isSelected = true
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewpager.setCurrentItem(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                viewpager.setCurrentItem(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                viewpager.setCurrentItem(2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun incrementBottomNavigationViewItem(position: Int) {
        updateBottomNavigationViewItem(position)
    }

    override fun resetBottomNavigationViewItem(position: Int) {
        updateBottomNavigationViewItem(position, true)
    }

    fun updateBottomNavigationViewItem(position: Int = 0, reset : Boolean = false) {

        // Getting and setting counter value
        // Only put Int in this the textview or update this part
        var value = 0
        if ( !reset ) {
            value = bottomNavViewModel.increment(position)
        } else {
            bottomNavViewModel.reset(position)
        }

        bindNavigationView(position, value)
    }

}

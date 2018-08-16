package com.baptistecarlier.android.demo.bottomnavigationviewbadge

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.baptistecarlier.android.demo.bottomnavigationviewbadge.adapter.DemoViewPagerAdapter
import com.baptistecarlier.android.demo.bottomnavigationviewbadge.fragment.OnCallbackReceived
import kotlinx.android.synthetic.main.activity_bottom_navigation.*
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.BitmapDrawable
import android.widget.TextView
import android.widget.FrameLayout
import android.support.v4.view.MenuItemCompat.getActionView
import android.view.View
import android.view.LayoutInflater
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.view.MenuItem
import android.view.ViewGroup
import android.view.MotionEvent
import android.view.View.OnTouchListener




class BottomNavigationActivity : AppCompatActivity(), OnCallbackReceived {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        viewpager.setAdapter(DemoViewPagerAdapter(supportFragmentManager, this))
        // disable pager swiping
        viewpager.setOnTouchListener(OnTouchListener { v, event -> true })

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun incrementBottomNavigationViewItem(position: Int) {
        Log.d("Activity", "incrementBottomNavigationViewItem( ${position} )");
        updateBottomNavigationViewItem(position)
    }

    override fun resetBottomNavigationViewItem(position: Int) {
        Log.d("Activity", "resetBottomNavigationViewItem( ${position} )");
        updateBottomNavigationViewItem(position, true)
    }

    fun updateBottomNavigationViewItem(position: Int = 0, reset : Boolean = false) {
        Log.d("Activity", "updateBottomNavigationViewItem( ${position} )");

        val mbottomNavigationMenuView = navigation.getChildAt(0) as BottomNavigationMenuView

        // Inflating new view
        val newItem = LayoutInflater.from(this)
                .inflate(R.layout.view_home_badge,
                        mbottomNavigationMenuView, false)

        var newItemRedCircle = newItem.findViewById(R.id.view_alert_red_circle) as FrameLayout
        var newItemCountTextView = newItem.findViewById(R.id.view_alert_count_textview) as TextView

        // Getting and setting counter value
        // Only put Int in this the textview or update this part
        var counter = 0
        if ( !reset ) {
            if (newItemCountTextView.text.isNotBlank()) {
                counter = newItemCountTextView.text.toString().toInt()
            }
            counter++
        }

        // Set text and color
        if (0 < counter) {
            newItemCountTextView.text = counter.toString();
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

}

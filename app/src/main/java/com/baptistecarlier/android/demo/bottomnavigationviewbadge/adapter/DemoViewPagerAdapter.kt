package com.baptistecarlier.android.demo.bottomnavigationviewbadge.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.baptistecarlier.android.demo.bottomnavigationviewbadge.fragment.DemoFragment
import com.baptistecarlier.android.demo.bottomnavigationviewbadge.model.EnumBottomNavigationLayout

/*
 * @author Baptiste Carlier @bapness
 */
class DemoViewPagerAdapter(supportFragmentManager: FragmentManager?, context: Context) : FragmentPagerAdapter(supportFragmentManager) {

    var mContext: Context? = null

    override fun getItem(position: Int): Fragment {
        return DemoFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return EnumBottomNavigationLayout.values().size
    }
}
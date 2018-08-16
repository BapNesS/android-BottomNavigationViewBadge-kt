package com.baptistecarlier.android.demo.bottomnavigationviewbadge.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.baptistecarlier.android.demo.bottomnavigationviewbadge.fragment.DemoFragment


class DemoViewPagerAdapter(supportFragmentManager: FragmentManager?, context: Context) : FragmentPagerAdapter(supportFragmentManager) {

    var mContext: Context? = null

    override fun getItem(position: Int): Fragment {
        return DemoFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return 3
    }
}
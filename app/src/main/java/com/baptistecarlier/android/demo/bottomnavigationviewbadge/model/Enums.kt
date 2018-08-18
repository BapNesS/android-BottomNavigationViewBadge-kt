package com.baptistecarlier.android.demo.bottomnavigationviewbadge.model

import com.baptistecarlier.android.demo.bottomnavigationviewbadge.R


enum class BottomNavViewMainNavigation(val position: Int, val layoutId : Int) {

    HOME(0, R.layout.view_home_badge),
    DASHBOARD(1, R.layout.view_dashboard_badge),
    NOTIFICATION(2, R.layout.view_notification_badge);


    companion object {
        private val map = BottomNavViewMainNavigation.values().associateBy(BottomNavViewMainNavigation::position);
        fun getLayoutIdFromPosition(position: Int) = map[position]!!.layoutId
    }
}
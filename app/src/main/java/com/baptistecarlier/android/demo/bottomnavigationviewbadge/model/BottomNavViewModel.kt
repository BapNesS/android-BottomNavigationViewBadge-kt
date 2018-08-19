package com.baptistecarlier.android.demo.bottomnavigationviewbadge.model

import android.arch.lifecycle.ViewModel

/*
 * @author Baptiste Carlier @bapness
 */
class BottomNavViewModel : ViewModel() {

    // Position / Value
    var valueMap : HashMap<Int, Int>? = null

    private fun initNavigation() {
        val size = EnumBottomNavigationLayout.values().size-1
        valueMap = HashMap<Int, Int>()
        for ( itemIndex in 0 .. size ) {
            valueMap!!.put(itemIndex, 0)
        }
    }

    fun increment(position: Int): Int {
        if ( valueMap == null ) {
            initNavigation()
        }
        var value = valueMap!!.get(position)!! + 1
        valueMap!!.set(position, value)
        return value
    }

    fun reset(position: Int) {
        if ( valueMap == null ) {
            initNavigation()
        }
        valueMap!!.set(position, 0)
    }

}
package com.baptistecarlier.android.demo.bottomnavigationviewbadge.fragment

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.baptistecarlier.android.demo.bottomnavigationviewbadge.R

/*
 * @author Baptiste Carlier @bapness
 */
class DemoFragment() : Fragment() {

    val fragmentPosition: Int by lazy { arguments?.getInt("fragmentPosition") ?: 0}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_demo, container, false)

        initSubviews(view!!)

        // Inflate the layout for this fragment
        return view
    }

    private fun initSubviews(view: View) {
        var text : TextView = view.findViewById(R.id.textView)
        text.append(" #${fragmentPosition+1}")

        var buttonIncrementFirst : Button = view.findViewById(R.id.buttonIncrementFirst)
        buttonIncrementFirst.setOnClickListener {
            mCallback!!.incrementBottomNavigationViewItem(0);
        }

        var buttonIncrementCurrent : Button = view.findViewById(R.id.buttonIncrementCurrent)
        buttonIncrementCurrent.setOnClickListener {
            mCallback!!.incrementBottomNavigationViewItem(fragmentPosition);
        }

        var buttonResetFirst : Button = view.findViewById(R.id.buttonResetFirst)
        buttonResetFirst.setOnClickListener {
            mCallback!!.resetBottomNavigationViewItem(0);
        }

        var buttonResetCurrent : Button = view.findViewById(R.id.buttonResetCurrent)
        buttonResetCurrent.setOnClickListener {
            mCallback!!.resetBottomNavigationViewItem(fragmentPosition);
        }

    }

    var mCallback: OnCallbackReceived? = null

    @SuppressWarnings("deprecation")
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            try {
                mCallback = activity as OnCallbackReceived
            } catch (e: ClassCastException) {
                Log.d("DemoFragment", "Activity doesn't implement OnCallbackReceived");
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mCallback = context as OnCallbackReceived
        } catch (e: ClassCastException) {
            Log.d("DemoFragment", "Context doesn't implement OnCallbackReceived");
        }
    }

    companion object {
        fun newInstance(fragmentPosition: Int) : DemoFragment {
            return DemoFragment().apply {
                arguments = Bundle().apply {
                    putInt("fragmentPosition", fragmentPosition)
                }
            }
        }
    }
}

interface OnCallbackReceived {

    fun incrementBottomNavigationViewItem(position: Int = 0)

    fun resetBottomNavigationViewItem(position: Int = 0)

}

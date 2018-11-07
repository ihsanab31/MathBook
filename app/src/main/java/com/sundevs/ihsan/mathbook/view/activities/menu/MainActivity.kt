package com.sundevs.ihsan.mathbook.view.activities.menu

import android.os.Bundle
import com.bumptech.glide.Glide
import com.sundevs.ihsan.mathbook.R
import com.sundevs.ihsan.mathbook.view.base.NormalActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : NormalActivity() {


    override fun getActivityView(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iniView()
    }

    override fun isActionBarEnable(): Boolean {
        return false
    }

    private fun iniView() {
        Glide.with(this)
            .load(R.drawable.part_one)
            .into(iv_part_one)

    }
}

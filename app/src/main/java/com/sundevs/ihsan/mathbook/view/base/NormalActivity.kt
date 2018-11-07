package com.sundevs.ihsan.mathbook.view.base

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 05, November, 2018
 * ------------------------------
 * This class for normal activity
 */
abstract class NormalActivity : BaseActivity() {

    abstract fun isActionBarEnable(): Boolean

    override fun onBindView() {
        super.onBindView()
        initActionBar()
    }

    private fun initActionBar() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            if (!isActionBarEnable()) {
                actionBar.hide()
            }
        }
    }
}
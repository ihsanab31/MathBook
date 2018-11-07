package com.sundevs.ihsan.mathbook.view.base

import android.support.annotation.StringRes

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 05, November, 2018
 * ------------------------------
 * This class for base view
 */
interface BaseView {

    fun  showProgressDialog( message : String )

    fun showProgressDialog(@StringRes message: Int)

    fun dismissProgressDialog()

    fun showMessage(message: String)

    fun showMessage(@StringRes message: Int)
}
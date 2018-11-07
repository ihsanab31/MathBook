package com.sundevs.ihsan.mathbook.view.base

import android.Manifest
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.Nullable
import android.support.annotation.StringRes
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import android.view.WindowManager
import android.content.Intent


/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 05, November, 2018
 * ------------------------------
 * This class for base activity
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {
    private var progressDialog: ProgressDialog? = null
    private var isPaused: Boolean = false
    val REQUEST_ID_MULTIPLE_PERMISSIONS = 1
    @LayoutRes
    internal abstract fun getActivityView(): Int

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getActivityView())
        onBindView()
        checkAndRequestPermissions()

    }

    protected open fun onBindView() {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showProgressDialog(message: String) {
        if (isPaused) return
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
            progressDialog!!.setCancelable(false)
            progressDialog!!.setCanceledOnTouchOutside(false)
        }

        if (!progressDialog!!.isShowing) {
            progressDialog!!.setMessage(message)
            progressDialog!!.show()
        }
    }

    override fun showProgressDialog(@StringRes message: Int) {
        if (isPaused) return
        showProgressDialog(getString(message).toInt())
    }

    override fun dismissProgressDialog() {
        if (isPaused) return
        if (progressDialog != null && progressDialog!!.isShowing())
            progressDialog!!.dismiss()
    }

    override fun showMessage(message: String) {
        if (isPaused) return
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(@StringRes message: Int) {
        if (isPaused) return
        showMessage(getString(message))
    }

    override fun onPause() {
        super.onPause()
        isPaused = true
    }

    override fun onResume() {
        super.onResume()
        isPaused = false
    }

    fun getBundle(): Bundle? {
        return if (intent.extras != null)
            intent.extras
        else
            Bundle()
    }

    fun startActivityNoHistory(target: Class<*>) {
        startActivity(Intent(this, target))
        val intent = Intent(applicationContext, target)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    fun startActivityHistory(target: Class<*>) {
        startActivity(Intent(this, target))

    }

    fun fullScreen(appCompatActivity: AppCompatActivity) {
        appCompatActivity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun checkAndRequestPermissions(): Boolean {
        val camerapermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val internet = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
        val writepermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val permissionLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val permissionRecordAudio = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)


        val listPermissionsNeeded = ArrayList<String>()

        if (internet != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.INTERNET)
        }
        if (writepermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (permissionRecordAudio != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECORD_AUDIO)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
            return false
        }
        return true
    }
}
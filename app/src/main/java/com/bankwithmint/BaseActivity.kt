package com.bankwithmint

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.bankwithmint.constant.Constant
import com.bankwithmint.network.ProcessService
import com.shashank.sony.fancytoastlib.FancyToast
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

abstract class BaseActivity : AppCompatActivity() {

    lateinit var processService: ProcessService
    lateinit var progressDialog: ProgressDialog

    @LayoutRes
    abstract fun getLayoutId(): Int

    //Init View
    abstract fun initView()

    // Method for code arrangement. Don't rearrange or delete!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        processService = ProcessService()
        this.progressDialog = ProgressDialog(this,R.style.MyAlertDialogStyle)
        progressDialog.setMessage("fetching card data please wait...")
    }
    fun showProgressDialog(isShow: Boolean) {
        if (isShow) {
            showProgress()
        } else {
            dismissProgress()
        }
    }

    private fun showProgress() {
        if (!progressDialog.isShowing) progressDialog.show()
    }

    private fun dismissProgress() {
        if (progressDialog.isShowing) progressDialog.dismiss()
    }
    // Method for code arrangement. Don't rearrange or delete!
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initView()
    }

     fun validatefield(cardnum: String): Boolean {
        if(cardnum.isEmpty()) {
            return false
        }
            return true
     }


    fun checkError(errorMessage: String) {
        try {
//   Check internet connection
            if (!isNetworkAvailable()) {
                FancyToast.makeText(this, this.getString(R.string.check_your_internet_connection),
                    FancyToast.LENGTH_SHORT,  FancyToast.ERROR, false).show()
                return
            } else {
                FancyToast.makeText(this, errorMessage, FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
           }
        } catch (e: Exception) {
            FancyToast.makeText(this, getString(R.string.system_error), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
        }
    }
   private fun connectedToTheNetwork(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
   private fun isNetworkAvailable(): Boolean {

        if (connectedToTheNetwork()) {
            Thread(Runnable {

                try {
                    val urlc = URL(Constant.BASE_URL)
                        .openConnection() as HttpURLConnection
                    urlc.setRequestProperty("User-Agent", "Android")
                    urlc.setRequestProperty("Connection", "close")
                    urlc.connectTimeout = 1500
                    urlc.connect()

                } catch (e: IOException) {
                    Log.e("BaseActivity__", "Error checking internet connection", e)
                }
            }).start()
            return true

        } else {
            Log.d("BaseActivity__" ,"No network available!")
        }
        return false
    }


}
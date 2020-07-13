package com.bankwithmint

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {



    @LayoutRes
    abstract fun getLayoutId(): Int

    //Init View
    abstract fun initView()

    //Init Data observer from viewModel
    abstract fun initDataObserver()

    //fetch Data
    abstract fun fetchData()

    // Method for code arrangement. Don't rearrange or delete!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

    }

    // Method for code arrangement. Don't rearrange or delete!
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initView()
        initDataObserver()
        fetchData()
    }
}
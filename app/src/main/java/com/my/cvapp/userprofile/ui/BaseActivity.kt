package com.my.cvapp.userprofile.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.my.cvapp.di.isConnectedToInternet
import com.my.cvapp.di.toast

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isConnectedToInternet()) {
            toast("No Internet Connection!! Please try again")
        }
}
}
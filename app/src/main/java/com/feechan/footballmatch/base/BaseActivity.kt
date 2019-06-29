package com.feechan.footballmatch.base

import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import com.feechan.footballmatch.utils.invisible
import com.feechan.footballmatch.utils.visible

abstract class BaseActivity : AppCompatActivity() {
    lateinit var progressBar: ProgressBar

//    fun showLoadingBar(){
//        progressBar.visible()
//    }

    fun hideLoadingBar() {
        progressBar.invisible()
    }
}
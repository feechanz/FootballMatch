package com.feechan.footballmatch.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getContentView(container!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setup()
    }

    abstract fun getContentView(container: ViewGroup): View

    abstract fun setup()

//    fun showLoadingBar(){
//        if( activity is BaseActivity){
//            (activity as BaseActivity).showLoadingBar()
//        } else {
//
//        }
//    }

    fun hideLoadingBar(){
        if( activity is BaseActivity){
            (activity as BaseActivity).hideLoadingBar()
        } else {

        }
    }

//    fun showFailedErrorMessage(errorMessage: String) {
//        //DialogUtils.showErrorMessageDialog(activity, errorMessage)
//    }
//
//    fun showNetworkErrorMessage() {
//        //showFailedErrorMessage()
//    }
}
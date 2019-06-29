package com.feechan.footballmatch.presenter

import com.feechan.footballmatch.base.BasePresenter
import com.feechan.footballmatch.base.BaseView

interface MainContract {
    interface View : BaseView {
        fun initBaseView()
    }

    interface Presenter : BasePresenter {
//        fun loadData()
    }
}
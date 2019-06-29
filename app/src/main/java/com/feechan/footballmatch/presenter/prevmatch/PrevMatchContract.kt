package com.feechan.footballmatch.presenter.prevmatch

import com.feechan.footballmatch.base.BasePresenter
import com.feechan.footballmatch.base.BaseView
import com.feechan.footballmatch.data.network.response.Event

interface PrevMatchContract {
    interface View : BaseView {
        fun loadMatchData(data: List<Event>)
    }

    interface Presenter : BasePresenter {
        fun loadData()
    }
}
package com.feechan.footballmatch.presenter

import com.feechan.footballmatch.base.BasePresenter
import com.feechan.footballmatch.base.BaseView
import com.feechan.footballmatch.data.network.response.Event

interface DetailMatchContract {
    interface View : BaseView {
        fun initBaseView()
        fun loadMatchData(data: Event)
        fun loadImage(homebadgeurl: String, awaybadgeurl: String)
        fun showSnackbar(text: String)
    }

    interface Presenter : BasePresenter {
        fun loadData(eventid: String)
        fun loadImage(homename: String, awayname: String)

        fun addToFavorite(event: Event)
        fun getEventFavoriteById(eventid: String): List<Event>
        fun removeFromFavorite(eventId: String)
    }
}
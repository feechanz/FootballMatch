package com.feechan.footballmatch.presenter.favorites

import android.content.Context
import com.feechan.footballmatch.data.network.response.Event
import com.feechan.footballmatch.data.sqllite.SportOpenHelper
import com.feechan.footballmatch.utils.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select


class FavoritesPresenter(private val view: FavoritesContract.View,
                         private val database: SportOpenHelper) : FavoritesContract.Presenter{
    override fun destroy() {

    }

    override fun loadData() {
        //view.showLoadingBar()
        val favorites = database.getFavorites()
        view.hideLoadingBar()
        view.loadMatchData(favorites)
    }
}
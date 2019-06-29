package com.feechan.footballmatch.presenter

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.feechan.footballmatch.data.SportApiDB
import com.feechan.footballmatch.data.network.response.Event
import com.feechan.footballmatch.data.network.response.EventResponse
import com.feechan.footballmatch.data.network.response.TeamResponse
import com.feechan.footballmatch.data.repository.ApiRepository
import com.feechan.footballmatch.data.sqllite.SportOpenHelper
import com.feechan.footballmatch.utils.CoroutineContextProvider
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import com.feechan.footballmatch.utils.database
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.*
import java.util.*

class DetailMatchPresenter(private val view: DetailMatchContract.View,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val database: SportOpenHelper,
                           private val contextCoroutine: CoroutineContextProvider = CoroutineContextProvider()) : DetailMatchContract.Presenter{

    override fun addToFavorite(event: Event) {
        try {
            database.insertEventFavorites(event)
            view.showSnackbar( "Added to favorite")
        } catch (e: SQLiteConstraintException){
            view.showSnackbar("Oops.. something error")
        }
    }

    override fun getEventFavoriteById(eventid: String): List<Event> {
        return database.getEventFavoriteById(eventid)
    }

    override fun removeFromFavorite(eventId: String) {
        try {
            database.deleteEventFavorites(eventId)
            view.showSnackbar("Removed to favorite")
        } catch (e: SQLiteConstraintException){
            view.showSnackbar("Oops.. something error")
        }
    }


    override fun loadData(eventid: String) {
        //view.showLoadingBar()
        async(contextCoroutine.main){
            val data =  bg {
                gson.fromJson(apiRepository
                        .doRequest(SportApiDB.getEventDetail(eventid)),
                        EventResponse::class.java
                )
            }

            val events = data.await().events
            if(events.isNotEmpty()) {
                view.loadMatchData(events[0])
                view.hideLoadingBar()
            }
        }
    }

    override fun loadImage(homename: String, awayname: String) {
        homename.replace(" ", "%20")
        awayname.replace(" ", "%20")

        async(contextCoroutine.main){
            val data1 =  bg {
                gson.fromJson(apiRepository
                        .doRequest(SportApiDB.getTeam(homename)),
                        TeamResponse::class.java
                )
            }

            val data2 = bg {
                gson.fromJson(apiRepository
                        .doRequest(SportApiDB.getTeam(awayname)),
                        TeamResponse::class.java
                )
            }

            val teams1 = data1.await().teams
            val teams2 = data2.await().teams
            if(teams1.isNotEmpty() && teams2.isNotEmpty()) {
                view.loadImage(data1.await().teams[0].teamBadge!!, data2.await().teams[0].teamBadge!!)
            }
        }
    }

    override fun destroy() {
    }
}
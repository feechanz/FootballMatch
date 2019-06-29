package com.feechan.footballmatch.presenter.nextmatch

import com.feechan.footballmatch.data.SportApiDB
import com.feechan.footballmatch.data.network.response.EventResponse
import com.google.gson.Gson
import com.feechan.footballmatch.data.repository.ApiRepository
import com.feechan.footballmatch.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(private val view: NextMatchContract.View,
                         private val apiRepository:ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) : NextMatchContract.Presenter{
    override fun destroy() {

    }

    override fun loadData() {
        //view.showLoadingBar()
//        doAsync {
//            val data = gson.fromJson(apiRepository
//                    .doRequest(SportApiDB.get15EventNext()),
//                    EventResponse::class.java
//            )
//
//            uiThread {
//                view.hideLoadingBar()
//                view.loadMatchData(data.events)
//            }
//        }
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(SportApiDB.get15EventNext("4328")),
                        EventResponse::class.java
                )
            }
            view.loadMatchData(data.await().events)
            view.hideLoadingBar()
        }
    }
}
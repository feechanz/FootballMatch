package com.feechan.footballmatch.presenter.nextmatch

import com.feechan.footballmatch.data.SportApiDB
import com.feechan.footballmatch.data.network.response.Event
import com.feechan.footballmatch.data.network.response.EventResponse
import com.feechan.footballmatch.data.repository.ApiRepository
import com.feechan.footballmatch.utils.TestContextProvider
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {
    @Mock
    private
    lateinit var view: NextMatchContract.View

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testLoadData() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(SportApiDB.get15EventNext(ArgumentMatchers.anyString())),
                EventResponse::class.java
        )).thenReturn(response)

        presenter.loadData()

        Mockito.verify(view).hideLoadingBar()
        Mockito.verify(view).loadMatchData(events)
    }
}
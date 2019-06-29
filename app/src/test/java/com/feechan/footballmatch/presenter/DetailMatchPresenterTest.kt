package com.feechan.footballmatch.presenter

import android.database.sqlite.SQLiteConstraintException
import com.feechan.footballmatch.data.SportApiDB
import com.feechan.footballmatch.data.network.response.Event
import com.feechan.footballmatch.data.network.response.EventResponse
import com.feechan.footballmatch.data.network.response.Team
import com.feechan.footballmatch.data.network.response.TeamResponse
import com.feechan.footballmatch.data.repository.ApiRepository
import com.feechan.footballmatch.data.sqllite.SportOpenHelper
import com.feechan.footballmatch.utils.TestContextProvider
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailMatchPresenterTest{

    @Mock
    private
    lateinit var view: DetailMatchContract.View

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var database: SportOpenHelper

    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view, apiRepository, gson, database, TestContextProvider())
    }
    @Test
    fun testLoadData(){
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)

        val event = Event(0, "","","",0,0,"",0,"","","","","","","",0,"","","","","","","")
        events.add(event)

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(SportApiDB.getEventDetail(ArgumentMatchers.anyString())),
                EventResponse::class.java
        )).thenReturn(response)

        presenter.loadData(anyString())

        Mockito.verify(view).hideLoadingBar()
        Mockito.verify(view).loadMatchData(event)
    }

    @Test
    fun testLoadImage(){
        val homeName = "A"
        val awayName = "B"

        val teams1 : MutableList<Team> = mutableListOf()
        val team1 = Team("id1","A","badge")
        teams1.add(team1)
        val response1 = TeamResponse(teams1)

        val teams2 : MutableList<Team> = mutableListOf()
        val team2 = Team("id2","B","badge")
        teams2.add(team2)
        val response2 = TeamResponse(teams2)


        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(SportApiDB.getTeam(homeName)),
                TeamResponse::class.java
        )).thenReturn(response1)

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(SportApiDB.getTeam(awayName)),
                TeamResponse::class.java
        )).thenReturn(response2)

        presenter.loadImage(homeName,awayName)
        Mockito.verify(view).loadImage(anyString(), anyString())
    }

    @Test
    fun testAddToFavorite(){
        val event = Event(0, "","","",0,0,"",0,"","","","","","","",0,"","","","","","","")
        presenter.addToFavorite(event)

        Mockito.verify(view).showSnackbar(anyString())
    }

    @Test
    fun testAddToFavoriteException(){
        val event = Event(0, "","","",0,0,"",0,"","","","","","","",0,"","","","","","","")
        Mockito.`when`(database.insertEventFavorites(event))
                .thenThrow(SQLiteConstraintException())

        presenter.addToFavorite(event)
        Mockito.verify(view).showSnackbar(anyString())
    }

    @Test
    fun testRemoveFromFavorite(){
        val id = "ID"
        presenter.removeFromFavorite(id)
        Mockito.verify(view).showSnackbar(anyString())
    }

    @Test
    fun testRemoveFromFavoriteException(){
        val id = "ID"
        Mockito.`when`(database.deleteEventFavorites(id))
                .thenThrow(SQLiteConstraintException())
        presenter.removeFromFavorite(id)
        Mockito.verify(view).showSnackbar(anyString())
    }

    @Test
    fun testGetEventFavoriteById(){
        val events: MutableList<Event> = mutableListOf()

        Mockito.`when`(database.getEventFavoriteById(anyString()))
                .thenReturn(events)

        val actual = presenter.getEventFavoriteById(anyString())
        assertEquals(events,actual)
    }
}
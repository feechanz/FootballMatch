package com.feechan.footballmatch.presenter.favorites

import com.feechan.footballmatch.data.network.response.Event
import com.feechan.footballmatch.data.sqllite.SportOpenHelper
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FavoritesPresenterTest {
    @Mock
    private
    lateinit var view: FavoritesContract.View

    @Mock
    private
    lateinit var database: SportOpenHelper


    private lateinit var presenter: FavoritesPresenter
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = FavoritesPresenter(view,database)
    }

    @Test
    fun testLoadData(){
        var favorites: List<Event> = mutableListOf<Event>()
        Mockito.`when`(database.getFavorites()).thenReturn(favorites)
        presenter.loadData()
        Mockito.verify(view).hideLoadingBar()
        Mockito.verify(view).loadMatchData(favorites)
    }
}
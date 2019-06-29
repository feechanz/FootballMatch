package com.feechan.footballmatch.data

import com.feechan.footballmatch.BuildConfig
import junit.framework.Assert.assertEquals
import org.junit.Test

class SportApiDBTest {
    @Test
    fun testGetEventDetail() {
        val id = "123"
        val url = BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/lookupevent.php?id=${id}"
        assertEquals(url,SportApiDB.getEventDetail(id))
    }

    @Test
    fun testGet15EventPast(){
        val id = "123"
        val url = BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/eventspastleague.php?id=${id}"
        assertEquals(url,SportApiDB.get15EventPast(id))
    }

    @Test
    fun testGet15EventNext(){
        val id = "123"
        val url = BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/eventsnextleague.php?id=${id}"
        assertEquals(url,SportApiDB.get15EventNext(id))
    }

    @Test
    fun testGetTeams(){
        val league = "league"
        val url = BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/search_all_teams.php?l=${league}"
        assertEquals(url,SportApiDB.getTeams(league))
    }

    @Test
    fun testGetTeam(){
        val teamname = "teamname"
        val url = BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/searchteams.php?t=${teamname}"
        assertEquals(url,SportApiDB.getTeam(teamname))
    }
}
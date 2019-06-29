package com.feechan.footballmatch.data

import android.net.Uri
import android.util.Log
import com.feechan.footballmatch.BuildConfig

object SportApiDB{
    fun getTeams(league: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l=" + league
//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//                .appendPath("api")
//                .appendPath("v1")
//                .appendPath("json")
//                .appendPath(BuildConfig.TSDB_API_KEY)
//                .appendPath("search_all_teams.php")
//                .appendQueryParameter("l",league)
//                .toString()
    }

    fun get15EventPast(id: String): String{
        return BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/eventspastleague.php?id="+id
//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//                .appendPath("api")
//                .appendPath("v1")
//                .appendPath("json")
//                .appendPath(BuildConfig.TSDB_API_KEY)
//                .appendPath("eventspastleague.php")
//                .appendQueryParameter("id","4328")
//                .toString()
    }

    fun get15EventNext(id: String): String{
        return BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/eventsnextleague.php?id="+id
//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//                .appendPath("api")
//                .appendPath("v1")
//                .appendPath("json")
//                .appendPath(BuildConfig.TSDB_API_KEY)
//                .appendPath("eventsnextleague.php")
//                .appendQueryParameter("id","4328")
//                .toString()
    }

    fun getEventDetail(eventId: String) : String{
        Log.d("chanz","Load lookupevent.php id = "+ eventId)
        return BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/lookupevent.php?id="+eventId
//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//                .appendPath("api")
//                .appendPath("v1")
//                .appendPath("json")
//                .appendPath(BuildConfig.TSDB_API_KEY)
//                .appendPath("lookupevent.php")
//                .appendQueryParameter("id",eventId)
//                .toString()
    }

    fun getTeam(teamname: String) : String{
        return BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/searchteams.php?t="+teamname
//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//                .appendPath("api")
//                .appendPath("v1")
//                .appendPath("json")
//                .appendPath(BuildConfig.TSDB_API_KEY)
//                .appendPath("searchteams.php")
//                .appendQueryParameter("t",teamname)
//                .toString()
    }
}
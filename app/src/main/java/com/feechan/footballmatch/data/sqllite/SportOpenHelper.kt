package com.feechan.footballmatch.data.sqllite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.feechan.footballmatch.data.network.response.Event
import com.feechan.footballmatch.utils.database
import org.jetbrains.anko.db.*
import java.util.*

class SportOpenHelper (ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteEvent.db", null, 1) {
    companion object {
        private var instance: SportOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): SportOpenHelper {
            if (instance == null) {
                instance = SportOpenHelper(ctx.applicationContext)
            }
            return instance as SportOpenHelper
        }
    }

    fun getFavorites(): List<Event>{
        var favorites: List<Event> = mutableListOf<Event>()
        this.use {
            val result = select(Event.TABLE_FAVORITE)
             favorites = result.parseList(classParser<Event>())

             favorites
        }
        return favorites
    }

    fun insertEventFavorites(event: Event){
        this.use {
            insert(Event.TABLE_FAVORITE,
                    Event.EVENT_ID to event.idEvent,
                    Event.HOME_TEAM_NAME to Objects.toString(event.homeTeamName,""),
                    Event.AWAY_TEAM_NAME to Objects.toString(event.awayTeamName,""),
                    Event.HOME_SCORE to event.homeTeamScore,
                    Event.AWAY_SCORE to event.awayTeamScore,
                    Event.DATE_EVENT to  Objects.toString(event.dateEvent,""),
                    Event.HOME_SHOTS to event.homeShots,
                    Event.HOME_GOALS_DETAILS to  Objects.toString(event.homeGoalDetails,""),
                    Event.HOME_KEEPER_LINE to  Objects.toString(event.goalKeeperLineHome,""),
                    Event.HOME_DEFENSE_LINE to  Objects.toString(event.defenseLineHome,""),
                    Event.HOME_MIDFIELD_LINE to  Objects.toString(event.midfieldLineHome,""),
                    Event.HOME_FORWARD_LINE to  Objects.toString(event.forwardLineHome,""),
                    Event.HOME_SUBSTITUTES_LINE to  Objects.toString(event.SubstitutesLineHome,""),
                    Event.HOME_FORMATION to   Objects.toString(event.homeFormation,""),
                    Event.AWAY_SHOTS to  event.awayShots,
                    Event.AWAY_GOALS_DETAILS to  Objects.toString(event.awayGoalDetails,""),
                    Event.AWAY_KEEPER_LINE to  Objects.toString(event.goalKeeperLineAway,""),
                    Event.AWAY_DEFENSE_LINE to  Objects.toString(event.defenseLineAway,""),
                    Event.AWAY_MIDFIELD_LINE to  Objects.toString(event.midfieldLineAway,""),
                    Event.AWAY_FORWARD_LINE to  Objects.toString(event.forwardLineAway,""),
                    Event.AWAY_SUBSTITUTES_LINE to  Objects.toString(event.SubstitutesLineAway,""),
                    Event.AWAY_FORMATION to  Objects.toString(event.AwayFormation,""))
        }
    }

    fun deleteEventFavorites(eventId: String){
        this.use {
            delete(Event.TABLE_FAVORITE, "(idEvent = {id})",
                    "id" to eventId)
        }
    }

    fun getEventFavoriteById(eventId: String): List<Event> {
        var favorite: List<Event> = mutableListOf()
        this.use {
            val result = select(Event.TABLE_FAVORITE)
                    .whereArgs("(idEvent = {id})",
                            "id" to eventId)

            val res = result.parseList(classParser<Event>())
            favorite = res
        }
        return favorite
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(Event.TABLE_FAVORITE, true,
                Event.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Event.EVENT_ID to TEXT + UNIQUE,
                Event.HOME_TEAM_NAME to TEXT,
                Event.AWAY_TEAM_NAME to TEXT,
                Event.HOME_SCORE to INTEGER,
                Event.AWAY_SCORE to INTEGER,
                Event.DATE_EVENT to TEXT,
                Event.HOME_SHOTS to INTEGER,
                Event.HOME_GOALS_DETAILS to TEXT,
                Event.HOME_KEEPER_LINE to TEXT,
                Event.HOME_DEFENSE_LINE to TEXT,
                Event.HOME_MIDFIELD_LINE to TEXT,
                Event.HOME_FORWARD_LINE to TEXT,
                Event.HOME_SUBSTITUTES_LINE to TEXT,
                Event.HOME_FORMATION to TEXT,
                Event.AWAY_SHOTS to INTEGER,
                Event.AWAY_GOALS_DETAILS to TEXT,
                Event.AWAY_KEEPER_LINE to TEXT,
                Event.AWAY_DEFENSE_LINE to TEXT,
                Event.AWAY_MIDFIELD_LINE to TEXT,
                Event.AWAY_FORWARD_LINE to TEXT,
                Event.AWAY_SUBSTITUTES_LINE to TEXT,
                Event.AWAY_FORMATION to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Event.TABLE_FAVORITE, true)
    }
}
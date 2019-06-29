package com.feechan.footballmatch.data.network.response

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("idTeam")
        var name: String? = null,

        @SerializedName("strTeam")
        var teamName: String? = null,

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null
)
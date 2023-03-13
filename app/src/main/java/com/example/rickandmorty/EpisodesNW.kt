package com.example.rickandmorty

import com.google.gson.annotations.SerializedName
data class EpisodesResponse(
    @SerializedName("results")
    val episodes: List<EpisodesNW>
)
data class EpisodesNW(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val air_date: String, // записана в формате "YYYY-MM-DD"
    @SerializedName("episode")
    val episode: String,
    @SerializedName("characters")
    val characters: List<String>, // список URL персонажей, связанных с этим эпизодом
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String // дата и время создания записи на сервере, связанной с этим эпизодом, в формате "YYYY-MM-DDTHH:MM:SS.SSSZ"
)
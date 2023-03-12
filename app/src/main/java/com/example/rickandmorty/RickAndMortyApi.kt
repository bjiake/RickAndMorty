package com.example.rickandmorty

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RickAndMortyApi {
    companion object{
        fun createAPI(): RickAndMortyApi {
            val retrofitBuilder = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofitBuilder.create(RickAndMortyApi::class.java)
        }
    }

    @GET("character")
    fun getAllCharacters() : Call<CharacterResponse>
}
package com.example.rickandmorty

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.databinding.ActivityEpisodesBinding
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class EpisodesActivity: AppCompatActivity() {
    private lateinit var binding: ActivityEpisodesBinding
    private var episodesAPI = EpisodesApi.createAPI()
    private lateinit var character: String
    private val episodesAdapter = EpisodesAdapter()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())

        character = intent.getStringExtra("character").toString()
        Log.d("aaaIntent","get:$character")

        recyclerViewInit()

        if(EpisodeObject.episodeObject.isEmpty()){
            loadEpisodes()
        } else{
            episodesAdapter.submitList(EpisodeObject.episodeObject)
            Log.d("aaaIntent","Забираю")
        }
    }

    private fun loadEpisodes()  {
        episodesAPI.getEpisodesByCharacter(character).enqueue(object : Callback<EpisodesResponse> {
            override fun onResponse(
                call: Call<EpisodesResponse>,
                response: Response<EpisodesResponse>
            ) {
                if (response.isSuccessful) {
                    val episodes = response.body()?.episodes
                    EpisodeObject.episodeObject = episodes ?: emptyList()
                    episodesAdapter.submitList(EpisodeObject.episodeObject)
                    Log.d("aaaIntent", "Загружаю с сервера")
                } else {
                    Log.d("EpisodesActivity", "Response error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<EpisodesResponse>, t: Throwable) {
                Log.d("EpisodesActivity", "Error: ${t.message}")
            }
        })
    }

    private fun recyclerViewInit() {
        binding.rvEpisodes.adapter = episodesAdapter
        binding.rvEpisodes.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
    }
}

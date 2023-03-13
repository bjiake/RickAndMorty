package com.example.rickandmorty

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.databinding.ActivityEpisodesBinding
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


        loadEpisodes()
    }

    private fun loadEpisodes()  {
        episodesAPI.getEpisodesByCharacter().enqueue(object : Callback<EpisodesResponse> {
            override fun onResponse(call: Call<EpisodesResponse>, response: Response<EpisodesResponse>) {
                if (response.isSuccessful) {
                    var newEpisodes = emptyList<EpisodesNW>()

                    response.body()?.episodes?.forEach {
                        for (item in it.characters) {
                            if (item == character) {
                                newEpisodes = newEpisodes + it
                            }
                        }
                    }
                    EpisodeObject.episodeObject = newEpisodes
                    episodesAdapter.submitList(EpisodeObject.episodeObject)
                    Log.d("aaaIntent", "${EpisodeObject.episodeObject}")
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

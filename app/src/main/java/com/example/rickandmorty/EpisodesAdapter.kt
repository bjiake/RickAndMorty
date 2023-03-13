package com.example.rickandmorty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class EpisodesAdapter: ListAdapter<EpisodesNW, RecyclerView.ViewHolder>(EpisodeDiffCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_episodes_holder, parent, false)
        return EpisodesHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as EpisodesHolder).bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}

class EpisodesHolder(private val view: View) :
        RecyclerView.ViewHolder(view.rootView) {
        fun bind(episode: EpisodesNW) {
            view.findViewById<TextView>(R.id.tvName).text = episode.name
            view.findViewById<TextView>(R.id.tvAirDate).text = episode.air_date
        }
}

class EpisodeDiffCallBack: DiffUtil.ItemCallback<EpisodesNW>() {
    override fun areItemsTheSame(
        oldItem: EpisodesNW,
        newItem: EpisodesNW
    ): Boolean = oldItem == newItem
    override fun areContentsTheSame(
        oldItem: EpisodesNW,
        newItem: EpisodesNW
    ): Boolean = oldItem == newItem
}

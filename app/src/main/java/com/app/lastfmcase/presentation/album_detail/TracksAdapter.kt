package com.app.lastfmcase.presentation.album_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.lastfmcase.databinding.TrackItemBinding
import com.app.lastfmcase.domain.model.TrackItem

class TracksAdapter(private val tracks: List<TrackItem>) :
    RecyclerView.Adapter<TracksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracksAdapter.ViewHolder {
        val binding =
            TrackItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TracksAdapter.ViewHolder, position: Int) {
        val track = tracks[position]
        track?.let {
            holder.bind(track)
        }
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    inner class ViewHolder(private val binding: TrackItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(track: TrackItem) {
            with(binding) {
                track?.let { trackItem ->
                    trackItem?.let {
                        tvTrackName.text = it.name
                        tvTrackUrl.text = it.url
                    }
                }
            }
        }
    }
}
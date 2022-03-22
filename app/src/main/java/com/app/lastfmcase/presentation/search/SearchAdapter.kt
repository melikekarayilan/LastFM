package com.app.lastfmcase.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.lastfmcase.R
import com.app.lastfmcase.databinding.SearchItemBinding
import com.app.lastfmcase.domain.model.ArtistItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SearchAdapter(
    private val artistList: List<ArtistItem>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artist = artistList[position]
        artist?.let {
            holder.bind(artist, listener)
        }
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    inner class ViewHolder(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(artist: ArtistItem, listener: OnItemClickListener) {
            with(binding) {
                artist?.let {
                    tvArtistName.text = artist.name
                    it.image[2]?.let { it ->
                        Glide.with(binding.root.context)
                            .load(it.text)
                            .apply(RequestOptions().placeholder(R.drawable.ic_baseline_library_music_24))
                            .into(binding.imgArtist)
                    }
                }
            }
            itemView.setOnClickListener {
                if (listener != null) {
                    if (layoutPosition != RecyclerView.NO_POSITION) {
                        listener.onArtistItemClick(itemView, layoutPosition, artist)
                    }
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onArtistItemClick(
            itemView: View,
            position: Int,
            artist: ArtistItem
        )
    }
}
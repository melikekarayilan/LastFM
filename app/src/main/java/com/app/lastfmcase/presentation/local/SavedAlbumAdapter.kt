package com.app.lastfmcase.presentation.local

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.lastfmcase.R
import com.app.lastfmcase.databinding.SavedAlbumItemBinding
import com.app.lastfmcase.domain.model.local.SavedAlbum
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SavedAlbumAdapter(
    private val albumList: List<SavedAlbum>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<SavedAlbumAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SavedAlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = albumList[position]
        album?.let {
            holder.bind(album, listener)
        }
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    inner class ViewHolder(private val binding: SavedAlbumItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: SavedAlbum, listener: OnItemClickListener) {
            with(binding) {
                album?.let {
                    tvLocalAlbumName.text = album.albumName
                    tvArtistName.text = album.artistName
                    it.img?.let { it ->
                        Glide.with(binding.root.context)
                            .load(it)
                            .apply(RequestOptions().placeholder(R.drawable.ic_baseline_home_24))
                            .into(binding.imgLocalAlbum)
                    }
                }
            }
            itemView.setOnClickListener {
                if (listener != null) {
                    if (layoutPosition != RecyclerView.NO_POSITION) {
                        listener.onArtistItemClick(itemView, layoutPosition, album)
                    }
                }
            }

            binding.imgDeleteAlbum.setOnClickListener {
                listener.onDeleteAlbumClick(album)
            }
        }
    }

    interface OnItemClickListener {
        fun onArtistItemClick(
            itemView: View,
            position: Int,
            album: SavedAlbum
        )

        fun onDeleteAlbumClick(
            album: SavedAlbum
        )
    }
}
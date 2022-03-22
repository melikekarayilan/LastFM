package com.app.lastfmcase.presentation.album

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.lastfmcase.R
import com.app.lastfmcase.databinding.TopAlbumItemBinding
import com.app.lastfmcase.domain.model.AlbumItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TopAlbumAdapter(
    private val albumList: List<AlbumItem>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<TopAlbumAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            TopAlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class ViewHolder(private val binding: TopAlbumItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: AlbumItem, listener: OnItemClickListener) {
            with(binding) {
                album?.let {
                    tvAlbumName.text = album.name
                    it.image[2]?.let { it ->
                        Glide.with(binding.root.context)
                            .load(it.text)
                            .apply(RequestOptions().placeholder(R.drawable.ic_baseline_library_music_24))
                            .into(binding.imgAlbum)
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

            binding.saveAlbum.setOnClickListener {
                listener.onSaveAlbumClick(album)
            }
        }
    }

    interface OnItemClickListener {
        fun onArtistItemClick(
            itemView: View,
            position: Int,
            album: AlbumItem
        )

        fun onSaveAlbumClick(
            album: AlbumItem
        )
    }
}
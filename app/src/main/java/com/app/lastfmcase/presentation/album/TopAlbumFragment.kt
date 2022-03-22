package com.app.lastfmcase.presentation.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.lastfmcase.R
import com.app.lastfmcase.databinding.TopAlbumFragmentBinding
import com.app.lastfmcase.domain.model.AlbumItem
import com.app.lastfmcase.presentation.album_detail.AlbumDetailViewModel
import com.app.lastfmcase.presentation.base.BaseFragment
import com.app.lastfmcase.presentation.local.SavedAlbumViewModel
import com.app.lastfmcase.presentation.search.SearchFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopAlbumFragment : BaseFragment(), TopAlbumAdapter.OnItemClickListener {

    private lateinit var binding: TopAlbumFragmentBinding
    private val args: TopAlbumFragmentArgs by navArgs()
    private val topAlbumViewModel: TopAlbumViewModel by viewModels()
    private val albumDetailViewModel: AlbumDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TopAlbumFragmentBinding.inflate(inflater, container, false)

        args?.artistName?.let {
            if (checkForInternet(this.requireContext())) topAlbumViewModel.getTopAlbum(it)
        }
        topAlbumViewModel.error.observe(viewLifecycleOwner) {
            it?.let {

            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeTopAlbums()
    }

    private fun observeTopAlbums() {
        topAlbumViewModel.topAlbums.observe(viewLifecycleOwner) { topAlbums ->
            topAlbums?.albums.let { albums ->
                albums?.album.let { album ->
                    album?.let {
                        binding.rvTopAlbums.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        binding.rvTopAlbums.adapter = TopAlbumAdapter(it, this)
                    }
                }
            }
        }
    }

    override fun onArtistItemClick(itemView: View, position: Int, album: AlbumItem) {
        val action =
            TopAlbumFragmentDirections.actionTopAlbumFragmentToAlbumDetailFragment(
                album.artistOfAlbum.name,
                album.name,
            )
        navigate(action)
    }

    /**
     * When save the album into db, meanwhile albumdetail data should be saved
     * for this reason getAlbumDetail is called.
     */

    override fun onSaveAlbumClick(album: AlbumItem) {
        if (checkForInternet(this.requireContext())) {
            albumDetailViewModel.getAlbumDetail(
                album = album.name,
                artist = album.artistOfAlbum.name
            )
            topAlbumViewModel.saveAlbum(album)
            observeAlbumDetail()
        }
    }

    private fun observeAlbumDetail() {
        albumDetailViewModel.albumDetail.observe(viewLifecycleOwner) { albumDetail ->
            albumDetail?.let {
                topAlbumViewModel.saveAlbumDetail(it)
            }
        }
    }
}
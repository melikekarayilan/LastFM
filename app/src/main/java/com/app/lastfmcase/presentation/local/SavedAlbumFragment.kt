package com.app.lastfmcase.presentation.local

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.lastfmcase.databinding.SavedAlbumFragmentBinding
import com.app.lastfmcase.domain.model.local.SavedAlbum
import com.app.lastfmcase.presentation.album.TopAlbumAdapter
import com.app.lastfmcase.presentation.album.TopAlbumFragmentDirections
import com.app.lastfmcase.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedAlbumFragment : BaseFragment(), SavedAlbumAdapter.OnItemClickListener {
    private lateinit var binding: SavedAlbumFragmentBinding
    private val savedAlbumViewModel: SavedAlbumViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SavedAlbumFragmentBinding.inflate(inflater, container, false)
        savedAlbumViewModel.getSavedAlbums()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSavedAlbums()
    }

    private fun observeSavedAlbums() {
        savedAlbumViewModel.savedAlbums.observe(viewLifecycleOwner) { savedAlbums ->
            savedAlbums?.let {
                binding.rvSavedAlbum.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvSavedAlbum.adapter = SavedAlbumAdapter(it, this)
            }

        }
    }

    override fun onArtistItemClick(itemView: View, position: Int, album: SavedAlbum) {
        val action = SavedAlbumFragmentDirections.actionLocalAlbumFragmentToAlbumDetailFragment(
            album.artistName,
            album.albumName
        )
        navigate(action)
    }

    /**
     * When delete the album, meanwhile albumdetail data should be deleted
     * for this reason deleteSavedAlbumDetail is called.
     */
    override fun onDeleteAlbumClick(album: SavedAlbum) {
        savedAlbumViewModel.deleteSavedAlbumDetail(album.albumName)
        savedAlbumViewModel.deleteSavedAlbum(album)
    }

}
package com.app.lastfmcase.presentation.album_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.lastfmcase.R
import com.app.lastfmcase.databinding.AlbumDetailFragmentBinding
import com.app.lastfmcase.presentation.base.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailFragment : BaseFragment() {
    private lateinit var binding: AlbumDetailFragmentBinding
    private val args: AlbumDetailFragmentArgs by navArgs()
    private val albumDetailViewModel: AlbumDetailViewModel by viewModels()


    /**
     * checkForInternet func. checks whether connection is available
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AlbumDetailFragmentBinding.inflate(inflater, container, false)
        args?.let {

            if (!checkForInternet(this.requireContext())) {
                albumDetailViewModel.getSavedAlbumDetail(args.albumName)
            } else albumDetailViewModel.getAlbumDetail(args.albumName, args.artistName)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeSavedAlbumDetail()
        observeAlbumDetail()
    }

    /**
     * The albumdetail data is received from webservice,if internet connection is available
     */
    private fun observeAlbumDetail() {
        albumDetailViewModel.albumDetail.observe(viewLifecycleOwner) { albumDetailResponse ->
            albumDetailResponse?.let { response ->
                response.album?.let { albumDetail ->
                    binding.tvAlbumDetailName.text = albumDetail.name
                    binding.tvAlbumDetailArtistName.text = albumDetail.artist
                    albumDetail.image?.get(3)?.let { it ->
                        Glide.with(binding.root.context)
                            .load(it.text)
                            .apply(RequestOptions().placeholder(R.drawable.ic_baseline_library_music_24))
                            .into(binding.imgAlbumDetail)
                    }
                    albumDetail.tracks?.let { trackItemList ->
                        trackItemList?.track?.let {
                            binding.rvTracks.layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                            binding.rvTracks.adapter = TracksAdapter(it)
                        }
                    }
                }
            }
        }
    }

    /**
     * The albumdetail data is received from webservice,if internet connection is not available and the data is saved before
     */
    private fun observeSavedAlbumDetail() {
        albumDetailViewModel.localAlbumDetail.observe(viewLifecycleOwner) { albumDetailResponse ->
            binding.tvAlbumIsntExist.visibility = View.GONE
            albumDetailResponse?.let { response ->
                binding.tvAlbumDetailName.text = response.albumName
                binding.tvAlbumDetailArtistName.text = response.artist
                response.image?.let { it ->
                    Glide.with(binding.root.context)
                        .load(it)
                        .apply(RequestOptions().placeholder(R.drawable.ic_baseline_library_music_24))
                        .into(binding.imgAlbumDetail)
                }
            }
        }
    }
}
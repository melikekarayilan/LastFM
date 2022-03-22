package com.app.lastfmcase.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.lastfmcase.databinding.SearchFragmentBinding
import com.app.lastfmcase.domain.model.ArtistItem
import com.app.lastfmcase.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment(), SearchAdapter.OnItemClickListener {
    private lateinit var binding: SearchFragmentBinding
    private val searchFragmentViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSearch.setOnClickListener {
            if (checkForInternet(this.requireContext())) {
                binding.edtArtistName.text?.toString()?.trim()?.let {
                    if (it != "") {
                        searchFragmentViewModel.getArtistList(it)
                    }
                }
            }
        }
        observeArtist()
    }

    private fun observeArtist() {
        searchFragmentViewModel.artistList.observe(viewLifecycleOwner) { result ->
            result.artistMatches.let { artistMatches ->
                artistMatches.artist?.let { artist ->
                    binding.rvArtistList.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    binding.rvArtistList.adapter = SearchAdapter(artist.artistItemList, this)
                }
            }
        }
    }

    override fun onArtistItemClick(itemView: View, position: Int, artist: ArtistItem) {
        if (checkForInternet(this.requireContext())) {
            val action =
                SearchFragmentDirections.actionSearchFragmentToTopAlbumFragment(artist.name)
            navigate(action)
        }
    }
}
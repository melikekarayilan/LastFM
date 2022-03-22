package com.app.lastfmcase.domain.use_case.search

import com.app.lastfmcase.data.repository.SearchRepositoryImpl
import com.app.lastfmcase.di.contract.IOResult
import com.app.lastfmcase.di.contract.IUseCase
import com.app.lastfmcase.domain.model.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchArtistUseCase @Inject constructor(private val searchRepository: SearchRepositoryImpl) :
    IUseCase<String, Result> {

    @ExperimentalCoroutinesApi
    override suspend fun execute(input: String): Flow<IOResult<Result>> {
        return searchRepository.searchArtist(input)
    }
}
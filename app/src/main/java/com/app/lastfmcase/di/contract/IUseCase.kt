package com.app.lastfmcase.di.contract

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface IUseCase<in I : Any, out O : Any> {

    @ExperimentalCoroutinesApi
    suspend fun execute(input: I): Flow<IOResult<O>>
}
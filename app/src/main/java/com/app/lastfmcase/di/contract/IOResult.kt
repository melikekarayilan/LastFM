package com.app.lastfmcase.di.contract

/**
 * Manage the result of request
 */
sealed class IOResult<out DTO : Any> {
    data class OnSuccess<out DTO : Any>(val data: DTO) : IOResult<DTO>()
    data class OnError<out DTO : Any>(val message: String, val data: DTO? = null) : IOResult<DTO>()
}
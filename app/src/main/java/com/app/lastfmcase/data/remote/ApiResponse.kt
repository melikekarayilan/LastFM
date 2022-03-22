package com.app.lastfmcase.data.remote

import com.app.lastfmcase.di.contract.IOResult
import retrofit2.Response
import java.lang.Exception

abstract class ApiResponse {

    suspend fun <T : Any> callApi(call: suspend () -> Response<T>): IOResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return IOResult.OnSuccess(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T : Any> error(errorMessage: String): IOResult<T> =
        IOResult.OnError("Api call failed $errorMessage")
}
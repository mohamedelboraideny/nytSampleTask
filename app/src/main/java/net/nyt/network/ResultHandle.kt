package net.nyt.network

import okhttp3.ResponseBody

sealed class ResultHandle<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultHandle<T>()
    data class Error(val exception: ResponseBody?) : ResultHandle<Nothing>()
    data class ErrorEX(val exception: Exception?) : ResultHandle<Nothing>()
}
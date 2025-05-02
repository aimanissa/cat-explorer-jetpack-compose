package com.aimanissa.catexplorer.data.network

sealed class NetworkState<out T> {
    data object Idle : NetworkState<Nothing>()
    data object Loading : NetworkState<Nothing>()
    data class Success<T>(val data: T) : NetworkState<T>()
    data class Error(val message: String) : NetworkState<Nothing>()
}

fun <T> NetworkState<T>.duringState(
    success: (T) -> Unit = {},
    error: (String) -> Unit = {},
    loading: () -> Unit = {},
    idle: () -> Unit = {}
) {
    when (this) {
        is NetworkState.Success -> success(this.data)
        is NetworkState.Error -> error(this.message)
        is NetworkState.Loading -> loading()
        is NetworkState.Idle -> idle()
    }
}
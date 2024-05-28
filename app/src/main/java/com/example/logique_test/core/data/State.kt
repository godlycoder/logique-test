package com.example.logique_test.core.data



sealed class State<T> {
    data class Success<T>(val data: T) : State<T>()
    data class Failed<T>(val error: Exception) : State<T>()
}

inline fun <T> State<T>.onSuccess(action: (T) -> Unit): State<T> {
    if (this is State.Success) action(data)
    return this
}

inline fun <T> State<T>.onFailure(action: (Exception) -> Unit) {
    if (this is State.Failed) action(error)
}

inline fun <T, R> State.Success<T>.to(action: (T) -> R): State<R> {
    return State.Success(action(data))
}

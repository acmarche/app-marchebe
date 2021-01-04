package be.marche.www.api

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()

    //data class Success(val recentVersions: List<T>) : UiState()
    data class Success<T>(val value: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

//https://developer.android.com/jetpack/guide
// A generic class that contains data and status about loading this data.
sealed class Resource<T>(
   val data: T? = null,
   val message: String? = null
) {
   class Success<T>(data: T) : Resource<T>(data)
   class Loading<T>(data: T? = null) : Resource<T>(data)
   class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
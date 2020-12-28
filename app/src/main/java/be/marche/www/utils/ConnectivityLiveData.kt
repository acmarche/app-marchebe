package be.marche.www.utils


import android.app.Application
import android.content.Context
import android.net.*
import androidx.lifecycle.LiveData

class ConnectivityLiveData(val application: Application?) : LiveData<Boolean>() {

    internal val networkRequest: NetworkRequest
    internal val connectivityManager: ConnectivityManager

    init {
        connectivityManager =
            application?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        networkRequest =
            NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build()
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            postValue(true)
        }

        override fun onLost(network: Network) {
            postValue(false)
        }
    }

    @Suppress("DEPRECATION")
    override fun onActive() {
        super.onActive()

        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        postValue(activeNetwork?.isConnected == true)

        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}

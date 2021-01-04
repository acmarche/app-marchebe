package be.marche.www.ui.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientContext

@Composable
fun AlertMessage(message: String) {
    val context = AmbientContext.current
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}
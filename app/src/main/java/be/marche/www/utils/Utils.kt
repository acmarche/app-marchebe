package be.marche.www.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun Context.makePhoneCall(number: String): Boolean {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        startActivity(intent)
        return true
    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }
}

fun makePhoneCall2(context: Context, number: String): Boolean {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        context.startActivity(intent)
        return true
    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }
}

fun makeOpenUrl(context: Context, url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(browserIntent);
}

fun sendEmail(context: Context, recipient: String) {
    val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient));
    try {
        context.startActivity(Intent.createChooser(intent, "Choisissez un client email"))
    } catch (e: Exception) {
        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
    }
}

fun openMapT(context: Context, latitude: String, longitude: String) {
    val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
 //   mapIntent.setPackage("com.google.android.apps.maps")
    context.startActivity(mapIntent)
}
package be.marche.www.ui.components

import android.graphics.Color
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat

@Composable
fun TextHtml(description: String) {
    val textView = rememberTextView()

    val htmlDescription = remember(description) {
        HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    textView.apply {
        lineHeight = 60
        textSize = 18.0F
        setTextColor(Color.parseColor("#FF82786f"))
    }

    AndroidView({ textView }) {
        it.text = htmlDescription
    }

    //essais mise en page
    // style = MaterialTheme.typography.body2,
    //  lineHeight = 20.sp
    /*   val modifier = Modifier.size(20.dp)

       AndroidView(
              modifier = modifier,
           // The viewBlock provides us with the Context so we do not have to pass this down into the @Composable
           // ourself
           viewBlock = { context ->
               // Inside the viewBlock we create a good ol' fashion TextView to match the width and height of its
               // parent
               //   textView.text = htmlDescription

               TextView(context).apply {
                   text = htmlDescription
               }
           })*/
}


@Composable
private fun rememberTextView(): TextView {
    val context = AmbientContext.current
    return remember {
        TextView(context).apply {
            movementMethod = LinkMovementMethod.getInstance()
        }
    }
}
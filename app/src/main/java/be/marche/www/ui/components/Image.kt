package be.marche.www.ui.components

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.marche.www.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun TitleComponent(title: String) {
    // Text is a predefined composable that does exactly what you'd expect it to - display text on
    // the screen. It allows you to customize its appearance using style, fontWeight, fontSize, etc.
    Text(
        title, style = TextStyle(
            fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W900,
            fontSize = 14.sp, color = Color.Black
        ), modifier = Modifier.padding(16.dp).fillMaxWidth()
    )
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun NetworkImageComponentPicasso(
    url: String,
    modifier: Modifier = Modifier
) {
    // Source code inspired from - https://kotlinlang.slack.com/archives/CJLTWPH7S/p1573002081371500.
    // Made some minor changes to the code Leland posted.
    val sizeModifier = modifier.fillMaxWidth().preferredSizeIn(maxHeight = 200.dp)
    var image by remember { mutableStateOf<ImageBitmap?>(null) }
    var drawable by remember { mutableStateOf<Drawable?>(null) }
    onCommit(url) {
        val picasso = Picasso.get()
        val target = object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                // TODO(lmr): we could use the drawable below
                drawable = placeHolderDrawable
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                drawable = errorDrawable
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                image = bitmap?.asImageBitmap()
            }
        }
        picasso
            .load(url)
            .into(target)

        onDispose {
            image = null
            drawable = null
            picasso.cancelRequest(target)
        }
    }

    val theImage = image
    val theDrawable = drawable
    if (theImage != null) {
        // Column is a composable that places its children in a vertical sequence. You
        // can think of it similar to a LinearLayout with the vertical orientation.
        // In addition we also pass a few modifiers to it.

        // You can think of Modifiers as implementations of the decorators pattern that are
        // used to modify the composable that its applied to.
        Column(
            modifier = sizeModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image is a pre-defined composable that lays out and draws a given [ImageBitmap].
            Image(bitmap = theImage)
        }
    } else if (theDrawable != null) {
        Canvas(modifier = sizeModifier) {
            drawIntoCanvas { canvas ->
                theDrawable.draw(canvas.nativeCanvas)
            }
        }
    }
}

@Composable
fun DrawableImage() {
    val imageModifier = Modifier
        .preferredHeightIn(max = 180.dp)
        .padding(16.dp)
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(8.dp))
    val image = imageResource(R.drawable.header)
    Image(image, modifier = imageModifier, contentScale = ContentScale.Crop)
}

@Preview
@Composable
fun DrawableImagePreview() {
    MarcheComposeTheme() {
        DrawableImage()
    }
}

/**
 * Material icon
 */
@Composable
fun IconImage() {
    Image(
        Icons.Filled.Face,  // material icon
        modifier = Modifier.preferredSize(40.dp),
        colorFilter = ColorFilter.tint(Color.Black),
        contentScale = ContentScale.Fit
    )
}
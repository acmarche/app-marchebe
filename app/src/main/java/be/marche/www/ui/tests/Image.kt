package be.marche.www.ui.tests

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.marche.www.R
import be.marche.www.ui.MarcheComposeTheme
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
 * Ex: Icons.Filled.Face // material icon
 */
@Composable
fun IconImage(imageVector: ImageVector= Icons.Filled.Face) {
    Image(
        imageVector,
        modifier = Modifier.preferredSize(40.dp),
        colorFilter = ColorFilter.tint(Color.Black),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun LoadImage(
    imageResId: Int,
    tint: Color? = null,
    opacity: Float = 1.0f
) {
    loadImageResource(imageResId).resource.resource?.let {
        Image(
            it,  // material icon
            modifier = Modifier.preferredSize(40.dp),
            colorFilter = tint?.let { it1 -> ColorFilter.tint(it1) },
            contentScale = ContentScale.Fit,
        )
    }
}

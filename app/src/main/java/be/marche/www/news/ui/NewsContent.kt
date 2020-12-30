package be.marche.www.news.ui

import android.graphics.Color as ColorBase
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import be.marche.www.R
import be.marche.www.model.News
import be.marche.www.news.NewsViewModel
import be.marche.www.ui.components.MarcheComposeTheme
import be.marche.www.ui.components.NetworkImageComponentPicasso
import be.marche.www.ui.components.blue1
import be.marche.www.utils.fakeNews

private val defaultSpacerSize = 16.dp


@Composable
fun NewsShowComponent(newsId: Int, newsViewModel: NewsViewModel) {

    val news by newsViewModel.findById(newsId).observeAsState(initial = null)
    news?.let { PostContent(it) }

}

@Composable
fun PostContent(news: News) {

    TopAppBar(
        title = { Text("Accueil") },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Rounded.Home)
                //Icon(imageResource(id = R.drawable.marche_logo))
            }
        },
    )
    ScrollableColumn(
        modifier = Modifier.padding(horizontal = defaultSpacerSize)
    ) {
            Spacer(Modifier.preferredHeight(defaultSpacerSize))
            PostHeaderImage(news)
            Text(
                text = news.intitule,
                style = MaterialTheme.typography.h2
                )
            Spacer(Modifier.preferredHeight(8.dp))

            news.content?.let { content ->
                Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                    TextHtml(news.content)
                    /*     Text(
                         text = PlantDescription(news.content),
                         style = MaterialTheme.typography.body2,
                         lineHeight = 20.sp
                     )*/
                }
                Spacer(Modifier.preferredHeight(defaultSpacerSize))
            }
            Spacer(Modifier.preferredHeight(24.dp))
            //   PostContents(post.content)
            Spacer(Modifier.preferredHeight(48.dp))
        }
}


@Composable
private fun PostHeaderImage(post: News) {
    post.image?.let { image ->
        val imageModifier = Modifier
            .heightIn(min = 180.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .clip(shape = MaterialTheme.shapes.medium)
        //contentScale = ContentScale.Fit
        //.scale(ContentScale.Crop)
        Image(
            imageResource(id = R.drawable.header),
            modifier = imageModifier
        )
        //Image(image, imageModifier, contentScale = ContentScale.Crop)
        Spacer(Modifier.preferredHeight(defaultSpacerSize))
    }
}

@Composable
private fun PostHeaderImage2(post: News) {
    post.image?.let { image ->
        val imageModifier = Modifier
            .heightIn(min = 180.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .clip(shape = MaterialTheme.shapes.medium)
        //contentScale = ContentScale.Fit
        //.scale(ContentScale.Crop)
        NetworkImageComponentPicasso(
            url = image,
            modifier = imageModifier
        )
        //Image(image, imageModifier, contentScale = ContentScale.Crop)
        Spacer(Modifier.preferredHeight(defaultSpacerSize))
    }
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

@Composable
private fun TextHtml(description: String) {
    val textView = rememberTextView()

    val htmlDescription = remember(description) {
        HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    textView.apply {
        lineHeight = 60
        textSize = 18.0F
        setTextColor(ColorBase.parseColor("#FF82786f"))
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

@Preview
@Composable
private fun PlantDescriptionPreview() {
    MarcheComposeTheme() {
        PostContent(fakeNews())
    }
}
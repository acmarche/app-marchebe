package be.marche.www.news.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.marche.www.R
import be.marche.www.api.UiState
import be.marche.www.model.News
import be.marche.www.news.NewsViewModel
import be.marche.www.ui.components.MarcheComposeTheme
import be.marche.www.ui.components.NetworkImageComponentPicasso
import be.marche.www.ui.components.TextHtml
import be.marche.www.utils.fakeNews
import timber.log.Timber

private val defaultSpacerSize = 16.dp


@Composable
fun NewsShowComponent(
    newsId: Int,
    newsViewModel: NewsViewModel,
    navigateUp: () -> Unit
) {
    val news by newsViewModel.findById(newsId).observeAsState(initial = null)

    val uiState by newsViewModel.uiState().observeAsState(initial = null)
    newsViewModel.performSingleNetworkRequest()

    if (uiState != null) {

        when (uiState) {
            is UiState.Loading -> {
                Text("loadin")
                Timber.w("zeze loading")
            }
            is UiState.Success -> {
                val news = (uiState as UiState.Success).recentVersions

                Text("zeze success")
                Timber.w("zeze success")
            }
            is UiState.Error -> {
                Timber.w("zeze error")
                Text("Error ${(uiState as UiState.Error).message}")
            }
            else -> Text("default")

        }
    }

    news?.let { NewsContent(it, navigateUp) }
}

@Composable
fun NewsContent(
    news: News,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = { },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            Box {
                IconButton(onClick = navigateUp) {
                    Icon(Icons.Filled.ArrowLeft, tint = Color.White)
                    //Icon(imageResource(id = R.drawable.marche_logo))
                }
            }
        },
    )
    ScrollableColumn(
        //  modifier = Modifier.padding(horizontal = defaultSpacerSize),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 0.dp, bottom = 10.dp)
    ) {
        Spacer(Modifier.preferredHeight(defaultSpacerSize))
        PostHeaderImage(news)
        Text(
            text = news.intitule,
            style = MaterialTheme.typography.h2
        )
        Spacer(Modifier.preferredHeight(8.dp))

        Box(modifier = Modifier.padding(start = 5.dp)) {
            news.content?.let { content ->
                Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                    TextHtml(news.content)
                }
            }
        }
        Spacer(Modifier.preferredHeight(12.dp))
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
        Image(
            imageResource(id = R.drawable.header),
            modifier = imageModifier,
            contentScale = ContentScale.Fit
        )
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

@Preview
@Composable
private fun PlantDescriptionPreview() {
    MarcheComposeTheme() {
        NewsContent(fakeNews(), {})
    }
}
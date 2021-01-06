package be.marche.www.news.ui

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import be.marche.www.model.News
import be.marche.www.navigation.Actions
import be.marche.www.news.NewsViewModel
import be.marche.www.ui.MarcheComposeTheme
import be.marche.www.ui.components.NetworkImageComponentPicasso
import be.marche.www.ui.components.TextHtml
import be.marche.www.utils.fakeNews


@Preview
@Composable
private fun PlantDescriptionPreview() {
    MarcheComposeTheme() {
        val navController = rememberNavController()
        NewsScreen.NewsContent(fakeNews(), Actions(navController))
    }
}

object NewsScreen {
    private val defaultSpacerSize = 16.dp

    //todo Collapsing Toolbar en compose ?
    @Composable
    fun ShowComponent(
        newsId: Int,
        newsViewModel: NewsViewModel,
        navigateTo: Actions
    ) {
        val news by newsViewModel.findById(newsId).observeAsState(initial = null)
        news?.let { NewsContent(it, navigateTo) }
    }

    @Composable
    fun NewsContent(
        news: News,
        navigateTo: Actions
    ) {
        TopAppBar(
            title = { },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            navigationIcon = {
                Box {
                    IconButton(onClick = navigateTo.navigateUp) {
                        Icon(Icons.Filled.ArrowLeft, tint = Color.White)
                    }
                }
            },
        )
        ScrollableColumn(
            //  modifier = Modifier.padding(horizontal = defaultSpacerSize),
            contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 0.dp, bottom = 10.dp)
        ) {
            Spacer(Modifier.preferredHeight(defaultSpacerSize))
            NewsHeaderImage(news)
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
    private fun NewsHeaderImage(news: News) {
        news.image?.let { image ->
            val imageModifier = Modifier
                .heightIn(min = 180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))
                .clip(shape = MaterialTheme.shapes.medium)
            /* Image(
             imageResource(id = R.drawable.header),
             modifier = imageModifier,
             contentScale = ContentScale.Fit
         )*/
            NetworkImageComponentPicasso(
                url = image,
                modifier = imageModifier
            )
            Spacer(Modifier.preferredHeight(defaultSpacerSize))
        }
    }

}
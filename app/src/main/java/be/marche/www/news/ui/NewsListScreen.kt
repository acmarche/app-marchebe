package be.marche.www.news.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import be.marche.www.R
import be.marche.www.model.News
import be.marche.www.ui.components.NetworkImageComponentPicasso

@Composable
fun ListNewsScreen(newsListLiveData: LiveData<List<News>>, onItemClick: (Int) -> Unit) {
    val newsList by newsListLiveData.observeAsState(initial = emptyList())
    if (newsList.isEmpty()) {
        LiveDataLoadingComponentListNews()
    } else {
        LiveDataComponentNewsList(newsList, onItemClick)
    }
}

@Composable
fun LiveDataComponentNewsList(newsList: List<News>, onItemClick: (Int) -> Unit) {
    // LazyColumn is a vertically scrolling list that only composes and lays out the currently
    // visible items. This is very similar to what RecyclerView tries to do as it's more optimized
    // than the VerticalScroller.

    val typography = MaterialTheme.typography

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.actus)) },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Rounded.ArrowLeft)
                        //Icon(imageResource(id = R.drawable.marche_logo))
                    }
                },
            )
        },
    ) {
        LazyColumn {
            items(items = newsList, itemContent = { news ->
                // Card composable is a predefined composable that is meant to represent the
                // card surface as specified by the Material Design specification. We also
                // configure it to have rounded corners and apply a modifier.

                // You can think of Modifiers as implementations of the decorators pattern that are used to
                // modify the composable that its applied to. In this example, we assign a padding of
                // 16dp to the Card along with specifying it to occupy the entire available width.
                Card(
                    shape = RoundedCornerShape(4.dp),
                    backgroundColor = Color.White,
                    modifier = Modifier.fillParentMaxWidth().padding(8.dp)
                        .clickable(onClick = { onItemClick(news.id) })
                ) {
                    // ListItem is a predefined composable that is a Material Design implementation of [list
                    // items](https://material.io/components/lists). This component can be used to achieve the
                    // list item templates existing in the spec
                    ListItem(
                        text = {
                            // The Text composable is pre-defined by the Compose UI library; you can use this
                            // composable to render text on the screen
                            Text(
                                text = news.intitule,
                                style = typography.h3
                            )
                        }, secondaryText = {
                            Text(
                                text = news.extrait,
                                style = typography.h4,
                            )
                        }, icon = {
                            news.image?.let {
                                NetworkImageComponentPicasso(
                                    url = it,
                                    modifier = Modifier.preferredWidth(60.dp).preferredHeight(60.dp)
                                )
                            }
                        }
                    )
                }
            })
        }
    }
}

@Composable
fun LiveDataLoadingComponentListNews() {
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation.
    // In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are
    // used to modify the composable that its applied to. In this example, we configure the
    // Column composable to occupy the entire available width and height using
    // Modifier.fillMaxSize().
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // A pre-defined composable that's capable of rendering a circular progress indicator. It
        // honors the Material Design specification.
        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
    }
}


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
import be.marche.www.navigation.Actions
import be.marche.www.ui.components.NetworkImageComponentPicasso

/**
 * https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/state/livedata/LiveDataActivity.kt
 */
object NewsListScreen {

    @Composable
    fun ListComponent(
        newsListLiveData: LiveData<List<News>>,
        navigateTo: Actions
    ) {
        val newsList by newsListLiveData.observeAsState(initial = emptyList())
        if (newsList.isEmpty()) {
            //todo try to fetch if error set to user
            LoadingComponent()
        } else {
            LiveDataComponentNewsList(newsList, navigateTo)
        }
    }

    @Composable
    fun LiveDataComponentNewsList(
        newsList: List<News>,
        navigateTo: Actions
    ) {

        val typography = MaterialTheme.typography

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = R.string.actus)) },
                    navigationIcon = {
                        IconButton(onClick = navigateTo.navigateUp) {
                            Icon(Icons.Rounded.ArrowLeft)
                            //Icon(imageResource(id = R.drawable.marche_logo))
                        }
                    },
                )
            },
        ) {
            LazyColumn {
                items(items = newsList, itemContent = { news ->
                    Card(
                        shape = RoundedCornerShape(4.dp),
                        backgroundColor = Color.White,
                        modifier = Modifier.fillParentMaxWidth().padding(8.dp)
                            .clickable(onClick = { navigateTo.newsShow(news.id) })
                    ) {
                        ListItem(
                            text = {
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
                                        modifier = Modifier.preferredWidth(60.dp)
                                            .preferredHeight(60.dp)
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
    fun LoadingComponent() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
        }
    }
}
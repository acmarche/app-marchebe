package be.marche.www.event.ui

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
import be.marche.www.model.Event
import be.marche.www.navigation.Actions
import be.marche.www.ui.components.NetworkImageComponentPicasso

object EventListScreen {

    @Composable
    fun ListComponent(
        eventListLiveData: LiveData<List<Event>>,
        navigateTo: Actions
    ) {
        val eventList by eventListLiveData.observeAsState(initial = emptyList())
        if (eventList.isEmpty()) {
            LoadingComponent()
        } else {
            LiveDataComponentListEvents(eventList, navigateTo)
        }
    }

    @Composable
    fun LiveDataComponentListEvents(
        eventList: List<Event>,
        navigateTo: Actions
    ) {
        val typography = MaterialTheme.typography

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = R.string.agenda)) },
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
                items(items = eventList, itemContent = { event ->
                    Card(
                        shape = RoundedCornerShape(4.dp),
                        backgroundColor = Color.White,
                        modifier = Modifier.fillParentMaxWidth().padding(8.dp)
                            .clickable(onClick = { navigateTo.eventShow(event.id) })
                    ) {
                        ListItem(text = {
                            Text(
                                text = event.titre,
                                style = typography.h3
                            )
                        }, secondaryText = {
                            Text(
                                text = event.date_debut,
                                style = typography.h4
                            )
                        }, icon = {
                            event.photo?.let { imageUrl ->
                                val newUrl = imageUrl.replace("http", "https")
                                NetworkImageComponentPicasso(
                                    url = newUrl,
                                    modifier = Modifier.preferredWidth(60.dp).preferredHeight(60.dp)
                                )
                            }
                        })
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
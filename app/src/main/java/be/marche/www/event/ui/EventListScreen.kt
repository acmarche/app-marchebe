package be.marche.www.event.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import be.marche.www.R
import be.marche.www.event.EventViewModel
import be.marche.www.model.Event
import be.marche.www.ui.components.NetworkImageComponentPicasso
import be.marche.www.utils.fakeEvents


@Composable
fun ListEventsComponent(
    eventListLiveData: LiveData<List<Event>>, onItemClick: (Int) -> Unit,
    navigateUp: () -> Unit
) {
    // Here we access the live data object and convert it to a form that Jetpack Compose
    // understands using the observeAsState method.

    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of the selectedIndex. Any composable that reads the value of counter will be recomposed
    // any time the value changes. This ensures that only the composables that depend on this
    // will be redraw while the rest remain unchanged. This ensures efficiency and is a
    // performance optimization. It is inspired from existing frameworks like React.
    val eventList by eventListLiveData.observeAsState(initial = emptyList())
    // Since Jetpack Compose uses the declarative way of programming, we can easily decide what
    // needs to shows vs hidden based on which branch of code is being executed. In this example,
    // if the eventList returned by the live data is empty, we want to show a loading indicator,
    // otherwise we want show the appropriate list. So we run the appropriate composable based on
    // the branch of code executed and that takes care of rendering the right views.
    if (eventList.isEmpty()) {
        LiveDataLoadingEventsComponent()
    } else {
        LiveDataComponentListEvents(eventList, onItemClick, navigateUp)
    }
}

@Composable
fun LiveDataComponentListEvents(
    eventList: List<Event>, onItemClick: (Int) -> Unit,
    navigateUp: () -> Unit
) {

    val typography = MaterialTheme.typography

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.agenda)) },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(Icons.Rounded.ArrowLeft)
                        //Icon(imageResource(id = R.drawable.marche_logo))
                    }
                },
            )
        },
    ) {
        LazyColumn {
            items(items = eventList, itemContent = { event ->
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
                        .clickable(onClick = { onItemClick(event.id) })
                ) {
                    // ListItem is a predefined composable that is a Material Design implementation of [list
                    // items](https://material.io/components/lists). This component can be used to achieve the
                    // list item templates existing in the spec
                    ListItem(text = {
                        // The Text composable is pre-defined by the Compose UI library; you can use this
                        // composable to render text on the screen
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
                            // Look at the implementation of this composable in ImageActivity to learn
                            // more about its implementation. It uses Picasso to load the imageUrl passed
                            // to it.
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

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun LiveDataLoadingEventsComponent() {
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

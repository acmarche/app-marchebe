package be.marche.www

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ListItem
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import be.marche.www.event.EventViewModel
import be.marche.www.event.fakeEvents
import be.marche.www.model.Event
import be.marche.www.ui.NetworkImageComponentPicasso
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val eventViewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LiveDataComponent(eventViewModel.allEvents)
        }

    }
}


// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun LiveDataComponent(personListLiveData: LiveData<List<Event>>) {
    // Here we access the live data object and convert it to a form that Jetpack Compose
    // understands using the observeAsState method.

    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of the selectedIndex. Any composable that reads the value of counter will be recomposed
    // any time the value changes. This ensures that only the composables that depend on this
    // will be redraw while the rest remain unchanged. This ensures efficiency and is a
    // performance optimization. It is inspired from existing frameworks like React.
    val personList by personListLiveData.observeAsState(initial = emptyList())
    // Since Jetpack Compose uses the declarative way of programming, we can easily decide what
    // needs to shows vs hidden based on which branch of code is being executed. In this example,
    // if the personList returned by the live data is empty, we want to show a loading indicator,
    // otherwise we want show the appropriate list. So we run the appropriate composable based on
    // the branch of code executed and that takes care of rendering the right views.
    if (personList.isEmpty()) {
        LiveDataLoadingComponent()
    } else {
        LiveDataComponentList(personList)
    }
}


// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun LiveDataComponentList(personList: List<Event>) {
    // LazyColumn is a vertically scrolling list that only composes and lays out the currently
    // visible items. This is very similar to what RecyclerView tries to do as it's more optimized
    // than the VerticalScroller.
    LazyColumn {
        items(items = personList, itemContent = { person ->
            // Card composable is a predefined composable that is meant to represent the
            // card surface as specified by the Material Design specification. We also
            // configure it to have rounded corners and apply a modifier.


                    Timber.w("photo " + person)

            // You can think of Modifiers as implementations of the decorators pattern that are used to
            // modify the composable that its applied to. In this example, we assign a padding of
            // 16dp to the Card along with specifying it to occupy the entire available width.
            Card(
                shape = RoundedCornerShape(4.dp),
                backgroundColor = Color.White,
                modifier = Modifier.fillParentMaxWidth().padding(8.dp)
            ) {
                // ListItem is a predefined composable that is a Material Design implementation of [list
                // items](https://material.io/components/lists). This component can be used to achieve the
                // list item templates existing in the spec
                ListItem(text = {
                    // The Text composable is pre-defined by the Compose UI library; you can use this
                    // composable to render text on the screen
                    Text(
                        text = person.titre,
                        style = TextStyle(
                            fontFamily = FontFamily.Serif, fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }, secondaryText = {
                    androidx.compose.material.Text(
                        text = "Age: ${person.date_debut}",
                        style = TextStyle(
                            fontFamily = FontFamily.Serif, fontSize = 15.sp,
                            fontWeight = FontWeight.Light, color = Color.DarkGray
                        )
                    )
                }, icon = {
                    Timber.w("photo " + person.photo)
                    person.photo?.let { imageUrl ->
                        // Look at the implementation of this composable in ImageActivity to learn
                        // more about its implementation. It uses Picasso to load the imageUrl passed
                        // to it.
                        NetworkImageComponentPicasso(
                            url = imageUrl,
                            modifier = Modifier.preferredWidth(60.dp).preferredHeight(60.dp)
                        )
                    }
                })
            }
        })
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun LiveDataLoadingComponent() {
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

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun LaunchInCompositionComponent(viewModel: EventViewModel) {
    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of whether the checkbox is checked. Any composable that reads the value of "personList"
    // will be recomposed any time the value changes. This ensures that only the composables that
    // depend on this will be redraw while the rest remain unchanged. This ensures efficiency and
    // is a performance optimization. It is inspired from existing frameworks like React.
    val personList = mutableStateListOf<Event>()

    // LaunchedEffect allows you to launch a suspendable function as soon as this composable
    // is first committed i.e this tree node is first allowed to be rendered on the screen. It
    // also takes care of automatically cancelling it when it is no longer in the composition.
    LaunchedEffect(Unit) {
        // This view model merely calls a suspendable function "loadSuperheroes" to get a list of
        // "Person" objects
        val list = viewModel.loadSuperheroes()
        // We add it to our state object
        personList.addAll(list)
    }

    // If the list is empty, it means that our coroutine has not completed yet and we just want
    // to show our loading component and nothing else. So we return early.
    if (personList.isEmpty()) {
        LiveDataLoadingComponent()
        return
    }

    // If the personList is available, we will go ahead and show the list of superheroes. We
    // reuse the same component that we created above to save time & space :)
    LiveDataComponentList(personList)
}

/**
 * Android Studio lets you preview your composable functions within the IDE itself, instead of
 * needing to download the app to an Android device or emulator. This is a fantastic feature as you
 * can preview all your custom components(read composable functions) from the comforts of the IDE.
 * The main restriction is, the composable function must not take any parameters. If your composable
 * function requires a parameter, you can simply wrap your component inside another composable
 * function that doesn't take any parameters and call your composable function with the appropriate
 * params. Also, don't forget to annotate it with @Preview & @Composable annotations.
 */
@Preview
@Composable
fun LiveDataComponentListPreview() {
    LiveDataComponentList(fakeEvents())
}

@Preview
@Composable
fun LiveDataLoadingComponentPreview() {
    LiveDataLoadingComponent()
}
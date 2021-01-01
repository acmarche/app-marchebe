package be.marche.www.bottin.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowLeft
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
import be.marche.bottin.model.Category
import be.marche.bottin.model.Fiche
import be.marche.www.R
import be.marche.www.bottin.CategoryViewModel
import be.marche.www.bottin.FicheViewModel
import be.marche.www.ui.components.NetworkImageComponentPicasso
import be.marche.www.utils.fakeFiches
import timber.log.Timber

@Composable
fun ListFichesScreen(
    categoryId: Int,
    categoryViewModel: CategoryViewModel,
    ficheViewModel: FicheViewModel,
    onItemClick: (Int) -> Unit
) {

    /**
     * get info category
     * if no error
     * get childs
     *
     */
    val category by categoryViewModel.findById(categoryId).observeAsState(initial = null)
    category?.let {

        val fiches by ficheViewModel.findFichesByCategory(categoryId).observeAsState(initial = null)

        fiches.let { fiches ->
            if (fiches != null) {
                fiches.forEach {
                    Timber.w("fiche: " + it)
                }
            }
        }
        CategoryContent(it)

    }


    /*  if (category == null) {
          //show error
      } else {
          val children = categoryViewModel.findChildren(categoryId)
          if (children.count() > 0) {
              //show children
          } else {
              val fiches = ficheViewModel.findByCategory(categoryId)
          }
      }


      val newsList by newsListLiveData.observeAsState(initial = emptyList())
      if (newsList.isEmpty()) {
          LiveDataLoadingComponentListNews()
      } else {
          LiveDataComponentListFiches(newsList, onItemClick)
      }*/
}

@Composable
fun CategoryContent(category: Category) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(category.name) },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Rounded.ArrowLeft)
                        //Icon(imageResource(id = R.drawable.marche_logo))
                    }
                },
            )
        },
    ) {
        Text(category.name)
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun LiveDataComponentListFiches(ficheList: List<Fiche>, onItemClick: (Int) -> Unit) {
    // LazyColumn is a vertically scrolling list that only composes and lays out the currently
    // visible items. This is very similar to what RecyclerView tries to do as it's more optimized
    // than the VerticalScroller.

    val typography = MaterialTheme.typography

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.agenda)) },
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
            items(items = ficheList, itemContent = { fiche ->
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
                        .clickable(onClick = { onItemClick(fiche.id) })
                ) {
                    // ListItem is a predefined composable that is a Material Design implementation of [list
                    // items](https://material.io/components/lists). This component can be used to achieve the
                    // list item templates existing in the spec
                    ListItem(text = {
                        // The Text composable is pre-defined by the Compose UI library; you can use this
                        // composable to render text on the screen
                        Text(
                            text = fiche.societe,
                            style = typography.h3
                        )
                    }, secondaryText = {
                        fiche.rue?.let { it1 ->
                            Text(
                                text = it1,
                                style = typography.h4
                            )
                        }
                    }, icon = {
                        fiche.image?.let { imageUrl ->
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
fun LiveDataLoadingFichesComponent() {
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
fun LaunchInCompositionComponent(viewModel: FicheViewModel) {
    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of whether the checkbox is checked. Any composable that reads the value of "ficheList"
    // will be recomposed any time the value changes. This ensures that only the composables that
    // depend on this will be redraw while the rest remain unchanged. This ensures efficiency and
    // is a performance optimization. It is inspired from existing frameworks like React.
    val ficheList = mutableStateListOf<Fiche>()

    // LaunchedEffect allows you to launch a suspendable function as soon as this composable
    // is first committed i.e this tree node is first allowed to be rendered on the screen. It
    // also takes care of automatically cancelling it when it is no longer in the composition.
    LaunchedEffect(Unit) {
        // This view model merely calls a suspendable function "loadSuperheroes" to get a list of
        // "Person" objects
        val list = viewModel.findAllFiches()
        // We add it to our state object
        ficheList.addAll(list)
    }

    // If the list is empty, it means that our coroutine has not completed yet and we just want
    // to show our loading component and nothing else. So we return early.
    if (ficheList.isEmpty()) {
        LiveDataLoadingFichesComponent()
        return
    }

    // If the ficheList is available, we will go ahead and show the list of superheroes. We
    // reuse the same component that we created above to save time & space :)
    LiveDataComponentListFiches(ficheList, {})
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
    LiveDataComponentListFiches(fakeFiches(), {})
}

@Preview
@Composable
fun LiveDataLoadingComponentPreview() {
    LiveDataLoadingFichesComponent()
}
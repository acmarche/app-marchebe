package be.marche.www.news.ui

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import be.marche.www.R
import be.marche.www.model.News
import be.marche.www.ui.NetworkImageComponentPicasso

@Composable
fun NewsShow(news: News) {
    // Consider negative values to mean 'cut corner' and positive values to mean 'round corner'
    val fabShape = RoundedCornerShape(50)

    // Scaffold is a pre-defined composable that implements the basic material design visual
    // layout structure. It takes in child composables for all the common elements that you see
    // in an app using material design - app bar, bottom app bar, floating action button, etc. It
    // also takes care of laying out these child composables in the correct positions - eg bottom
    // app bar is automatically placed at the bottom of the screen even though I didn't specify
    // that explicitly.
    Scaffold(
        topBar = { TopAppBar(title = { Text(news.intitule) }) },
        bottomBar = {
            // We specify the shape of the FAB bu passing a shape composable (fabShape) as a
            // parameter to cutoutShape property of the BottomAppBar. It automatically creates a
            // cutout in the BottomAppBar based on the shape of the Floating Action Button.
            BottomAppBar(cutoutShape = fabShape) {}
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                // We specify the same shape that we passed as the cutoutShape above.
                shape = fabShape,
                // We use the secondary color from the current theme. It uses the defaults when
                // you don't specify a theme (this example doesn't specify a theme either hence
                // it will just use defaults. Look at DarkModeActivity if you want to see an
                // example of using themes.
                backgroundColor = MaterialTheme.colors.secondary
            ) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Home)
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bodyContent = { padding ->
            // ScrollableColumn is a composable that adds the ability to scroll through the
            // child views
            ScrollableColumn {
                // Column is a composable that places its children in a vertical sequence. You
                // can think of it similar to a LinearLayout with the vertical orientation.
                Column(Modifier.padding(padding)) {
                    NewsCard(news)
                }
            }
        }
    )
}

@Composable
fun NewsCard(news: News) {

    val typography = MaterialTheme.typography
    val image = imageResource(R.drawable.header)

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        val imageModifier = Modifier
            .preferredHeight(180.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(4.dp))

        news.image.let { imageUrl ->
            // Look at the implementation of this composable in ImageActivity to learn
            // more about its implementation. It uses Picasso to load the imageUrl passed
            // to it.
            NetworkImageComponentPicasso(
                url = imageUrl,
                //modifier = Modifier.preferredWidth(60.dp).preferredHeight(60.dp)
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(Modifier.preferredHeight(16.dp))

        Text(
            news.content,
            style = typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

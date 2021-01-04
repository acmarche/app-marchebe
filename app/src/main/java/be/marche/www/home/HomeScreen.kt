package be.marche.www.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Streetview
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import be.marche.www.R
import be.marche.www.bottin.model.Bottin
import be.marche.www.ui.components.BottomBar
import be.marche.www.ui.components.BottomHome
import be.marche.www.ui.components.MaterialColors


@Composable
fun HomeScreenWithDrawer(
    listNews: () -> Unit,
    listEvents: () -> Unit,
    listFiches: (Int) -> Unit,
    navigateUp: () -> Unit,
//    scaffoldState: ScaffoldState = remember { ScaffoldState() }
) {
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(DrawerValue.Closed)
    )

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            Column() {
                IconButton(onClick = { scaffoldState.drawerState.open() }) {
                    Icon(Icons.Filled.Menu)
                }
                // The actions should be at the end of the BottomAppBar
                Spacer(Modifier.weight(1f, true))
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Streetview)
                }
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Favorite)
                }
            }
        },
        bodyContent = {
            HomeContent(listNews = listNews, listEvents = listEvents, listFiches = listFiches)
            //  Content(scaffoldState = scaffoldState)
        },
        //  floatingActionButton = { Fab() },
        bottomBar = { BottomBar(scaffoldState) }
    )
}

@Composable
fun HomeScreen(
    listNews: () -> Unit,
    listEvents: () -> Unit,
    listFiches: (Int) -> Unit,
    navigateUp: () -> Unit
) {
    Surface(
        elevation = 10.dp,
        shape = RectangleShape
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Accueil") },
                    navigationIcon = {
                        IconButton(onClick = navigateUp) {
                            Icon(Icons.Rounded.Home)
                            //Icon(imageResource(id = R.drawable.marche_logo))
                        }
                    },
                )
            },
            bottomBar = {
                BottomHome()
            }
        )
        {
            HomeContent(listNews = listNews, listEvents = listEvents, listFiches = listFiches)
        }
    }
}

@Composable
fun HomeContent(
    listNews: () -> Unit,
    listEvents: () -> Unit,
    listFiches: (Int) -> Unit
) {

    ScrollableColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val image = loadImageResource(R.drawable.gout_vivre)

        image.resource.resource?.let {
            Image(
                bitmap = it,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Divider(Modifier.padding(vertical = 5.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        )
        {
            RoundedIconButton(
                R.drawable.accueil,
                listNews,
                stringResource(id = R.string.contact)
            )
            RoundedIconButton(
                R.drawable.actus,
                listNews,
                stringResource(id = R.string.actus)
            )
            RoundedIconButton(
                R.drawable.agenda,
                listEvents,
                stringResource(id = R.string.agenda)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            RoundedIconButton(
                R.drawable.services_communaux,
                { listFiches(Bottin.SERVICES_COMMUNAUX) },
                stringResource(id = R.string.services_communaux)
            )
            RoundedIconButton(
                R.drawable.enfance,
                listNews,
                stringResource(id = R.string.enfance)
            )
            RoundedIconButton(
                R.drawable.loisirs,
                listNews,
                stringResource(id = R.string.loisirs)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            RoundedIconButton(
                R.drawable.commerces,
                { listFiches(Bottin.COMMERCES) },
                stringResource(id = R.string.commerce)
            )
            RoundedIconButton(
                R.drawable.fetes,
                listNews,
                stringResource(id = R.string.marchefete)
            )
            RoundedIconButton(
                R.drawable.jour_marche,
                listNews,
                stringResource(id = R.string.jour_marche)
            )
        }
    }
}

@Composable
fun RoundedIconButton(
    imageId: Int,
    onClick: () -> Unit,
    text: String,
    background: Color = Color.Red
) {
    Column(
        modifier = Modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val modifier = Modifier
            // .preferredSizeIn(minHeight = 120.dp, maxWidth = 130.dp)
            //   .clickable(onClick = onClick)
            .height(90.dp)
            .width(90.dp)

        val image = loadImageResource(imageId)
        image.resource.resource?.let {
            Image(
                bitmap = it,
                modifier = modifier
            )
        }
        Text(
            modifier = Modifier.width(90.dp),
            style = MaterialTheme.typography.body2,
            color = Color.Gray,
            text = text,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ImageHome(imageId: Int, onClick: () -> Unit) {

    val image = loadImageResource(imageId)
    val modifier = Modifier
        .clickable(onClick = onClick)
        .height(120.dp)
        .width(120.dp)

    image.resource.resource?.let {
        Image(
            bitmap = it,
            modifier = modifier
        )
    }
}

@Composable
fun TestIconAndroid() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.preferredSize(100.dp).clip(CircleShape)
                .background(MaterialColors.secondary)
        ) {
            Image(
                Icons.Filled.ContactPhone,  // material icon
                modifier = Modifier.preferredSize(100.dp),
                colorFilter = ColorFilter.tint(Color.White),
                //contentScale = ContentScale.Fit
            )
        }

        Text(
            //  modifier = Modifier.padding(top = 2.dp),
            style = MaterialTheme.typography.body2,
            color = Color.Gray,
            text = "Coucou"
        )
    }
}

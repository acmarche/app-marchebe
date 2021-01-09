package be.marche.www.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Streetview
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import be.marche.www.R
import be.marche.www.bottin.model.Bottin
import be.marche.www.navigation.Actions
import be.marche.www.sync.SyncViewModel
import be.marche.www.ui.components.BottomBar

class HomeScreen {

    @Composable
    fun HomeComponent(
        navigateTo: Actions
    ) {
        Surface(
            elevation = 10.dp,
            shape = RectangleShape
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(R.string.welcome)) },
                        navigationIcon = {
                            IconButton(onClick = {}) {
                                Icon(Icons.Rounded.Home)
                            }
                        },
                    )
                },
                bottomBar = {
                    //   BottomHome()
                }
            )
            {
                HomeContent(navigateTo)
            }
        }
    }

    @Composable
    fun HomeContent(
        navigateTo: Actions
    ) {
        val context = AmbientContext.current
        val url_facebook = stringResource(R.string.url_facebook)
        val url_twitter = stringResource(R.string.url_twitter)

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
                    { navigateTo.administration() },
                    stringResource(id = R.string.contact)
                )
                RoundedIconButton(
                    R.drawable.actus,
                    navigateTo.listNews,
                    stringResource(id = R.string.actus)
                )
                RoundedIconButton(
                    R.drawable.agenda,
                    navigateTo.listEvents,
                    stringResource(id = R.string.agenda)
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                RoundedIconButton(
                    R.drawable.services_communaux,
                    { navigateTo.listFiches(Bottin.SERVICES_COMMUNAUX) },
                    stringResource(id = R.string.services_communaux)
                )
                RoundedIconButton(
                    R.drawable.enfance,
                    { navigateTo.enfanceList },
                    stringResource(id = R.string.enfance)
                )
                RoundedIconButton(
                    R.drawable.loisirs,
                    { navigateTo.loisirsList },
                    stringResource(id = R.string.loisirs)
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                RoundedIconButton(
                    R.drawable.commerces,
                    { navigateTo.listFiches(Bottin.COMMERCES) },
                    stringResource(id = R.string.commerce)
                )
                RoundedIconButton(
                    R.drawable.fetes,
                    { Toast.makeText(context, "Page en préparation", Toast.LENGTH_LONG).show() },
                    stringResource(id = R.string.marchefete)
                )
                RoundedIconButton(
                    R.drawable.jour_marche,
                    { Toast.makeText(context, "Page en préparation", Toast.LENGTH_LONG).show() },
                    stringResource(id = R.string.jour_marche)
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                RoundedIconButton(
                    R.drawable.urgences,
                    { navigateTo.urgenceList() },
                    stringResource(id = R.string.urgences)
                )
                RoundedIconButton(
                    R.drawable.facebook,
                    {
                        navigateTo.openUrl(
                            context,
                            url_facebook
                        )
                    },
                    stringResource(id = R.string.facebook)
                )
                RoundedIconButton(
                    R.drawable.twitter,
                    { navigateTo.openUrl(context, url_twitter) },
                    stringResource(id = R.string.twitter)
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
    fun HomeScreenWithDrawer(
        navigateTo: Actions,
        syncViewModel: SyncViewModel
//    scaffoldState: ScaffoldState = remember { ScaffoldState() }
    ) {
        val scaffoldState = rememberScaffoldState(
            drawerState = rememberDrawerState(DrawerValue.Closed)
        )
        Scaffold(
            scaffoldState = scaffoldState,
            drawerContent = {
                Column {
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
                HomeContent(navigateTo)
                //  Content(scaffoldState = scaffoldState)
            },
            //  floatingActionButton = { Fab() },
            bottomBar = { BottomBar(scaffoldState) }
        )
    }

}
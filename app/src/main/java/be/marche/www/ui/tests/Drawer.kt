package be.marche.www.ui.tests

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Streetview
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import be.marche.www.home.HomeScreen
import be.marche.www.sync.SyncViewModel


@Composable
fun DrawerScreen(
    listNews: () -> Unit,
    listEvents: () -> Unit,
    listFiches: (Int) -> Unit,
    navigateUp: () -> Unit,
    syncViewModel: SyncViewModel
) {
    val state = rememberBottomDrawerState(BottomDrawerValue.Closed)

    BottomDrawerLayout(
        drawerState = state,
        drawerContent = {
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp),
                onClick = { state.close() },
                content = { Text("Close Drawer") }
            )
        },
        bodyContent = {
            HomeScreen.HomeComponent(
                listNews = listNews,
                listEvents = listEvents,
                listFiches = listFiches,
                navigateUp = navigateUp
            )
        }
    )
}

@Composable
fun HomeDrawer() {
    val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)

    BottomDrawerLayout(
        drawerState = drawerState,
        drawerContent = {
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp),
                onClick = { drawerState.close() },
                content = { Text("Close Drawer") }
            )
        },
        bodyContent = {
            Row() {
                IconButton(onClick = { drawerState.open() }) {
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
        }
    )
}
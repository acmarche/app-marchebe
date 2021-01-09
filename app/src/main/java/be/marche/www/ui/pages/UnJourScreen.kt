package be.marche.www.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.marche.www.R
import be.marche.www.bottin.model.Bottin
import be.marche.www.model.ItemMenu
import be.marche.www.navigation.Actions
import be.marche.www.ui.typography

class UnJourScreen {

    @Composable
    fun ListComponent(navigateTo: Actions) {
        Surface(
            elevation = 10.dp,
            shape = RectangleShape
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(R.string.un_jour)) },
                        navigationIcon = {
                            IconButton(onClick = { navigateTo.home() }) {
                                Icon(Icons.Rounded.ArrowLeft)
                            }
                        },
                    )
                },
                bottomBar = {
                    //   BottomHome()
                }
            )
            {
                Content(navigateTo)
            }
        }
    }

    @Composable
    private fun Content(navigateTo: Actions) {

        val context = AmbientContext.current
        val urlMobilite = stringResource(R.string.url_mobilite)
        val urlParking = stringResource(R.string.url_parking)
        val urlMarches = stringResource(R.string.url_marches)

        val items: List<ItemMenu> = listOf(
            ItemMenu(stringResource(R.string.restauration)) {
                navigateTo.listFiches(Bottin.RESTAURATION)
            },
            ItemMenu(stringResource(R.string.hebergements)) {
                navigateTo.listFiches(Bottin.HEBERGEMENTS)
            },
            ItemMenu(stringResource(R.string.maison_tourisme)) {
                navigateTo.ficheShow(0, Bottin.MDT)
            },
            ItemMenu(stringResource(R.string.train_tect)) {
                navigateTo.openUrl(context, urlMobilite)
            },
            ItemMenu(stringResource(R.string.parkings)) {
                navigateTo.openUrl(context, urlParking)
            },
            ItemMenu(stringResource(R.string.marches)) {
                navigateTo.openUrl(context, urlMarches)
            },
        )

        LazyColumn {
            items(items = items, itemContent = { item ->
                Card(
                    shape = RoundedCornerShape(4.dp),
                    backgroundColor = Color.White,
                    modifier = Modifier.fillParentMaxWidth().padding(8.dp)
                        .clickable(onClick = item.onClick)
                ) {
                    ListItem(text = {
                        Text(
                            text = item.nom,
                            style = typography.h3
                        )
                    }, icon = {
                        Icon(Icons.Filled.ArrowRight)
                    })
                }
            })
        }
    }
}
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

class LoisirsScreen {

    @Composable
    fun ListComponent(navigateTo: Actions) {
        Surface(
            elevation = 10.dp,
            shape = RectangleShape
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(R.string.loisirs)) },
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
        val urlPiscine = stringResource(R.string.url_piscine)
        val urlCinema = stringResource(R.string.url_cinema)

        val items: List<ItemMenu> = listOf(
            ItemMenu(stringResource(R.string.piscine)) {
                navigateTo.openUrl(context, urlPiscine)
            },
            ItemMenu(stringResource(R.string.cinema)) {
                navigateTo.openUrl(context, urlCinema)
            },
            ItemMenu(stringResource(R.string.sports)) {
                navigateTo.listFiches(Bottin.SPORT)
            },
            ItemMenu(stringResource(R.string.musees)) {
                navigateTo.listFiches(Bottin.MUSEES)
            },
            ItemMenu(stringResource(R.string.maison_culture)) {
                navigateTo.ficheShow(0, Bottin.MCFA)
            },
            ItemMenu(stringResource(R.string.academie_arts)) {
                navigateTo.ficheShow(0, Bottin.ACADEMIES_ARTS)
            },
            ItemMenu(stringResource(R.string.conservatoire)) {
                navigateTo.ficheShow(0, Bottin.CONSERVATOIRE)
            },
            ItemMenu(stringResource(R.string.bibliotheque)) {
                navigateTo.listFiches(Bottin.BIBLIOTHEQUES)
            },
            ItemMenu(stringResource(R.string.mda)) {
                navigateTo.ficheShow(0,Bottin.MDA)
            }
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
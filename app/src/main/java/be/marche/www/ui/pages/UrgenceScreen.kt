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

class UrgenceScreen {

    @Composable
    fun ListComponent(navigateTo: Actions) {
        Surface(
            elevation = 10.dp,
            shape = RectangleShape
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(R.string.urgences)) },
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
        val n101 = stringResource(R.string.tel_101)
        val n1733 = stringResource(R.string.tel_1733)
        val n112 = stringResource(R.string.tel_112)
        val urlGarde = stringResource(R.string.pharmacie_url)

        val items: List<ItemMenu> = listOf(
            ItemMenu(stringResource(R.string.police)) {
                navigateTo.callNumber(context, n101)
            },
            ItemMenu(stringResource(R.string.pompier)) {
                navigateTo.callNumber(context, n112)
            },
            ItemMenu(stringResource(R.string.poste_medical)) {
                navigateTo.callNumber(context, n1733)
            },
            ItemMenu(stringResource(R.string.hopital)) {
                navigateTo.ficheShow(0, Bottin.HOPITAL)
            },
            ItemMenu(stringResource(R.string.pharamacies_garde)) {
                navigateTo.openUrl(context, urlGarde)
            },
            ItemMenu(stringResource(R.string.medecins)) {
                navigateTo.listFiches(Bottin.MEDECINS)
            },
            ItemMenu(stringResource(R.string.pharmacies)) {
                navigateTo.listFiches(Bottin.PHARMACIES)
            },
            ItemMenu(stringResource(R.string.mutuelles)) {
                navigateTo.listFiches(Bottin.MUTUELLES)
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
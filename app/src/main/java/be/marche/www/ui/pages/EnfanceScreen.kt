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

class EnfanceScreen {

    @Composable
    fun ListComponent(navigateTo: Actions) {
        Surface(
            elevation = 10.dp,
            shape = RectangleShape
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(R.string.enfance)) },
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
        val urlRepas = stringResource(R.string.repas_url)
        val urlStages = stringResource(R.string.stages_url)

        val items: List<ItemMenu> = listOf(
            ItemMenu(stringResource(R.string.cee)) {
                navigateTo.ficheShow(0, Bottin.CEE)
            },
            ItemMenu(stringResource(R.string.accueillantes)) {
                navigateTo.listFiches(Bottin.ACCUEILLANTES)
            },
            ItemMenu(stringResource(R.string.creches)) {
                navigateTo.listFiches(Bottin.CRECHES)
            },
            ItemMenu(stringResource(R.string.repas_scolaires)) {
                navigateTo.openUrl(context, urlRepas)
            },
            ItemMenu(stringResource(R.string.enseignement)) {
                navigateTo.listFiches(Bottin.ENSEIGNEMENT)
            },
            ItemMenu(stringResource(R.string.plaines)) {
                navigateTo.openUrl(context, urlStages)
            },
            ItemMenu(stringResource(R.string.mdj)) {
                navigateTo.ficheShow(0, Bottin.MDJ)
            },
            ItemMenu(stringResource(R.string.educateurs)) {
                navigateTo.ficheShow(0, Bottin.EDUCATEURS)
            },
            ItemMenu(stringResource(R.string.infor_jeunes)) {
                navigateTo.ficheShow(0, Bottin.INFOR_JEUNES)
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
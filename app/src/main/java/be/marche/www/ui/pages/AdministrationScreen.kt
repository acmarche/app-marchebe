package be.marche.www.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.marche.www.R
import be.marche.www.bottin.model.Bottin
import be.marche.www.navigation.Actions

class AdministrationScreen {

    @Composable
    fun ListComponent(navigateTo: Actions) {
        Surface(
            elevation = 10.dp,
            shape = RectangleShape
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(R.string.contact)) },
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
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Column() {
                Button(
                    onClick = {
                        navigateTo.listFiches(Bottin.COLLEGE)
                    },
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Composition du Collège")
                }

                Button(
                    onClick = {
                        navigateTo.listFiches(Bottin.CONSEIL)
                    },
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Composition du Conseil")
                }
            }

        }
    }
}
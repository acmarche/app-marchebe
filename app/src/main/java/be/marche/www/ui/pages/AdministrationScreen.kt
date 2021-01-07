package be.marche.www.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import be.marche.www.R
import be.marche.www.bottin.model.Bottin
import be.marche.www.navigation.Actions
import be.marche.www.ui.MarcheComposeTheme
import be.marche.www.ui.components.IconeAndText

@Preview
@Composable
fun PreviewList() {
    MarcheComposeTheme() {
        val screen = AdministrationScreen()
        val navController = rememberNavController()
        screen.ListComponent(navigateTo = Actions(navController))
    }
}

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
            elevation = 5.dp,
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Column() {

                val url = stringResource(R.string.url_marche)
                val email = stringResource(R.string.email_info_marche)
                val telephone = stringResource(R.string.tel_administration)

                IconeAndText(texte = stringResource(R.string.telephone), telephone, Icons.Filled.Phone
                ) { navigateTo.callNumber(context, telephone) }

                IconeAndText(
                    texte = stringResource(R.string.site_internet),
                    url,
                    Icons.Filled.Public
                ) { navigateTo.openUrl(context, url) }

                IconeAndText(texte = stringResource(R.string.courriel), email, Icons.Filled.Email
                ) { navigateTo.mailTo(context, email) }

                Button(
                    onClick = {
                        navigateTo.listFiches(Bottin.COLLEGE)
                    },
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(stringResource(R.string.college_compo))
                }
                Spacer(Modifier.preferredHeight(8.dp).clip(shape = RoundedCornerShape(8.dp)))
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
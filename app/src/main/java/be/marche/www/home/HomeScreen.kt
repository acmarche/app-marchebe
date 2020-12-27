package be.marche.www.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.unit.dp
import be.marche.www.R

@Composable
fun HomeScreen(
    listNews: () -> Unit,
    listEvents: () -> Unit,
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
                            //Icon(imageResource(id = R.drawable.marche__logo))
                        }
                    },
                )
            },
        ) {

            Column(
                modifier = Modifier.clip(RoundedCornerShape(8.dp)).padding(16.dp).fillMaxWidth()
                    .fillMaxHeight(),
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

                Row(modifier = Modifier.fillMaxWidth()) {
                    RoundedIconButton(R.drawable.accueil, listNews, "Accueil")
                    RoundedIconButton(R.drawable.actus, listNews, "Actus")
                    RoundedIconButton(R.drawable.agenda, listEvents, "Agenda")
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    RoundedIconButton(R.drawable.services_communaux, listNews, "Services communaux")
                    RoundedIconButton(R.drawable.enfance, listNews, "Enfance")
                    RoundedIconButton(R.drawable.loisirs, listNews, "Loisirs")
                }
            }
        }
    }
}

@Composable
fun RoundedIconButton(
    imageId: Int, onClick: () -> Unit,
    text: String,
    background: Color = Color.Red
) {
    Column(
        modifier = Modifier.clickable(onClick = onClick).background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val modifier = Modifier
            // .preferredSizeIn(minHeight = 120.dp, maxWidth = 130.dp)
            //   .clickable(onClick = onClick)
            .height(120.dp)
            .width(120.dp)
        //  .padding(20.dp)


        val image = loadImageResource(imageId)
        image.resource.resource?.let {
            Image(
                bitmap = it,
                modifier = modifier
            )
        }
        Text(
            //  modifier = Modifier.padding(top = 2.dp),
            style = MaterialTheme.typography.body2,
            color = Color.Gray,
            text = text
        )
    }
}

@Composable
fun ImageHome(imageId: Int, onClick: () -> Unit) {

    val image = loadImageResource(imageId)
    val modifier = Modifier
        // .preferredSizeIn(minHeight = 120.dp, maxWidth = 130.dp)
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
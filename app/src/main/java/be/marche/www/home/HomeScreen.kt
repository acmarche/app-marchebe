package be.marche.www.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
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
) {
    Surface(
        color = Color.LightGray,
        elevation = 10.dp,
        shape = RectangleShape
    ) {

        Column(
            modifier = Modifier.clip(RoundedCornerShape(8.dp)).padding(16.dp).fillMaxWidth()
                .fillMaxHeight(),
        //    verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val image = loadImageResource(R.drawable.gout_vivre)

            image.resource.resource?.let {
                Image(
                    bitmap = it,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Divider(modifier = Modifier.padding(12.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                ImageHome(R.drawable.accueil, listNews)
                ImageHome(R.drawable.actus, listNews)
                ImageHome(R.drawable.agenda, listEvents)
            }

            Divider(modifier = Modifier.padding(12.dp))

            Row(modifier = Modifier.padding(0.dp).fillMaxWidth()) {
                ImageHome(R.drawable.services_communaux, listNews)
                ImageHome(R.drawable.enfance, listNews)
                ImageHome(R.drawable.loisirs, listNews)
            }
        }
    }
}

@Composable
fun ImageHome(imageId: Int, onClick: () -> Unit) {

    val image = loadImageResource(imageId)
    val modifier = Modifier
        .preferredSizeIn(maxHeight = 120.dp, maxWidth = 130.dp)
        .clickable(onClick = onClick)

    image.resource.resource?.let {
        Image(
            bitmap = it,
            modifier = modifier
        )
    }
}
package be.marche.www.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import be.marche.www.R
import be.marche.www.event.EventActivity
import be.marche.www.news.NewsActivity

@Composable
fun HomeNewComponent() {

    val fabShape = RoundedCornerShape(50)
    val context = AmbientContext.current
    val resources = AmbientContext.current.resources

    val title = stringResource(id = R.string.app_name)
    TopAppBar(title = { Text(text = title) })

    Column {

        Row(modifier = Modifier.fillMaxWidth()) {
            TitleComponent(stringResource(R.string.construct))
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            // This Row consists of two buttons. We wanted to ensure that both these buttons occupy
            // equal amount of width. We do that by using the LayoutWeight modifier and passing equal
            // weight to both the buttons. This is similar to how we used layout_weight with
            // LinearLayouts in the old Android UI Toolkit.
            Button(
                modifier = Modifier.padding(16.dp).weight(1f),
                elevation = ButtonDefaults.elevation(5.dp),
                // We increment the counter every time this button is clicked.
                onClick = {

                }) {
                // The Text composable is pre-defined by the Compose UI library; you can use this
                // composable to render text on the screen
                Text(text = "Accueil", modifier = Modifier.padding(16.dp))
            }

            Button(
                modifier = Modifier.padding(16.dp).weight(1f),
                elevation = ButtonDefaults.elevation(5.dp),
                onClick = {
                    context.startActivity(Intent(context, NewsActivity::class.java))
                }) {
                Text(text = "Actus", modifier = Modifier.padding(16.dp))
            }

            Button(
                modifier = Modifier.padding(16.dp).weight(1f),
                elevation = ButtonDefaults.elevation(5.dp),
                onClick = {
                    context.startActivity(Intent(context, EventActivity::class.java))
                }) {
                Text(text = "Agenda", modifier = Modifier.padding(16.dp))
            }
        }
    }

}

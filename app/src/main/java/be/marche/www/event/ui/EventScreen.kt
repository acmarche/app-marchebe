package be.marche.www.event.ui

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.marche.www.event.EventViewModel
import be.marche.www.model.Event
import be.marche.www.ui.components.MarcheComposeTheme
import be.marche.www.ui.components.NetworkImageComponentPicasso
import be.marche.www.ui.components.TextHtml
import be.marche.www.utils.fakeEvent


@Preview
@Composable
private fun PlantDescriptionPreview() {
    MarcheComposeTheme() {
        EventScreen.EventContent(fakeEvent(), {})
    }
}

object EventScreen {
    private val defaultSpacerSize = 16.dp

    @Composable
    fun ShowComponent(
        eventId: Int, eventViewModel: EventViewModel,
        navigateUp: () -> Unit
    ) {
        val event by eventViewModel.findById(eventId).observeAsState(initial = null)
        event?.let { EventContent(it, navigateUp) }
    }

    @Composable
    fun EventContent(
        event: Event,
        navigateUp: () -> Unit
    ) {
        TopAppBar(
            title = { },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            navigationIcon = {
                Box {
                    IconButton(onClick = navigateUp) {
                        Icon(Icons.Filled.ArrowLeft, tint = Color.White)
                        //Icon(imageResource(id = R.drawable.marche_logo))
                    }
                }
            },
        )
        ScrollableColumn(
            //  modifier = Modifier.padding(horizontal = defaultSpacerSize),
            contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 0.dp, bottom = 10.dp)
        ) {
            Spacer(Modifier.preferredHeight(defaultSpacerSize))
            EventHeaderImage(event)
            Text(
                text = event.titre,
                style = MaterialTheme.typography.h2
            )
            Spacer(Modifier.preferredHeight(8.dp))

            Box(modifier = Modifier.padding(start = 5.dp)) {
                event.description?.let { content ->
                    Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                        TextHtml(event.description)
                    }
                }
            }
            Spacer(Modifier.preferredHeight(12.dp))
        }
    }

    @Composable
    private fun EventHeaderImage(event: Event) {
        event.photo?.let { image ->
            val imageModifier = Modifier
                .heightIn(min = 180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))
                .clip(shape = MaterialTheme.shapes.medium)
            NetworkImageComponentPicasso(
                url = image,
                modifier = imageModifier
            )
            Spacer(Modifier.preferredHeight(defaultSpacerSize))
        }
    }
}
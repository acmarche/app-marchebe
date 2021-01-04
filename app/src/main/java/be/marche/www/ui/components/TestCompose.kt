package be.marche.www.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.marche.www.model.News

@Composable
fun EventCardTop(news: News, modifier: Modifier = Modifier) {
    // TUTORIAL CONTENT STARTS HERE
    val typography = MaterialTheme.typography
    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        news.thumbnail?.let { imageUrl ->
            val imageModifier = Modifier
                .heightIn(min = 180.dp)
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.medium)

            NetworkImageComponentPicasso(
                url = imageUrl,
                modifier = Modifier.preferredWidth(60.dp).preferredHeight(60.dp)
            )

            //   Image(image, modifier = imageModifier, contentScale = ContentScale.Crop)
        }
        Spacer(Modifier.preferredHeight(16.dp))

        Text(
            text = news.intitule,
            style = typography.h6
        )
        Text(
            text = news.extrait,
            style = typography.body2
        )

    }
}
// TUTORIAL CONTENT ENDS HERE

@Composable
fun TestAlign() {


    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,

        ) {

    }

}


@Preview
@Composable
fun HomePreview() {
    TestAlign()

}
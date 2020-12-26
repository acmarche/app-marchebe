package be.marche.www.news.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.marche.www.model.News
import be.marche.www.ui.NetworkImageComponentPicasso

@Composable
fun NewsCard(news: News, modifier: Modifier) {

    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = Color.White,
        modifier = modifier
    ) {
        // ListItem is a predefined composable that is a Material Design implementation of [list
        // items](https://material.io/components/lists). This component can be used to achieve the
        // list item templates existing in the spec
        ListItem(text = {
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(
                text = news.intitule,
                style = TextStyle(
                    fontFamily = FontFamily.Serif, fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }, secondaryText = {
            Text(
                text = "Age: ${news.extrait}",
                style = TextStyle(
                    fontFamily = FontFamily.Serif, fontSize = 15.sp,
                    fontWeight = FontWeight.Light, color = Color.DarkGray
                )
            )
        }, icon = {
            news.thumbnail?.let { imageUrl ->
                // Look at the implementation of this composable in ImageActivity to learn
                // more about its implementation. It uses Picasso to load the imageUrl passed
                // to it.
                NetworkImageComponentPicasso(
                    url = imageUrl,
                    modifier = Modifier.preferredWidth(60.dp).preferredHeight(60.dp)
                )
            }
        })
    }

}
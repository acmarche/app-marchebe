package be.marche.www.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp

@Composable
fun TestIconAndroid() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.preferredSize(100.dp).clip(CircleShape)
                .background(MaterialColors.secondary)
        ) {
            Image(
                Icons.Filled.ContactPhone,  // material icon
                modifier = Modifier.preferredSize(100.dp),
                colorFilter = ColorFilter.tint(Color.White),
                //contentScale = ContentScale.Fit
            )
        }

        androidx.compose.material.Text(
            //  modifier = Modifier.padding(top = 2.dp),
            style = MaterialTheme.typography.body2,
            color = Color.Gray,
            text = "Coucou"
        )
    }
}
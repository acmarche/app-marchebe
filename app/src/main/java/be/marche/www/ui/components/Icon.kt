package be.marche.www.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import be.marche.www.ui.blue3

@Composable
fun IconeAndText(texte: String?, value: String?, icon: ImageVector, onClick: () -> Unit = {}) {
    if (value !== null) {
        Row(
            Modifier.clickable(onClick = onClick)
        ) {
            IconButton(onClick = {}) {
                Icon(icon, tint = blue3)
            }
            val content = StringBuilder()
            if (texte != null) {
                content.append("$texte: \n")
            }
            content.append(value)
            Text(
                style = MaterialTheme.typography.h4, text = content.toString()
            )
        }
    }
}
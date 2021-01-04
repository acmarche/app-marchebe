package be.marche.www.ui.tests

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

fun isLightTheme():Boolean {
    return true
}

@Composable
fun CustomDivider() {
    Divider(color = if (isLightTheme()) Color(0xFFEEEEEE) else Color(0xFF333333))
}
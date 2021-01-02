package be.marche.www.ui.components

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import be.marche.www.R

private val Montserrat = fontFamily(
    font(R.font.montserrat_light, FontWeight.Light),
    font(R.font.montserrat_regular, FontWeight.Normal),
    font(R.font.montserrat_medium, FontWeight.Medium),
    font(R.font.montserrat_semibold, FontWeight.SemiBold)
)

private val defaultTypography = Typography()

// Set of Material typography styles to start with
val typography = Typography(

    body1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),

    body2 = defaultTypography.body2.copy(fontFamily = Montserrat),

    h2 = TextStyle(
        fontFamily = Montserrat,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = orange
    ),

    h3 = TextStyle(
        fontFamily = Montserrat,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = orange
    ),

    h4 = TextStyle(
        fontFamily = Montserrat,
        fontSize = 15.sp,
        fontWeight = FontWeight.Light,
        color = gris,
    ),

    h6 = defaultTypography.h6.copy(fontFamily = Montserrat),
    subtitle1 = defaultTypography.subtitle1.copy(fontFamily = Montserrat),
    subtitle2 = defaultTypography.subtitle2.copy(fontFamily = Montserrat),
    button = defaultTypography.button.copy(fontFamily = Montserrat),
    caption = defaultTypography.caption.copy(fontFamily = Montserrat),
    overline = defaultTypography.overline.copy(fontFamily = Montserrat)

    /* Other default text styles to override
button = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp
),
caption = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)
*/
)
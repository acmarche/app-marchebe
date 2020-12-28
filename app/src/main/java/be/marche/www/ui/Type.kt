package be.marche.www.ui

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


// Set of Material typography styles to start with
val typography = Typography(
    body1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    h3 = TextStyle(
        fontFamily = Montserrat,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFff7200)
    ),
    h4 = TextStyle(
        fontFamily = Montserrat,
        fontSize = 12.sp,
      //  fontWeight = FontWeight.Bold,
        color = Color.LightGray
    )


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
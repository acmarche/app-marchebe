package be.marche.www.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

/**
 * https://alexzh.com/jetpack-compose-theme-and-typography/
 * Background colors
 *  Primary color displayed most frequently across your app's screens and components;
 *  Primary Variant color is used to distinguish elements using primary colors, such as top app bar and the system bar.
 *  Secondary color provides more ways to accent and distinguish your product. Having a secondary color is optional, and should be applied sparingly to accent select parts of your UI;
 *  Secondary Variant color is used to distinguish elements using secondary colours;
 *  Background color appears behind scrollable content;
 *  Surface color uses on surfaces of components, like cards and menus;
 *  Error color used for indicating an error.
 * Typography and icon colors
 *  On Primary color of text and icons displayed on top of the primary color.
 *  On Secondary color of text and icons displayed on top of the secondary color;
 *  On Background color of text and icons displayed on top of the background color;
 *  On Surface color of text and icons displayed on top of the surface color;
 *  On Error color of text and icons displayed on top of the error color.
 */
private val DarkColorPalette = darkColors(
    primary = blue1,
    primaryVariant = white,
    secondary = orange,
    background = white,
    surface = white,
    onSurface = white,
    onPrimary = white,
    onSecondary = rouge,
    onBackground = white,
)

private val LightColorPalette = lightColors(
    primary = blue1,
    primaryVariant = rouge,
    secondary = orange,
    background = white,
    surface = rouge,
    onSurface = rouge,
    onPrimary = white,//color on top bar
    onSecondary = rouge,
    onBackground = blue3,//color texte
)

@Composable
fun MarcheComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@Composable
val MaterialColors: Colors
    get() = MaterialTheme.colors

package be.marche.www.ui.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun bottomBar() {
    Column() {
        // Title Component is a custom composable that we created which is capable of
        // rendering text on the screen in a certain font style & text size.
        TitleComponent("This is a simple bottom navigation bar that always shows label")
        // Card composable is a predefined composable that is meant to represent
        // the card surface as specified by the Material Design specification. We
        // also configure it to have rounded corners and apply a modifier.

        // You can think of Modifiers as implementations of the decorators pattern that
        // are used to modify the composable that its applied to. In this example, we assign
        // a padding of 8dp to the Card.
        Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
            BottomNavigationAlwaysShowLabelComponent()
        }
        TitleComponent(
            "This is a bottom navigation bar that only shows label for " +
                    "selected item"
        )
        Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
            BottomNavigationOnlySelectedLabelComponent()
        }
    }
}
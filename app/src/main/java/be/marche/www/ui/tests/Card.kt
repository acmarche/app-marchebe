package be.marche.www.ui.tests

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.unit.dp
import be.marche.www.R


// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun MaterialCardComponent() {
    // Ambient is an implicit way to pass values down the compose tree. Typically, we pass values
    // down the compose tree by passing them as parameters. This makes it easy to have fairly
    // modular and reusable components that are easy to test as well. However, for certain types
    // of data where multiple components need to use it, it makes sense to have an implicit way
    // to access this data. For such scenarios, we use Ambients. In this example, we use the
    // AmbientContext to get hold of the Context object. In order to get access to the latest
    // value of the Ambient, use the "current" property eg - AmbientContext.current. Some other
    // examples of common Ambient's are AmbientTextInputService, AmbientDensity,
    // CoroutineAmbientContext, etc.
    val resources = AmbientContext.current.resources

    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we add a padding of
    // 8dp to the Card composable.
    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
        // ListItem is a predefined composable that is a Material Design implementation of [list
        // items](https://material.io/components/lists). This component can be used to achieve the
        // list item templates existing in the spec
        ListItem(text = {
            Text(text = "Title")
        }, secondaryText = {
            Text(text = "Subtitle")
        }, icon = {
            // Column is a composable that places its children in a vertical sequence. You
            // can think of it similar to a LinearLayout with the vertical orientation.
            // In addition we also pass a few modifiers to it.
            Column(modifier = Modifier.preferredWidth(48.dp).preferredHeight(48.dp)) {
                Image(bitmap = imageFromResource(resources, R.drawable.landscape))
            }
        })
    }
}
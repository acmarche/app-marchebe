package be.marche.www.ui

import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.unit.dp
import be.marche.www.event.EventActivity
import be.marche.www.news.NewsActivity


// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun HomeComponent() {

    val context = AmbientContext.current

    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of the counter. Any composable that reads the value of counter will be recomposed
    // any time the value changes. This ensures that only the composables that depend on this
    // will be redraw while the rest remain unchanged. This ensures efficiency and is a
    // performance optimization. It is inspired from existing frameworks like React.
    var counter by remember { mutableStateOf(0) }
    TitleComponent("L'application est en cours de rénovation. Merci pour votre compréhension")
    // Row is a composable that places its children in a horizontal sequence. You can think of it
    // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
    // to the Row composable. You can think of Modifiers as implementations of the decorators
    // pattern that are used to modify the composable that its applied to. In this example, we
    // assign add a modifier(Modifier.fillMaxWidth()) to the Row and ask it to extend the
    // full width available to it. Alternatively, we could've assigned a fixed width to this row
    // using Modifier.preferredWidth(val width: Dp).
    Row(modifier = Modifier.fillMaxWidth()) {
        // This Row consists of two buttons. We wanted to ensure that both these buttons occupy
        // equal amount of width. We do that by using the LayoutWeight modifier and passing equal
        // weight to both the buttons. This is similar to how we used layout_weight with
        // LinearLayouts in the old Android UI Toolkit.
        Button(
            modifier = Modifier.padding(16.dp).weight(1f),
            elevation = ButtonDefaults.elevation(5.dp),
            // We increment the counter every time this button is clicked.
            onClick = {
                counter++
            }) {
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Accueil", modifier = Modifier.padding(16.dp))
        }

        Button(
            modifier = Modifier.padding(16.dp).weight(1f),
            elevation = ButtonDefaults.elevation(5.dp),
            onClick = {
                context.startActivity(Intent(context, NewsActivity::class.java))
            }) {
            Text(text = "Actus", modifier = Modifier.padding(16.dp))
        }

        Button(
            modifier = Modifier.padding(16.dp).weight(1f),
            elevation = ButtonDefaults.elevation(5.dp),
            onClick = {
                context.startActivity(Intent(context, EventActivity::class.java))
            }) {
            Text(text = "Agenda", modifier = Modifier.padding(16.dp))
        }
    }
}
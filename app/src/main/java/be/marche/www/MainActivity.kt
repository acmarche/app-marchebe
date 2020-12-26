package be.marche.www

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import be.marche.www.ui.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            Column(modifier = Modifier.fillMaxWidth()) {
                HomeComponent()
            }

            /*
            Column(modifier = Modifier.fillMaxWidth()) {
           //     HomeComponent()
                HomeNewComponent()
                TopAppBar(title = { Text(text = "Popular Movies") })

              //  ScaffoldWithBottomBarAndCutout()
                // A pre-defined composable that renders a thin line on the screen that makes it
                // easy to group contents
                Divider()

                MaterialCardComponent()

            }*/

        }
    }
}

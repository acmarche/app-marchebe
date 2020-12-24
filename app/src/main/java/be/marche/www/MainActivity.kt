package be.marche.www

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import be.marche.www.event.EventViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import be.marche.www.ui.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val eventViewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            Column(modifier = Modifier.fillMaxWidth()) {
                HomeComponent()
                // A pre-defined composable that renders a thin line on the screen that makes it
                // easy to group contents
                Divider()

                MaterialCardComponent()

            }

        }
    }
}



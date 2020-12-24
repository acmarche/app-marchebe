package be.marche.www.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import be.marche.www.event.EventViewModel
import be.marche.www.ui.HomeComponent
import be.marche.www.ui.ListEventsComponent
import be.marche.www.ui.MaterialCardComponent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventActivity : AppCompatActivity() {

    private val eventViewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ListEventsComponent(eventViewModel.allEvents)
        }
    }
}



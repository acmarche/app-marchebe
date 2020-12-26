package be.marche.www.event

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import be.marche.www.ui.ListEventsComponent
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

package be.marche.www

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import be.marche.www.ui.MarcheComposeTheme
import be.marche.www.ui.NewsStory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtistCard()
            MarcheComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun ArtistCard() {
    Text("Alfred Sisley")
    Text("3 minutes ago")
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtistCard()
}

/*@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MarcheComposeTheme {
        Greeting("Android")
    }
}*/
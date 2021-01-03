package be.marche.www.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//https://proandroiddev.com/android-jetpack-compose-exploring-state-based-ui-e1d970471d0a
class UiStateComponents {
    @Composable
    fun LoadingView() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            /*   val colorState = transition(
                   definition = Transition,
                   initState = 0,
                   toState = 1
               )*/
            CircularProgressIndicator(
                modifier = Modifier.wrapContentWidth(CenterHorizontally),
                color = Color.Green
            )
        }
    }

    @Composable
    fun ErrorView(message: String = "Oops! Something went wrong, Please refresh after some time") {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            Image(
                Icons.Filled.Face,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.error),
                modifier = Modifier.size(108.dp)
            )
            //  columnSpacer(value = 12)
            ErrorText(message)
        }
    }

    @Composable
    fun EmptyView(message: String = "Nothing in here Yet!, Please comeback later") {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            Image(
                Icons.Filled.Face,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface.copy(alpha = 0.8F)),
                modifier = Modifier.size(108.dp)
            )
            //  columnSpacer(value = 12)
            ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                EmptyText(message)
            }
        }
    }

    @Composable
    fun ErrorText(text: String, modifier: Modifier = Modifier) {
        Text(
            text = text, modifier = modifier.fillMaxWidth().padding(horizontal = 48.dp),
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center, color = MaterialTheme.colors.error.copy(alpha = 0.9F)
        )
    }

    @Composable
    fun EmptyText(text: String, modifier: Modifier = Modifier) {
        Text(
            text = text, modifier = modifier.fillMaxWidth().padding(horizontal = 48.dp),
            style = MaterialTheme.typography.body2,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}
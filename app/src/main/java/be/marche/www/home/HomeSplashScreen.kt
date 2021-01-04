package be.marche.www.home

import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import be.marche.www.R
import be.marche.www.sync.SyncViewModel
import be.marche.www.ui.components.MarcheComposeTheme
import kotlinx.coroutines.delay

/**
 * Aller voir dans l'exemple
 * package androidx.compose.samples.crane.home
 */

private const val SplashWaitTime: Long = 2000

@Composable
fun MainScreen(
    listNews: () -> Unit,
    listEvents: () -> Unit,
    listFiches: (Int) -> Unit,
    navigateUp: () -> Unit
) {
    CraneScaffold {
        var splashShown by remember { mutableStateOf(SplashState.Shown) }
        val transition = transition(splashTransitionDefinition, splashShown)
        Box {
         /*  LandingScreen(
                modifier = Modifier.alpha(transition[splashAlphaKey]),
                onTimeout = { splashShown = SplashState.Completed }
            )*/
            HomeScreen(
                listNews, listEvents, listFiches, navigateUp
            )
        }
    }
}

@Composable
fun CraneScaffold(content: @Composable () -> Unit) {
    MarcheComposeTheme() {
        Surface(color = Color.Blue) {
            content()
        }
    }
}

@Composable
fun LandingScreen(modifier: Modifier = Modifier, onTimeout: () -> Unit) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // Adds composition consistency. Use the value when LaunchedEffect is first called
        val currentOnTimeout by rememberUpdatedState(onTimeout)
        LaunchedEffect(Unit) {
            delay(SplashWaitTime)
            currentOnTimeout()
        }
        Image(imageVector = vectorResource(id = R.drawable.ic_launcher_marche_background))
    }
}


enum class SplashState { Shown, Completed }

private val splashAlphaKey = FloatPropKey("Splash alpha")
private val contentAlphaKey = FloatPropKey("Content alpha")
private val contentTopPaddingKey = DpPropKey("Top padding")

private val splashTransitionDefinition = transitionDefinition<SplashState> {
    state(SplashState.Shown) {
        this[splashAlphaKey] = 1f
        this[contentAlphaKey] = 0f
        this[contentTopPaddingKey] = 100.dp
    }
    state(SplashState.Completed) {
        this[splashAlphaKey] = 0f
        this[contentAlphaKey] = 1f
        this[contentTopPaddingKey] = 0.dp
    }
    transition {
        splashAlphaKey using tween(
            durationMillis = 100
        )
        contentAlphaKey using tween(
            durationMillis = 300
        )
        contentTopPaddingKey using spring(
            stiffness = StiffnessLow
        )
    }
}
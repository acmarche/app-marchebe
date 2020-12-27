package be.marche.www.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import be.marche.www.R
import be.marche.www.ui.MaterialColors

@Composable
fun ToDometerTopBar() {
    Surface(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.height(56.dp).fillMaxWidth()
            ) {
                ToDometerTitle(modifier = Modifier.align(Alignment.Center))
                Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                    IconButton(onClick = {}, modifier = Modifier.align(Alignment.CenterEnd)) {
                        Icon(Icons.Outlined.AccountCircle)
                    }
                }
            }
            HorizontalDivider()
        }
    }
}


@Composable
fun ToDometerTitle(modifier: Modifier = Modifier) {

    val image = loadImageResource(R.drawable.marche__logo)
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        image.resource.resource?.let {
            Image(
                bitmap = it,
                modifier = modifier
            )
        }
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun HorizontalDivider(modifier: Modifier = Modifier) {
    Divider(thickness = 1.dp, color = MaterialColors.onSecondary, modifier = modifier)
}

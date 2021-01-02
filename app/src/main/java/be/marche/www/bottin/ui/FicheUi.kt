package be.marche.www.bottin.ui

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.marche.www.bottin.CategoryViewModel
import be.marche.www.bottin.FicheViewModel
import timber.log.Timber

class FicheUi {

    @Composable
    fun ShowScreen(
        categoryId: Int,
        ficheId: Int,
        categoryViewModel: CategoryViewModel,
        ficheViewModel: FicheViewModel
    ) {

        val fiche by ficheViewModel.findByIdAsLive(ficheId).observeAsState(initial = null)
        fiche.let {

            TopAppBar(
                title = { },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                navigationIcon = {
                    Box {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.ArrowLeft, tint = Color.White)
                            //Icon(imageResource(id = R.drawable.marche_logo))
                        }
                    }
                },
            )
            ScrollableColumn(
                //  modifier = Modifier.padding(horizontal = defaultSpacerSize),
                contentPadding = PaddingValues(
                    start = 10.dp,
                    end = 10.dp,
                    top = 0.dp,
                    bottom = 10.dp
                )
            ) {
                Spacer(Modifier.preferredHeight(16.dp))
                //   PostHeaderImage(event)
                fiche?.societe?.let { it1 ->
                    Text(
                        text = it1,
                        style = MaterialTheme.typography.h2
                    )
                }
                Spacer(Modifier.preferredHeight(8.dp))

                Box(modifier = Modifier.padding(start = 5.dp)) {
                    fiche?.comment1?.let { content ->
                        Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                            Text(
                                text = content,
                                style = MaterialTheme.typography.body2,
                                lineHeight = 20.sp
                            )
                        }
                    }
                }
                Spacer(Modifier.preferredHeight(12.dp))
            }
        }
    }

}
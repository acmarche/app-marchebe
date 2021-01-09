package be.marche.www.bottin.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import be.marche.bottin.model.Categorie
import be.marche.bottin.model.Fiche
import be.marche.www.R
import be.marche.www.bottin.CategoryViewModel
import be.marche.www.bottin.FicheViewModel
import be.marche.www.navigation.Actions
import be.marche.www.ui.components.NetworkImageComponentPicasso

class FicheListScreen {

    @Composable
    fun NavigateCategoryComponent(
        categoryId: Int,
        categoryViewModel: CategoryViewModel,
        ficheViewModel: FicheViewModel,
        navigateTo: Actions
    ) {
        val category by categoryViewModel.findByIdAsLiveData(categoryId)
            .observeAsState(initial = null)

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        if (category != null) {
                            Text(category!!.name)
                        } else {
                            Text(stringResource(R.string.category_not_found))
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { navigateTo.navigateUp }) {
                            Icon(Icons.Rounded.ArrowLeft)
                        }
                    },
                )
            },
            bodyContent = {
                category.let { categorie ->
                    if (categorie !== null) {
                        if (categorie.children.isNotEmpty()) {
                            categorie.children.let { children ->
                                CategoryChildrenComponent(
                                    categories = children,
                                    navigateTo = navigateTo
                                )
                            }
                        } else {
                            val fiches = ficheViewModel.findFichesByCategory(categoryId)
                            fiches.let {
                                ListFichesComponent(
                                    category = categorie,
                                    ficheList = it,
                                    navigateTo = navigateTo
                                )
                            }
                        }
                    } else {
                        CategoryNotFoundComponent()
                    }
                }
            }
        )
    }

    private @Composable
    fun CategoryNotFoundComponent() {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.category_not_found))
        }
    }

    @Composable
    fun CategoryChildrenComponent(
        categories: List<Categorie>,
        navigateTo: Actions
    ) {
        val typography = MaterialTheme.typography

        LazyColumn {
            items(items = categories, itemContent = { category ->
                Card(
                    shape = RoundedCornerShape(4.dp),
                    backgroundColor = Color.White,
                    modifier = Modifier.fillParentMaxWidth().padding(8.dp)
                        .clickable(onClick = { navigateTo.listFiches(category.id) })
                ) {
                    ListItem(
                        text = {
                            Text(
                                text = category.name,
                                style = typography.h3
                            )
                        },
                        icon = {
                            if (category.logo != null) {
                                NetworkImageComponentPicasso(
                                    url = category.logo,
                                    modifier = Modifier.preferredWidth(60.dp)
                                        .preferredHeight(60.dp)
                                )
                            } else {
                                val sizeModifier = Modifier.height(70.dp).width(70.dp)
                                Column(
                                    modifier = sizeModifier,
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    // Image is a pre-defined composable that lays out and draws a given [ImageBitmap].
                                    Image(imageResource(R.drawable.header))
                                }
                            }
                        }
                    )
                }
            })
        }
    }

    @Composable
    fun ListFichesComponent(
        category: Categorie,
        ficheList: LiveData<List<Fiche>>,
        navigateTo: Actions
    ) {
        val typography = MaterialTheme.typography
        val fiches by ficheList.observeAsState(initial = emptyList())

        LazyColumn {
            items(items = fiches, itemContent = { fiche ->
                Card(
                    shape = RoundedCornerShape(4.dp),
                    backgroundColor = Color.White,
                    modifier = Modifier.fillParentMaxWidth().padding(8.dp)
                        .clickable(onClick = { navigateTo.ficheShow(category.id, fiche.id) })
                ) {
                    ListItem(text = {
                        Text(
                            text = fiche.societe,
                            style = typography.h3
                        )
                    }, secondaryText = {
                        val adresse = "${fiche.rue} ${fiche.numero}\n${fiche.localite} "
                        fiche.rue?.let {
                            Text(
                                text = adresse,
                                style = typography.h4
                            )
                        }
                    }, icon = {
                        fiche.logo?.let { imageUrl ->
                            NetworkImageComponentPicasso(
                                url = imageUrl,
                                modifier = Modifier.preferredWidth(60.dp).preferredHeight(60.dp)
                            )
                        }
                    })
                }
            })

        }
    }

    @Composable
    fun LoadingFichesComponent() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
        }
    }
}
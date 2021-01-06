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
import androidx.compose.ui.unit.dp
import be.marche.bottin.model.Category
import be.marche.bottin.model.Fiche
import be.marche.www.R
import be.marche.www.bottin.CategoryViewModel
import be.marche.www.bottin.FicheViewModel
import be.marche.www.navigation.Actions
import be.marche.www.ui.components.NetworkImageComponentPicasso
import timber.log.Timber

object FicheListScreen {

    @Composable
    fun NavigateCategoryComponent(
        categoryId: Int,
        categoryViewModel: CategoryViewModel,
        ficheViewModel: FicheViewModel,
        navigateTo: Actions
    ) {
        Timber.w("category id" + categoryId)
        val categoryLive by categoryViewModel.findById(categoryId).observeAsState(initial = null)
        Timber.w("category live" + categoryLive)

        categoryLive?.let { category ->

            Timber.w("category object " + category.name)

            val childrenLive by categoryViewModel.findChildren(categoryId)
                .observeAsState(initial = emptyList())

            childrenLive.let { children ->

                Timber.w("category children " + children)

                when (children.count()) {
                    0 -> {
                        val fiches by ficheViewModel.findFichesByCategory(categoryId)
                            .observeAsState(initial = emptyList())

                        if (fiches.isEmpty()) {
                            Timber.w("category fiches empty " + categoryId)

                            //Todo
                        } else {
                            ListFichesComponent(
                                category,
                                fiches,
                                navigateTo
                            )
                        }
                    }
                    else -> {
                        CategoryChildrenComponent(category, children, navigateTo)
                    }
                }
            }
        }
    }

    @Composable
    fun CategoryChildrenComponent(
        currentCategory: Category,
        categories: List<Category>,
        navigateTo: Actions
    ) {

        val typography = MaterialTheme.typography

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(currentCategory.name) },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Rounded.ArrowLeft)
                        }
                    },
                )
            },
        ) {
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

    }

    @Composable
    fun ListFichesComponent(
        category: Category,
        ficheList: List<Fiche>,
        navigateTo: Actions
    ) {

        val typography = MaterialTheme.typography

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(category.name) },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Rounded.ArrowLeft)
                        }
                    },
                )
            },
        ) {
            LazyColumn {
                items(items = ficheList, itemContent = { fiche ->
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
                            fiche.rue?.let { it1 ->
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
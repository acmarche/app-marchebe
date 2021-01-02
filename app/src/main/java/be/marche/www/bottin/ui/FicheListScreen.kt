package be.marche.www.bottin.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.marche.bottin.model.Category
import be.marche.bottin.model.Fiche
import be.marche.www.bottin.CategoryViewModel
import be.marche.www.bottin.FicheViewModel
import be.marche.www.ui.components.NetworkImageComponentPicasso
import be.marche.www.utils.fakeCategory
import be.marche.www.utils.fakeFiches

@Composable
fun ListFichesScreen(
    categoryId: Int,
    categoryViewModel: CategoryViewModel,
    ficheViewModel: FicheViewModel,
    onItemClick: (Int) -> Unit
) {

    /**
     * get info category
     * if no error
     * get childs
     *
     */
    val category by categoryViewModel.findById(categoryId).observeAsState(initial = null)
    category?.let {

        val fiches by ficheViewModel.findFichesByCategory(categoryId)
            .observeAsState(initial = emptyList())

        if (fiches.isEmpty()) {
            //Todo
        } else {
            LiveDataComponentListFiches(it, fiches, onItemClick)
        }
    }

    /*  if (category == null) {
          //show error
      } else {
          val children = categoryViewModel.findChildren(categoryId)
          if (children.count() > 0) {
              //show children
          } else {
              val fiches = ficheViewModel.findByCategory(categoryId)
          }
      }
*/
}

@Composable
fun LiveDataComponentListFiches(
    category: Category,
    ficheList: List<Fiche>,
    onItemClick: (Int) -> Unit
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
                        .clickable(onClick = { onItemClick(fiche.id) })
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
                        fiche.image?.let { imageUrl ->
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
fun LiveDataLoadingFichesComponent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
    }
}

@Composable
fun LaunchInCompositionComponent(category: Category, viewModel: FicheViewModel) {
    val ficheList = mutableStateListOf<Fiche>()
    LaunchedEffect(Unit) {
        val list = viewModel.findAllFiches()
        ficheList.addAll(list)
    }

    if (ficheList.isEmpty()) {
        LiveDataLoadingFichesComponent()
        return
    }

    LiveDataComponentListFiches(category, ficheList, {})
}

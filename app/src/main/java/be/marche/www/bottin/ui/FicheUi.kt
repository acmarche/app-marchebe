package be.marche.www.bottin.ui

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Facebook
import androidx.compose.material.icons.rounded.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.marche.bottin.model.Fiche
import be.marche.www.bottin.CategoryViewModel
import be.marche.www.bottin.FicheViewModel
import be.marche.www.ui.components.MarcheComposeTheme
import be.marche.www.ui.components.blue3
import be.marche.www.utils.fakeFiche

class FicheUi {

    @Composable
    fun ShowScreen(
        categoryId: Int,
        ficheId: Int,
        categoryViewModel: CategoryViewModel,
        ficheViewModel: FicheViewModel
    ) {
        val category by categoryViewModel.findById(categoryId).observeAsState(initial = null)
        category?.let {

        }
        val fiche by ficheViewModel.findByIdAsLive(ficheId).observeAsState(initial = null)
//let renvoie quelque chose
        fiche.let {
            if (it != null) {
                Content(it)
            } else {
                NotFound()
            }
        }
    }

    @Composable
    private fun Content(fiche: Fiche) {
        Surface(
            elevation = 10.dp,
            shape = RectangleShape
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(fiche.societe) },
                        navigationIcon = {
                            IconButton(onClick = {}) {
                                Icon(Icons.Rounded.List)
                                //Icon(imageResource(id = R.drawable.marche_logo))
                            }
                        },
                    )
                },
            ) {
                ScrollableColumn(
                    //  modifier = Modifier.padding(horizontal = defaultSpacerSize),
                    contentPadding = PaddingValues(
                        start = 10.dp,
                        end = 10.dp,
                        top = 0.dp,
                        bottom = 10.dp
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        val adresse = "${fiche.rue} ${fiche.numero} \n${fiche.cp} ${fiche.localite}"
                        IconeAndText(texte = null, value = adresse, Icons.Filled.Map)
                    }
                    //   Spacer(Modifier.preferredHeight(8.dp).clip(shape = RoundedCornerShape(8.dp)))
                    Coordonnees(fiche)
                    Social(fiche)
                    Comments(fiche)
                }
            }
        }
    }

    @Composable
    private fun Coordonnees(fiche: Fiche) {
        IconeAndText(texte = "Téléphone", value = fiche.telephone, Icons.Filled.Phone)
        IconeAndText(texte = "Gsm", value = fiche.gsm, Icons.Filled.Smartphone)
        IconeAndText(texte = "Fax", value = fiche.fax, Icons.Filled.SpeakerPhone)
    }

    @Composable
    private fun Social(fiche: Fiche) {
        IconeAndText(texte = "Site", value = fiche.website, Icons.Filled.Public)
        IconeAndText(texte = "Email", value = fiche.email, Icons.Filled.Email)

        IconeAndText(texte = "Facebook", value = fiche.facebook, Icons.Filled.Facebook)
        IconeAndText(texte = "Twitter", value = fiche.twitter, Icons.Outlined.Facebook)
        IconeAndText(
            texte = "Instagram",
            value = fiche.instagram,
            Icons.Filled.SportsSoccer
        )
    }

    @Composable
    private fun Comments(fiche: Fiche) {
        ShowComment(fiche.comment1)
        ShowComment(fiche.comment2)
        ShowComment(fiche.comment3)
    }

    @Composable
    private fun ShowComment(comment: String?) {
        comment?.let {
            Text(
                text = comment,
                style = MaterialTheme.typography.body2,
                lineHeight = 20.sp
            )
            Spacer(Modifier.preferredHeight(12.dp))
        }
    }

    @Composable
    private fun IconeAndText(texte: String?, value: String?, icon: ImageVector) {
        if (value !== null) {
            IconButton(onClick = {}) {
                Icon(icon, tint = blue3)
            }
            val content = StringBuilder()

            if (texte != null) {
                content.append("$texte: \n")
            }

            content.append(value)
            Text(text = content.toString())
        }
    }

    private @Composable
    fun NotFound() {
        Text("Fiche non trouvée")
    }

    @Preview
    @Composable
    fun PreviewFiche() {
        MarcheComposeTheme {
            Content(fakeFiche())
        }
    }
}


package be.marche.www.bottin.ui

import android.content.Context
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import be.marche.bottin.model.Fiche
import be.marche.www.bottin.CategoryViewModel
import be.marche.www.bottin.FicheViewModel
import be.marche.www.navigation.Actions
import be.marche.www.ui.MarcheComposeTheme
import be.marche.www.ui.blue3
import be.marche.www.ui.components.IconeAndText
import be.marche.www.ui.components.NetworkImageComponentPicasso
import be.marche.www.utils.fakeCategory
import be.marche.www.utils.fakeFiche


@Preview
@Composable
fun PreviewFiche() {
    MarcheComposeTheme {
        val navController = rememberNavController()
        val screen = FicheScreen()
        screen.Content(fakeFiche(), fakeCategory().id, Actions(navController))
    }
}

class FicheScreen {

  lateinit var context: Context

    @Composable
    fun ShowComponent(
        categoryId: Int,
        ficheId: Int,
        categoryViewModel: CategoryViewModel,
        ficheViewModel: FicheViewModel,
        navigateTo: Actions
    ) {
        val context = AmbientContext.current

        val category by categoryViewModel.findByIdAsLiveData(categoryId)
            .observeAsState(initial = null)
        category?.let {

        }
        val fiche by ficheViewModel.findByIdAsLive(ficheId).observeAsState(initial = null)

        fiche.let {
            if (it != null) {
                Content(it, categoryId, navigateTo)
            } else {
                NotFound()
            }
        }
    }

    @Composable
    fun Content(fiche: Fiche, categoryId: Int, navigateTo: Actions) {
        Surface(
            elevation = 10.dp,
            shape = RectangleShape
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(fiche.societe) },
                        navigationIcon = {
                            IconButton(onClick = navigateTo.navigateUp) {
                                Icon(Icons.Rounded.ArrowLeft)
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
                    Spacer(Modifier.preferredHeight(8.dp).clip(shape = RoundedCornerShape(8.dp)))
                    HeaderImage(fiche)
                    Spacer(Modifier.preferredHeight(8.dp).clip(shape = RoundedCornerShape(8.dp)))
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        val adresse = "${fiche.rue} ${fiche.numero} \n${fiche.cp} ${fiche.localite}"
                        IconeAndText( null,  adresse, Icons.Filled.Map)
                    }
                    Coordonnees(fiche, false,navigateTo)
                    Social(fiche, navigateTo)
                    Contact(fiche, navigateTo)
                    Comments(fiche)
                }
            }
        }
    }

    @Composable
    private fun Coordonnees(
        fiche: Fiche, isContact: Boolean = false, navigateTo: Actions
    ) {
        val website = if (isContact) null else fiche.website
        val email = if (isContact) fiche.contact_email else fiche.email
        val telephone = if (isContact) fiche.contact_telephone else fiche.telephone
        val telephoneAutre = if (isContact) fiche.contact_telephone_autre else fiche.telephone_autre
        val gsm = if (isContact) fiche.contact_gsm else fiche.gsm
        val fax = if (isContact) fiche.contact_fax else fiche.fax

        IconeAndText( "Site", website, Icons.Filled.Public, { navigateTo.openUrl(context, website) })
        IconeAndText( "Email",  email, Icons.Filled.Email, { navigateTo.mailTo(context, email) })
        IconeAndText( "Téléphone",  telephone, Icons.Filled.Phone, { navigateTo.callNumber(context, telephone) })
        IconeAndText( "Téléphone",  telephoneAutre, Icons.Filled.Phone, { navigateTo.callNumber(context, telephoneAutre) })
        IconeAndText( "Gsm",  gsm, Icons.Filled.Smartphone, { navigateTo.callNumber(context, gsm) })
        IconeAndText( "Fax",  fax, Icons.Filled.SpeakerPhone)
    }

    @Composable
    private fun Social(
        fiche: Fiche, navigateTo: Actions
    ) {
        IconeAndText( "Facebook",  fiche.facebook, Icons.Filled.Facebook, { navigateTo.openUrl(context, fiche.facebook) })
        IconeAndText( "Twitter",  fiche.twitter, Icons.Filled.Facebook, { navigateTo.openUrl(context, fiche.twitter) })
        IconeAndText(             "Instagram",             fiche.instagram,            Icons.Filled.SportsSoccer, { navigateTo.openUrl(context, fiche.instagram) }       )
    }

    @Composable
    private fun Accessibility(fiche: Fiche) {
        IconeAndText( "Pmr",  fiche.pmr.toString(), Icons.Filled.Accessibility)
        IconeAndText( "Centre",  fiche.centreville.toString(), Icons.Filled.Construction)
        IconeAndText( "Midi",  fiche.midi.toString(), Icons.Filled.Fastfood)
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
    private fun Contact(
        fiche: Fiche, navigateTo: Actions
    ) {
        fiche.nom?.let {
            Text("$it ${fiche.prenom} ")
        }
        fiche.contact_rue?.let {
            val adresse =
                "${fiche.contact_rue} ${fiche.contact_num} \n${fiche.contact_cp} ${fiche.contact_localite}"
            IconeAndText( null,  adresse, Icons.Filled.Map)
        }
        Coordonnees(fiche, true, navigateTo)
    }


    @Composable
    private fun HeaderImage(fiche: Fiche) {
        fiche.logo?.let { image ->
            val imageModifier = Modifier
                .heightIn(min = 180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))
                .clip(shape = MaterialTheme.shapes.medium)
            NetworkImageComponentPicasso(
                url = image,
                modifier = imageModifier
            )
        }
    }


    private @Composable
    fun NotFound() {
        Text("Fiche non trouvée")
    }
}

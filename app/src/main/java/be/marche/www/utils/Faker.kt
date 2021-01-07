package be.marche.www.utils

import be.marche.bottin.model.Categorie
import be.marche.bottin.model.Fiche
import be.marche.www.model.Event
import be.marche.www.model.News

fun fakeFiches(): List<Fiche> {
    return listOf(
        fakeFiche()
    )
}

fun fakeFiche(): Fiche {
    val fiche =
    Fiche(
        id = 2,
        societe = "Societe de jf",
        midi = false,
        pmr = false,
        centreville = false,
        telephone = "084 32 51 22",
        rue = "Rue des jolis bois",
        numero = "12",
        cp = "6900",
        localite = "Aye",
        website = "https://cst.marche.be",
        email = "cst@marche.be",
        telephone_autre = "045 22 33 66",
        fax = "081 22 55 88",
        gsm = "0475 66 89 52",
        fonction = "Webmaster",
        civilite = "Monsieur",
        nom = "Dupont",
        prenom = "Jean",
        contact_rue = "Rue des carmes",
        contact_num = "22",
        contact_cp = "6900",
        contact_localite = "Marche",
        contact_email = "info@marche.be",
        contact_telephone = "085 66 99 23",
        contact_telephone_autre = "041 22 55 66",
        contact_fax = "042 44 66 99",
        contact_gsm = "0475 88 99 66",
        comment1 = "ma description 1",
        comment2 = "ma descrition 2",
        comment3 = "ma description 3",
        facebook = "https://facebook.cm",
        image = "https://bottin.marche.be/bottin/fiches/898/947408814a3e396b6fb2a55035291240.jpg",
        instagram = "https://instagram.com",
        twitter = "https://twitter.com",
        logo = "https://bottin.marche.be/bottin/fiches/898/bf3017ff8bdb7b1f9927d9629c138379.png",
        longitude = "50.22",
        latitude = "5.23",
        photos = emptyList()
    )
    return fiche
}

fun fakeCategory(): Categorie {
    return Category(name = "Category test")
}

fun Category(name: String): Categorie {
    return Category(name = "Category test")
}


fun fakeNews(): News {
    return News(
        id = 5,
        intitule = "Fêtes de fin d' année : fermeture de l'Administration communale",
        extrait = "Pas de permanence du service Population les 26/12 et 02/01",
        content = "A l’occasion des fêtes de fin d’année, l’Administration communale de Marche-en-Famenne sera fermée les vendredi 25 décembre et 1er janvier.\n" +
                "\n" +
                "Attention, les 24 et 31 décembre, les services de l’Administration seront accessibles jusque 13h.\n" +
                "\n" +
                "Il n’y aura pas de permanence du service Population les samedi 26 décembre et 2 janvier.",
        image = "https://www.marche.be/wp-content/uploads/2020/05/HDV-site.jpg",
        date = "",
        thumbnail = "",
        url = ""
    )
}

fun fakeEvent(): Event {
    return Event(
        10,
        "Iron Man",
        "blablabla",
        "",
        "",
        "le 10 11 2020",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "https://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55.jpg"
    )

}


fun fakeEvents() = listOf(
    Event(
        10,
        "Iron Man",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "https://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55.jpg"
    )

)


fun fakeListNews() = listOf(
    News(
        10,
        "Iron Man",
        "",
        "",
        "",
        "",
        "",
        ""
    )

)


package be.marche.www.utils

import be.marche.www.model.News


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
package be.marche.www.navigation

class Router {
    object Routes {
        const val Administration = "administration"
        const val Home = "home"
        const val Urgence = "urgences"
        const val News = "listNews"
        const val Agenda = "listEvents"
        const val EventShow = "eventShow/{eventId}"
        const val NewsShow = "newsShow/{newsId}"
        const val Fiches = "listFiches/{categoryId}"
        const val FicheShow = "ficheShow/{categoryId}/{ficheId}"

        object routeArgs {
            const val eventId = "eventId"
            const val newsId = "newsId"
            const val ficheId = "ficheId"
            const val categoryId = "categoryId"
        }
    }
}
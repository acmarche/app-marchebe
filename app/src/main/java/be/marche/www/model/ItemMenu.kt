package be.marche.www.model

data class ItemMenu(
    val nom: String,
    val onClick: () -> Unit,

    )

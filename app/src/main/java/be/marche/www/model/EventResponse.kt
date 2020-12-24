package be.marche.www.entity

data class EventResponse(
    val data: List<Event>?=null,
    val status: String?=""
)
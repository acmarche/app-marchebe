package be.marche.www.model

data class EventResponse(
    val data: List<Event>?=null,
    val status: String?=""
)
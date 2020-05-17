package it.flowing.app.models

data class Content (
    val id: Double,
    val title: String,
    val content: String,
    val slug: String,
    val featuredImage: String,
    val people: List<Surfer>
)

data class Surfer (
    val id: Double,
    val slug: String,
    val name: String,
    val image: String,
    val description: String,
    val role: String
)
package it.flowing.app.models

data class Page<T>(
    val items: List<T>,
    val total: Double
)
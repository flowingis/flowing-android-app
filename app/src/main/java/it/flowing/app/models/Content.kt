package it.flowing.app.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Content (
    val id: Double,
    val title: String,
    val content: String,
    val slug: String,
    val featuredImage: String,
    val people: List<Surfer>
) : Parcelable


@Parcelize
data class Surfer (
    val id: Double,
    val slug: String,
    val name: String,
    val image: String,
    val description: String,
    val role: String
) : Parcelable
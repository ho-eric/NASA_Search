package com.example.nasasearch.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Collection(
    val collection: Second
)

@Serializable
data class Second(
    val version: String,
    val href: String,
    val items: List<Item>,
    val metadata: Metadata
)

@Serializable
data class Item(
    val href: String,
    val data: List<Data>,
    val links: List<Link>
)

@Serializable
data class Data(
    val center: String,
    val title: String,
    @SerialName("nasa_id")
    val nasaId: String,
    @SerialName("date_created")
    val dateCreated: String,
    @SerialName("media_type")
    val mediaType: String,
    val description: String
)

@Serializable
data class Link(
    val href: String,
    val rel: String,
    val render: String
)

@Serializable
data class Metadata(
    @SerialName("total_hits")
    val totalHits: Int
)
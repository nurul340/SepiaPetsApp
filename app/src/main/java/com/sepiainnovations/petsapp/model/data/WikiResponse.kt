package com.sepiainnovations.petsapp.model.data

data class WikiResponse(
    val query: Query? = null
)

data class Query(
    val pages:  Map<String, Pages>? = null
)


data class Pages(
    val extract: String? = null
)

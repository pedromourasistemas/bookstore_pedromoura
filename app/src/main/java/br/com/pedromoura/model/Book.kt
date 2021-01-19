package br.com.pedromoura.model

data class Book (
    var id: String? = null,
    var thumb: String? = null,
    var title: String? = null,
    var description: String? = null,
    var author: ArrayList<String>? = ArrayList(),
    var linkShop: List<String>? = null,
    var favorite: Boolean? = false
)
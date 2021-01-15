package br.com.pedromoura.model

import com.google.gson.annotations.SerializedName

data class Book (
    var thumb: String? = null,
    var name: String? = null,
    var title: String? = null,
    var author: String? = null,
    var linkShop: String? = null,
    var favorite: Boolean? = false
)
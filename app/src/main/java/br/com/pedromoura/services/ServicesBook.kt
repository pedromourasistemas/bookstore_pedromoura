package br.com.pedromoura.services

import br.com.pedromoura.model.BookDTO
import br.com.pedromoura.model.Items
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServicesBook {

    @GET("volumes?q=android&maxResults=20&startIndex=0")
    fun getBooks() : Call<BookDTO>

    @GET("volumes/{volumeId}")
    fun getBookDetail(@Path("volumeId") volumeId: String) : Call<Items>
}
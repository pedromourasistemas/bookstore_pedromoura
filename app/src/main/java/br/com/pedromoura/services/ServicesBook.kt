package br.com.pedromoura.services

import br.com.pedromoura.model.BookDTO
import retrofit2.Call
import retrofit2.http.GET

interface ServicesBook {

    @GET("volumes?q=android&maxResults=20&startIndex=0")
    fun getBooks() : Call<BookDTO>

    //@GET("{cep}")
    //fun getAddress(@Path("cep") cep: String?): Call<Cep>
}
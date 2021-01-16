package br.com.pedromoura

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.pedromoura.model.Book
import br.com.pedromoura.model.BookDTO
import br.com.pedromoura.services.ServicesBook
import br.com.pedromoura.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookViewModel(application: Application) : AndroidViewModel(application) {

    //region Attributes

    var bookResponse: MutableLiveData<List<Book>> = MutableLiveData()
    private val URL_BASE = "https://www.googleapis.com/books/v1/"

    //endregion

    //region Private Methods

    fun getBooks() {
        val retrofitClient = NetworkUtils.getRetrofitInstance(URL_BASE)

        val endpoint = retrofitClient.create(ServicesBook::class.java)
        val callback = endpoint.getBooks()

        var books: MutableList<Book> = mutableListOf()

        callback.enqueue(object : Callback<BookDTO> {
            override fun onResponse(call: Call<BookDTO>, response: Response<BookDTO>) {
                if (response.isSuccessful) {

                    val bookResp = response.body()

                    bookResp?.items?.forEach { item ->
                        var title = item.volumeInfo.title
                        var description = item.volumeInfo.description
                        var thumb = item.volumeInfo.imageLinks?.thumbnail

                        var authors: ArrayList<String> = ArrayList()

                        item.volumeInfo.authors?.forEach {
                            authors.add(it)
                        }

                        var book = Book(thumb, title, description, authors, null, false)

                        books.add(book)
                    }

                    bookResponse.value = books
                }
            }

            override fun onFailure(call: Call<BookDTO>, t: Throwable) {
                Toast.makeText(getApplication(), "Ocorreu um erro na requisição!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    //endregion
}
package br.com.pedromoura

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.pedromoura.model.Book
import br.com.pedromoura.model.BookDTO
import br.com.pedromoura.model.Items
import br.com.pedromoura.services.ServicesBook
import br.com.pedromoura.utils.NetworkUtils
import br.com.pedromoura.utils.StringUtils.Companion.URL_BASE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookViewModel(application: Application) : AndroidViewModel(application) {

    //region Attributes

    var bookResponse: MutableLiveData<List<Book>> = MutableLiveData()
    var bookDetailResponse: MutableLiveData<Book> = MutableLiveData()
    var bookClicked: MutableLiveData<String> = MutableLiveData()
    var bookClickedId: String? = null

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
                        var id = item.id
                        var title = item.volumeInfo.title
                        var description = item.volumeInfo.description
                        var thumb = item.volumeInfo.imageLinks?.thumbnail

                        var authors: ArrayList<String> = ArrayList()

                        item.volumeInfo.authors?.forEach {
                            authors.add(it)
                        }

                        var book = Book(id, thumb, title, description, authors, null, false)

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

    fun getBookDetail(id: String) {
        val retrofitClient = NetworkUtils.getRetrofitInstance(URL_BASE)

        val endpoint = retrofitClient.create(ServicesBook::class.java)
        val callback = endpoint.getBookDetail(id)

        callback.enqueue(object : Callback<Items> {
            override fun onResponse(call: Call<Items>, response: Response<Items>) {
                if (response.isSuccessful) {

                    val bookResp = response.body()

                    var authors: ArrayList<String> = ArrayList()

                    bookResp?.volumeInfo?.authors?.forEach {
                        authors.add(it)
                    }

                    var book = Book(bookResp?.id, bookResp?.volumeInfo?.imageLinks?.thumbnail, bookResp?.volumeInfo?.title, bookResp?.volumeInfo?.description, authors, null, false)

                    bookDetailResponse.value = book
                }
            }

            override fun onFailure(call: Call<Items>, t: Throwable) {
                Toast.makeText(getApplication(), "Ocorreu um erro na requisição!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    //endregion
}
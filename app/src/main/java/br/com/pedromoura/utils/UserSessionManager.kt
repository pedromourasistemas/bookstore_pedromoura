package br.com.pedromoura.utils

import android.content.Context
import android.content.SharedPreferences
import br.com.pedromoura.model.Book
import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList


class UserSessionManager(context: Context) {

    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var _context: Context? = null

    private var PRIVATE_MODE = 0
    private val PREF_NAME = "SharedPrefs"
    private val BOOK_FAVORITE = "BOOK_FAVORITE"

    init {
        _context = context
        pref = _context!!.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref?.edit()
    }

    // This four methods are used for maintaining favorites.
    fun saveFavorites(context: Context, favorites: List<Book?>?) {
        val settings: SharedPreferences
        //val editor: Editor
        settings = context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
        //editor = settings.edit()
        val gson = Gson()
        val jsonFavorites = gson.toJson(favorites)
        editor?.putString(BOOK_FAVORITE, jsonFavorites)
        editor?.commit()
    }

    fun addFavorite(context: Context, book: Book?) {
        var favorites: MutableList<Book?>? = getFavorites(context)
        if (favorites == null) favorites = ArrayList<Book?>()
        favorites!!.add(book)
        saveFavorites(context, favorites)
    }

    fun removeFavorite(context: Context, book: Book?) {
        val favorites: ArrayList<Book?>? = getFavorites(context)
        if (favorites != null) {
            favorites.remove(book)
            saveFavorites(context, favorites)
        }
    }

    fun getFavorites(context: Context): ArrayList<Book?>? {
        val settings: SharedPreferences
        var favorites: List<Book?>?
        settings = context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
        if (settings.contains(BOOK_FAVORITE)) {
            val jsonFavorites = settings.getString(BOOK_FAVORITE, null)
            val gson = Gson()
            val favoriteItems: List<Book> = gson.fromJson<List<Book>>(
                jsonFavorites,
                Book::class.java
            )
            favorites = favoriteItems
            favorites = ArrayList<Book>(favorites)
        } else return null
        return favorites as ArrayList<Book?>?
    }

    /*fun setBooksFavorites(books: List<Book>) {
        val gson = Gson()
        val json = gson.toJson(books)

        editor?.putString("Books", json)
        editor?.commit()
    }

    fun getBooksFavorites() : List<Book> {
        val gson = Gson()
        val json = pref?.getString("Books", "")

        val mBooks: Book = gson.fromJson(json, Book::class.java)

        return mBooks
    }*/
}
package br.com.pedromoura.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.pedromoura.R
import br.com.pedromoura.model.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book_list.view.*


class BookListAdapter(private val books: List<Book>, val listener: (String) -> Unit) : RecyclerView.Adapter<BookListAdapter.BookHolder>() {

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val product: Book = books[position]
        holder.bind(product, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val view = LayoutInflater.from(parent?.context).inflate(
                R.layout.item_book_list,
                parent,
                false
        )
        return BookHolder(view)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    class BookHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var thumb: ImageView? = null
        private var favorite: ImageView? = null
        private var title: TextView? = null
        private var id: TextView? = null

        init {
            id = itemView.findViewById(R.id.id)
            thumb = itemView.findViewById(R.id.img_book)
            title = itemView.findViewById(R.id.name_book)
            favorite = itemView.findViewById(R.id.img_favorite)
        }

        fun bind(book: Book, listener: (String) -> Unit) {
            id?.text = book.id

            title?.text = book.title

            val imageUri = book.thumb

            Picasso.with(itemView.context)
                    .load(imageUri)
                    .fit()
                    .centerCrop()
                    //.placeholder(R.drawable.user_placeholder)
                    //.error(R.drawable.user_placeholder_error)
                    .into(thumb);

            itemView.card_view_book.setOnClickListener {
                listener(id?.text.toString())
            }

            favorite?.setOnClickListener {
                Toast.makeText(itemView.context, "Livro Favoritado!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
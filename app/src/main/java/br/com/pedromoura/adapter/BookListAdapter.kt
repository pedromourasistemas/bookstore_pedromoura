package br.com.pedromoura.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.pedromoura.R
import br.com.pedromoura.model.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book_list.view.*


class BookListAdapter(private val products: List<Book>) : RecyclerView.Adapter<BookListAdapter.BookHolder>() {

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val product: Book = products[position]
        holder.bind(product)
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
        return products.size
    }

    class BookHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var thumb: ImageView? = null
        private var title: TextView? = null

        init {
            thumb = itemView.findViewById(R.id.img_book)
            title = itemView.findViewById(R.id.name_book)
        }

        fun bind(book: Book) {
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
                itemView?.findNavController()?.navigate(R.id.BookDetailFragment)
            }
        }
    }
}
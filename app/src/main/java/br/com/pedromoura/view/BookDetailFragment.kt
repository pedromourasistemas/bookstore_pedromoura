package br.com.pedromoura.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import br.com.pedromoura.BookViewModel
import br.com.pedromoura.databinding.FragmentBookDetailBinding
import com.squareup.picasso.Picasso

class BookDetailFragment : Fragment() {

    //region Attributes

    var binding: FragmentBookDetailBinding? = null
    val viewModel: BookViewModel by activityViewModels()

    //endregion

    //region Override Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
        setUpListeners()
        initViewModel()
    }

    //endregion

    //region Private Methods

    private fun setUpView() {
        viewModel.bookClickedId.let {
            viewModel.getBookDetail(viewModel.bookClickedId!!)
        }
    }

    private fun setUpListeners() {
        binding?.txtLinkShop?.setOnClickListener {
            getUrlFromIntent()
        }
    }

    private fun initViewModel() {
        viewModel.bookDetailResponse.observe(requireActivity()) { state ->
            state?.let { book ->

                val imageUri = book.thumb

                Picasso.with(activity)
                        .load(imageUri)
                        .fit()
                        .centerCrop()
                        //.placeholder(R.drawable.user_placeholder)
                        //.error(R.drawable.user_placeholder_error)
                        .into(binding?.imgThumb);

                binding?.txtTitle?.text = book.title

                var author: String? = ""

                book.author?.forEach {
                    author += it + " \n "
                }

                binding?.txtAuthors?.text = author

                binding?.txtDescription?.text = book.description

                binding?.txtLinkShop?.visibility = View.VISIBLE
            }
        }
    }

    fun getUrlFromIntent() {
        val url = "https://play.google.com/store"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    //endregion
}
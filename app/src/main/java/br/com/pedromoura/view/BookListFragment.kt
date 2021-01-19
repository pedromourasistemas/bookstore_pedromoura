package br.com.pedromoura.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.pedromoura.BookViewModel
import br.com.pedromoura.adapter.BookListAdapter
import br.com.pedromoura.databinding.FragmentBookListBinding


class BookListFragment : Fragment() {

    //region Attributes

    var mListRecyclerView: RecyclerView? = null
    var binding: FragmentBookListBinding? = null

    val viewModel: BookViewModel by lazy {
        ViewModelProvider(this).get(
            BookViewModel::class.java
        )
    }


    //endregion

    //region Override Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBookListBinding.inflate(inflater, container, false)
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
        mListRecyclerView = binding?.recyclerView

        viewModel.getBooks()

        var layoutManager = GridLayoutManager(activity, 2)
        mListRecyclerView?.layoutManager = layoutManager
    }

    private fun setUpListeners() {

    }

    private fun initViewModel() {
        viewModel.bookResponse.observe(requireActivity()) { state ->
            state?.let {
                mListRecyclerView?.adapter = BookListAdapter(it)
            }
        }

        viewModel.bookClicked.observe(requireActivity()) { state ->
            state?.let {
                viewModel.bookClickedId = it
            }
        }
    }

    //endregion
}

interface BookClicked {
    fun clickedBook(id: String)
}
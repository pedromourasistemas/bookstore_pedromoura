package br.com.pedromoura.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.pedromoura.BookViewModel
import br.com.pedromoura.R
import br.com.pedromoura.adapter.BookListAdapter
import br.com.pedromoura.databinding.FragmentBookListBinding


class BookListFragment : Fragment() {

    //region Attributes

    var mListRecyclerView: RecyclerView? = null
    var binding: FragmentBookListBinding? = null
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

    fun onClickListItem(id: String) {
        viewModel.bookClickedId = id

        findNavController()?.navigate(R.id.BookDetailFragment)
    }

    private fun initViewModel() {
        viewModel.bookResponse.observe(requireActivity()) { state ->
            state?.let {
                mListRecyclerView?.adapter = BookListAdapter(it, this::onClickListItem)
            }
        }
    }

    //endregion
}
package br.com.pedromoura.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.pedromoura.R
import br.com.pedromoura.adapter.BookListAdapter
import br.com.pedromoura.databinding.FragmentBookListBinding
import br.com.pedromoura.model.Book

class BookListFragment : Fragment() {

    //region Attributes

    var mListRecyclerView: RecyclerView? = null
    var binding: FragmentBookListBinding? = null

    private val mList = listOf(
        Book(R.drawable.book_android_2,"Google Android"),
        Book(R.drawable.book_android_2,"Google Android"),
        Book(R.drawable.book_android,"Google Android"),
        Book(R.drawable.book_android_2,"Google Android"),
        Book(R.drawable.book_android,"Google Android")
    )

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
        //initViewModel()
    }

    //endregion

    //region Private Methods

    private fun setUpView() {
        mListRecyclerView = binding?.recyclerView
        mListRecyclerView?.adapter = BookListAdapter(mList)

        var layoutManager = GridLayoutManager(activity, 2)
        mListRecyclerView?.layoutManager = layoutManager
    }

    private fun setUpListeners() {
        /*binding?.labelNotHaveAccount?.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        binding?.btnSignin?.setOnClickListener {
            val email = binding?.editLogin?.text.toString().trim()
            val password = binding?.editPassword?.text.toString().trim()

            if (validateFields(email, password)) {
                viewModel.loginUser(this, email, password)
            }
        }*/
    }

    //endregion

    companion object {

    }
}
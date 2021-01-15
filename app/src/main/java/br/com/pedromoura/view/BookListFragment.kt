package br.com.pedromoura.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.pedromoura.BookViewModel
import br.com.pedromoura.adapter.BookListAdapter
import br.com.pedromoura.databinding.FragmentBookListBinding
import br.com.pedromoura.model.Book
import br.com.pedromoura.model.BookDTO
import br.com.pedromoura.services.ServicesBook
import br.com.pedromoura.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BookListFragment : Fragment() {

    //region Attributes

    var mListRecyclerView: RecyclerView? = null
    var binding: FragmentBookListBinding? = null
    private val viewModel: BookViewModel by lazy {
        ViewModelProvider(this).get(
                BookViewModel::class.java
        )
    }
    //private val URL_BASE = "https://www.googleapis.com/books/v1/"

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
        //getData()
    }

    //endregion

    //region Private Methods

    private fun setUpView() {
        mListRecyclerView = binding?.recyclerView

        viewModel.getBooks()

        //mListRecyclerView?.adapter = BookListAdapter(mList)

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
    private fun initViewModel() {
        viewModel.bookResponse.observe(requireActivity()) { state ->
            state?.let {
                binding?.txtTile?.text = it.get(0).title
                //navigateToIntroduction(it)
            }
        }
    }


    /*private fun getData() {
        val retrofitClient = NetworkUtils.getRetrofitInstance(URL_BASE)

        val endpoint = retrofitClient.create(ServicesBook::class.java)
        val callback = endpoint.getBooks()

        callback.enqueue(object : Callback<BookDTO> {
            override fun onResponse(call: Call<BookDTO>, response: Response<BookDTO>) {
                if (response.isSuccessful) {

                    val book = response.body()

                    //mListRecyclerView?.adapter = BookListAdapter(book)

                    //val strCep = book?.kind

                    //binding?.txtTile?.text = book?.kind
                }
            }

            override fun onFailure(call: Call<BookDTO>, t: Throwable) {
                Toast.makeText(activity, "Ocorreu um erro na requisição!", Toast.LENGTH_SHORT).show()
            }
        })
    }*/

    //endregion

    companion object {

    }
}
package com.example.eval7.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eval7.R
import com.example.eval7.VisitCountDataStore
import com.example.eval7.adapter.BookListAdapter
import com.example.eval7.databinding.FragmentHomeScreenBinding
import com.example.eval7.viewModel.BooksViewModel
import com.example.eval7.viewModel.BooksViewModelFactory
import kotlinx.coroutines.flow.collect
import androidx.lifecycle.asLiveData
import com.example.eval7.database.Book
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding

    private val sharedViewModel: BooksViewModel by lazy {
        val activity = requireNotNull(this.activity) {
        }
        ViewModelProvider(this, BooksViewModelFactory(activity.application))
            .get(BooksViewModel::class.java)
    }

    private lateinit var dataStore: VisitCountDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onAttach(context: Context) {
        super.onAttach(context)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)



        dataStore = VisitCountDataStore(requireContext())
        dataStore.preferenceFlow.asLiveData().observe(viewLifecycleOwner, {
            binding.visitCount.text = "Current visitor count : ${it+1}"
        })




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = BookListAdapter(::toggle, ::delete)
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)


        binding.booksList.adapter = adapter
        binding.booksList.layoutManager = layoutManager


        sharedViewModel.allBooks.observe(viewLifecycleOwner, {
            adapter.submitList(it)
            binding.countText.text = "Total books : ${it.size}"
        })

        binding.floatingActionButton.setOnClickListener { findNavController().navigate(R.id.action_homeScreenFragment_to_addFragment) }

        binding.unreadBookBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenFragment_to_unreadBooksFragment)
        }


    }

    fun toggle(book: Book) {
        sharedViewModel.update(book)
    }

    fun delete(book: Book) {
        sharedViewModel.delete(book)
    }


}
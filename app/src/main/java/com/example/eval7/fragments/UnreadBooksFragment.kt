package com.example.eval7.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eval7.R
import com.example.eval7.adapter.BookListAdapter
import com.example.eval7.adapter.UnreadBooksListAdapter
import com.example.eval7.databinding.FragmentUnreadBooksBinding
import com.example.eval7.viewModel.BooksViewModel
import com.example.eval7.viewModel.BooksViewModelFactory

class UnreadBooksFragment : Fragment() {

    private lateinit var binding: FragmentUnreadBooksBinding
    private val sharedViewModel: BooksViewModel by lazy {
        val activity = requireNotNull(this.activity) {
        }
        ViewModelProvider(this, BooksViewModelFactory(activity.application))
            .get(BooksViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUnreadBooksBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = UnreadBooksListAdapter()
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        binding.unreadBooksList.adapter = adapter
        binding.unreadBooksList.layoutManager = layoutManager


        sharedViewModel.unreadBooks.observe(viewLifecycleOwner,{
            adapter.submitList(it)
            binding.unreadCount.text = "Total Unread books : ${it.size}"
        })

    }


}
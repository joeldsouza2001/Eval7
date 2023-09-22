package com.example.eval7.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.eval7.R
import com.example.eval7.databinding.FragmentAddBinding
import com.example.eval7.viewModel.BooksViewModel
import com.example.eval7.viewModel.BooksViewModelFactory

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

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
        binding = FragmentAddBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener { submit() }
    }

    fun submit() {
        sharedViewModel.insert(
            binding.titleEdit.text.toString(),
            binding.authorEdit.text.toString(),
            binding.checkbox.isChecked

        )


//        println(binding.checkbox.isActivated)


        binding.titleEdit.text?.clear()
        binding.authorEdit.text?.clear()
        findNavController().navigateUp()
//        Toast.makeText(,"Books Added",Toast.LENGTH_SHORT)
    }


}
package com.example.eval7.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eval7.database.Book
import com.example.eval7.databinding.BookItemBinding

class BookListAdapter(
    val toggle: (Book) -> Unit,
    val delete:(Book) -> Unit

) : ListAdapter<Book, BookListAdapter.ItemViewHolder>(DiffCallback) {

    class ItemViewHolder(private var binding: BookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //val binding = binding
        val binding1 = binding
        fun bind(book: Book) {
            binding.bookTitle.text = book.title
            binding.bookAuthor.text = book.author
            binding.readSwitch.isChecked = book.doneReading

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context))
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var curItem = getItem(position)
        holder.bind(curItem)
        holder.binding1.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            toggle(
                Book(
                    curItem.id,
                    curItem.title,
                    curItem.author,
                    isChecked
                )
            )
        }

        holder.binding1.deleteBtn.setOnClickListener{
            delete(curItem)
        }

    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}
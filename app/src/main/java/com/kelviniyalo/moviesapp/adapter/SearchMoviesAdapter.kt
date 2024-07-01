package com.kelviniyalo.moviesapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kelviniyalo.moviesapp.common.Helper.loadImage
import com.kelviniyalo.moviesapp.data.model.Search
import com.kelviniyalo.moviesapp.databinding.MovieItemLayoutBinding


class SearchMoviesAdapter(
    private val context: Context,
    private val onItemClicked: (position: Int, itemAtPosition: Search) -> Unit
) : ListAdapter<Search, SearchMoviesAdapter.MoviesHistoryVH>(object :
    DiffUtil.ItemCallback<Search>() {

    override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
        return oldItem.imdbID == newItem.imdbID
    }

    override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
        return oldItem == newItem
    }

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHistoryVH {
        //inflate the view to be used by the payment option view holder
        val binding = MovieItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesHistoryVH(binding, onItemClick = { position ->
            val itemAtPosition = currentList[position]
            this.onItemClicked(position, itemAtPosition)
        })

    }

    override fun getItemCount(): Int = currentList.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MoviesHistoryVH, position: Int) {
        val itemAtPosition = currentList[position]
        holder.bind(itemAtPosition)
    }


    inner class MoviesHistoryVH(
        private val binding: MovieItemLayoutBinding,
        onItemClick: (position: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }


        @SuppressLint("ResourceType")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(movies: Search) {
            with(binding) {
                movieImg.loadImage(movies.Poster,context)
                movieTittle.text = movies.Title
                movieYear.text = buildString {
                    append("Genre: "+movies.Type)
                    append(" - ")
                    append("Released: "+ movies.Year)
                }
            }
        }

    }
}
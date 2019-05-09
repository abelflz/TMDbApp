package com.afeliz.tmdbapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.afeliz.tmdbapp.ui.interfaces.FragmentListener
import com.afeliz.tmdbapp.R
import com.afeliz.tmdbapp.repository.retrofit.MovieList

class RecyclerAdapter(private var movieList:List<MovieList>, private var listener: FragmentListener) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.f_movie_list, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = movieList[position]

        holder.title.text = item.title
        holder.overview.text = item.overview
        val url:String = "https://image.tmdb.org/t/p/w500"+item.poster_path

        Glide.with(holder.itemView).load(url).into(holder.image)

        holder.itemView.setOnClickListener {
            listener.onMainToSecond(item.id, item.title)
        }
    }

    fun setData(list:List<MovieList>){
        this.movieList = list
        notifyDataSetChanged()
    }

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val title:TextView = view.findViewById(R.id.tvTitle)
        val image:ImageView = view.findViewById(R.id.ivMovie)
        val overview:TextView = view.findViewById(R.id.tvOverview)
    }
}
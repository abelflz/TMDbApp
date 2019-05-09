package com.afeliz.tmdbapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.afeliz.tmdbapp.R
import com.afeliz.tmdbapp.repository.ApiService
import com.afeliz.tmdbapp.repository.MovieResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondFragment : Fragment() {

    private var movieId:Int = 0
    private var movieTitle:String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.f_second_fragment, container, false)

        val title: TextView = view.findViewById(R.id.tvMovieTitle)
        title.text = movieTitle

        val btnYoutube : Button = view.findViewById(R.id.btnYoutubeLink)

        ApiService.api.getMovieTrailer(movieId).enqueue(object : Callback<MovieResult>{
            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                Log.wtf("a", "a")
            }

            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                if(response.code() == 200){
                    btnYoutube.setOnClickListener {
                        watchYouTube(response.body()?.results?.get(0)?.key.toString())
                    }
                }
            }

        })

        return view
    }

    fun watchYouTube(key:String)
    {
        val youTubePlay = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$key"))
        val chooser = Intent.createChooser(youTubePlay , "Open With")

        if (youTubePlay.resolveActivity(activity!!.packageManager) != null) {
            startActivity(chooser)
        }
    }

    companion object {
        fun newInstance(id:Int, title:String) : SecondFragment {
            val second = SecondFragment()
            second.movieId = id
            second.movieTitle = title
            return second
        }
    }
}
package com.afeliz.tmdbapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afeliz.tmdbapp.ui.interfaces.FragmentListener
import com.afeliz.tmdbapp.R
import com.afeliz.tmdbapp.repository.ApiService
import com.afeliz.tmdbapp.repository.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {

    lateinit var listener: FragmentListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try{
            listener = context as FragmentListener
        }catch (e: ClassCastException){
            throw ClassCastException(context?.toString()+" must implement Listener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.f_main_fragment, container, false)

        val myAdapter = RecyclerAdapter(
            listOf(),
            object : FragmentListener {
                override fun onMainToSecond(id: Int, title: String) {
                    listener.onMainToSecond(id, title)
                }
            })

        val recycler : RecyclerView = view.findViewById(R.id.recycle_view)
        recycler.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = myAdapter
        }

        ApiService.api.getUpcomingMovies().enqueue(object : Callback<Movies> {
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.wtf("a","a")
            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if(response.code() == 200){
                    response.body()?.results?.apply {
                        myAdapter.setData(this)
                    }
                }
            }
        })

        super.onCreateView(inflater, container, savedInstanceState)
        return view
    }

    companion object {
        fun newInstance() : MainFragment {
            return MainFragment()
        }
    }
}
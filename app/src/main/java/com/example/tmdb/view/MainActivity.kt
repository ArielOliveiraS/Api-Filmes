package com.example.tmdb.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdb.R
import com.example.tmdb.api.remote.API_KEY
import com.example.tmdb.model.Movie
import com.example.tmdb.view.adapter.MovieRecyclerViewAdapter
import com.example.tmdb.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val list = mutableListOf<Movie>()
    private val adapter = MovieRecyclerViewAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        recyclerViewXml.adapter = adapter
        recyclerViewXml.layoutManager = LinearLayoutManager(this)

        viewModel.getAllMovies(API_KEY)

        viewModel.movieListResult.observe(this, Observer {
            adapter.updateList(it as MutableList<Movie>)
        })

        viewModel.loadingResult.observe(this, Observer {
            if (it){
                progressBar.visibility = View.VISIBLE
            }else{
                progressBar.visibility = View.GONE
            }
        })

        viewModel.errorResult.observe(this, Observer {
            Snackbar.make(recyclerViewXml, it, Snackbar.LENGTH_SHORT).show()
        })
    }


    fun listaNomes(): MutableList<String>{
        var lista = mutableListOf("Ariel", "Glayce", "Ariel", "Glayce", "Ariel", "Glayce", "Ariel", "Glayce")
        return lista
    }
}

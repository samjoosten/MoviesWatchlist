package com.example.movieapplication.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.R
import com.example.movieapplication.add.AddActivity
import com.example.movieapplication.detail.DetailActivity
import com.example.movieapplication.model.Movie

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val movies = arrayListOf<Movie>()
    private val movieAdapter = MovieAdapter(movies, onClick = {onItemClicked(it)})

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()

    }

    private fun initViews() {
        rvMovies.layoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager?
        rvMovies.adapter = movieAdapter

        val sortOpts = resources.getStringArray(R.array.sortOpts)

        val sortSpinner = findViewById<Spinner>(R.id.spnSort)

        if (sortSpinner != null) {
            val adapter = ArrayAdapter(this,
                R.layout.spinner_item, sortOpts)
            sortSpinner.adapter = adapter

            sortSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                    onSortClick(sortOpts[position])
                }

            }
        }

        fab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        btnDelete.setOnClickListener { onDeleteMoviesClick() }
//        btnSort.setOnClickListener { onSortClick()   }

    }

    private fun initViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mainViewModel.allMovies.observe(this, Observer { movieList ->
            if (movieList != null) {
                movies.clear()
                movies.addAll(movieList)
                movieAdapter.notifyDataSetChanged()

            }
        })
    }

    private fun onDeleteMoviesClick() {
        if (movies.isNotEmpty()) {
            val movieToBeDeleted = movies.first()
            movies.remove(movies.first())
            val snackbar = Snackbar.make(
                content,
                getString(R.string.item_deleted, movieToBeDeleted.title),
                Snackbar.LENGTH_LONG
            )
            snackbar.setAction(getString(R.string.undo)) {
                onUndoClick(movieToBeDeleted)
            }
            snackbar.addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    if (event == DISMISS_EVENT_TIMEOUT) {
                        mainViewModel.deleteMovie(movieToBeDeleted)
                    }
                    super.onDismissed(transientBottomBar, event)
                }
            })
            snackbar.show()
            movieAdapter.notifyDataSetChanged()
        }
    }

    private fun onSortClick(sortProperty: String) {
        mainViewModel.allMovies.observe(this, Observer { movieList ->
            if (movieList != null) {
                var sortedMovies = movieList
                if (sortProperty == "Rating") {
                    sortedMovies = movieList.sortedWith(compareBy(Movie::rating))
                } else if (sortProperty == "Title") {
                    sortedMovies = movieList.sortedWith(compareBy(Movie::title))
                }
                movies.clear()
                movies.addAll(sortedMovies)
                movieAdapter.notifyDataSetChanged()
            }



        })
    }

    private fun onItemClicked(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("Movie", movie)
        intent.putExtras(bundle)
        startActivity(intent)

    }

    private fun onUndoClick(movie: Movie) {
        movies.add(0, movie)
        movieAdapter.notifyDataSetChanged()
    }
}

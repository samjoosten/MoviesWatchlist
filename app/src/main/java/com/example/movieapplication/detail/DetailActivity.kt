package com.example.movieapplication.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.movieapplication.R
import com.example.movieapplication.model.Movie
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.btnConfirm
import kotlinx.android.synthetic.main.activity_detail.toolbar
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.content_add.*
import kotlinx.android.synthetic.main.content_detail.*
import java.lang.Exception
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        initViews()
    }

    private fun initViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        val bundle = intent.extras
        if (bundle != null) {
            val movie: Movie = bundle.get("Movie") as Movie
            movieTitle.setText(movie.title)
            movieRating.setText(movie.rating)
            movieReminder.text = movie.notification.toString()
            movieType.setText(movie.type)
            movieNotes.setText(movie.notes)

            btnConfirm.setOnClickListener { movie.id?.let { it1 -> onUpdateClick(it1) } }
            btnDelete.setOnClickListener { onDeleteClick(movie) }
        }

    }

    private fun onUpdateClick(movieId: Long) {
        detailViewModel.updateMovie(
            Movie(
                movieId,
                movieTitle.text.toString(),
                movieRating.text.toString(),
                movieType.text.toString(),
                Date(movieReminder.text.toString()),
                movieNotes.text.toString()
            ))
        val snackbar = Snackbar.make(detailContent, getString(R.string.item_updated,
            movieTitle.text.toString()), Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    private fun onDeleteClick(movie: Movie) {
        detailViewModel.deleteMovie(movie)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return try {
            finish()
            true
        } catch (e: Exception) {
            false
        }
    }
}
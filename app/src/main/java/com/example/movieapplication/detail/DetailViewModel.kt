package com.example.movieapplication.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.movieapplication.database.MovieRepository
import com.example.movieapplication.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository = MovieRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    fun updateMovie(movie: Movie) {
        mainScope.launch {
            movieRepository.updateMovie(movie)
        }
    }

    fun deleteMovie(movie: Movie) {
        mainScope.launch {
            movieRepository.deleteMovie(movie)
        }
    }
}
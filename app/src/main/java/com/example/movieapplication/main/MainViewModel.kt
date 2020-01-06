package com.example.movieapplication.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.movieapplication.database.MovieRepository
import com.example.movieapplication.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository = MovieRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val allMovies = movieRepository.getAllMovies()

    fun deleteMovie(movie: Movie) {
        mainScope.launch {
            movieRepository.deleteMovie(movie)
        }
    }
}
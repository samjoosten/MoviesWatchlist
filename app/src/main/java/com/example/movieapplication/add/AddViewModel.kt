package com.example.movieapplication.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.movieapplication.database.MovieRepository
import com.example.movieapplication.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository = MovieRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    fun insertMovie(movie: Movie) {
        mainScope.launch {
            movieRepository.insertMovie(movie)
        }
    }

}
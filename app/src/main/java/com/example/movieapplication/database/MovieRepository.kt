package com.example.movieapplication.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.movieapplication.model.Movie

class MovieRepository (context: Context) {

    private val movieDao: MovieDao

    init {
        val database = MovieRoomDatabase.getMovieDatabase(context)
        movieDao = database!!.movieDao()
    }

    fun getAllMovies() : LiveData<List<Movie>?> {
        return movieDao.getAllMovies()
    }

    suspend fun updateMovie(movie: Movie) {
        return movieDao.updateMovie(movie)
    }

    suspend fun deleteMovie(movie: Movie) {
        return movieDao.deleteMovie(movie)
    }

    suspend fun insertMovie(movie: Movie) {
        return movieDao.insertMovie(movie)
    }
}
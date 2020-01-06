package com.example.movieapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieapplication.model.Movie

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovie(movie: Movie)

    @Query("SELECT * FROM Movie")
    fun getAllMovies(): LiveData<List<Movie>?>

    @Update
    suspend fun updateMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)
}
package com.tranhoabinh.framgia.moviedbkotlin.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie
import io.reactivex.Maybe

@Dao
interface RoomMovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movie: Movie?): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFavoriteMovie(vararg movie: Movie?): Int

    @Query("SELECT * FROM movie WHERE movie.id = :id LIMIT 1")
    fun findById(id: String?): List<Movie>

    @Query("SELECT * FROM movie WHERE movie.isFavorite == 1 LIMIT :limit OFFSET :offset")
    fun getFavoriteMovies(limit: Int, offset: Int): Maybe<List<Movie>>
}

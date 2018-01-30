package com.letoti.kawa.philmaker.web.api

import com.letoti.kawa.philmaker.web.entity.MovieItem
import com.letoti.kawa.philmaker.web.entity.MovieShortDto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by kawa on 29.01.2018.
 */
interface MovieAPI {

    @GET("discover/movie/")
    fun getMovieList(@Query("page") page: Int): Observable<MovieShortDto>

    @GET("search/movie/")
    fun getMovieListByName(@Query("page") page: Int, @Query("query") query: String): Observable<MovieShortDto>

    @GET("movie/{movie_id}")
    fun getMovieInfo(@Path("movie_id") movieId: Int): Observable<MovieItem>
}
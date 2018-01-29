package com.letoti.kawa.philmaker.web.api

import com.letoti.kawa.philmaker.web.entity.MovieDto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by kawa on 29.01.2018.
 */
interface MovieAPI {
    @GET("discover/movie")
    fun getMovieList(@Query("page") page: Int, @Query("with_keywords") withKeywords: String): Observable<MovieDto>
}
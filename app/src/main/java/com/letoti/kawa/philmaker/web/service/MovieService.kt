package com.letoti.kawa.philmaker.web.service

import com.letoti.kawa.philmaker.web.entity.MovieShortDto
import com.letoti.kawa.philmaker.web.WebManager
import com.letoti.kawa.philmaker.web.api.MovieAPI
import com.letoti.kawa.philmaker.web.entity.MovieItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by kawa on 29.01.2018.
 */

class MovieService : MovieAPI {

    private val service = WebManager.instance.retrofit.create(MovieAPI::class.java)

    override fun getMovieListByName(page: Int, query: String): Observable<MovieShortDto> {
        return service.getMovieListByName(page, query).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getMovieList(page: Int): Observable<MovieShortDto> {
        return service.getMovieList(page).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getMovieInfo(movieId: Int): Observable<MovieItem> {
        return service.getMovieInfo(movieId).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    }
}
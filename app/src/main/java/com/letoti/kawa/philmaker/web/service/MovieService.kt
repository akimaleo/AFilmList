package com.letoti.kawa.philmaker.web.service

import com.letoti.kawa.philmaker.web.entity.MovieDto
import com.letoti.kawa.philmaker.web.WebManager
import com.letoti.kawa.philmaker.web.api.MovieAPI
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by kawa on 29.01.2018.
 */

class MovieService : MovieAPI {

    private val service = WebManager.instance.retrofit.create(MovieAPI::class.java)

    override fun getMovieList(page: Int, withKeywords: String): Observable<MovieDto> {
        return service.getMovieList(page, withKeywords).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    }
}
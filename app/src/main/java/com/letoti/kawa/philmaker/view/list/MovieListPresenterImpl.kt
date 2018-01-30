package com.letoti.kawa.philmaker.view.list

import com.letoti.kawa.philmaker.web.WebManager
import com.letoti.kawa.philmaker.web.entity.MovieShortDto
import io.reactivex.Observable
import java.util.*

/**
 * Created by kawa on 29.01.2018.
 */

class MovieListPresenterImpl(var view: MovieListView) : MovieListPresenter {

    override fun getMovie(query: String, page: Int) {
        view.showLoadingProgress()
        val observable: Observable<MovieShortDto> = if (query.isEmpty()) {
            WebManager.instance.movieService.getMovieList(page)
        } else {
            WebManager.instance.movieService.getMovieListByName(page,query)
        }
        observable.subscribe({ resp ->
            view.hideLoadingProgress()
            if (page > 1) {
                view.addMovieList(resp)
            } else {
                view.showMovieList(resp)
            }
        }, { err ->
            view.hideLoadingProgress()
            err.printStackTrace()
        })
    }
}
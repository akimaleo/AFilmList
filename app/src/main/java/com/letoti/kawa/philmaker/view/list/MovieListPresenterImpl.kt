package com.letoti.kawa.philmaker.view.list

import com.letoti.kawa.philmaker.web.WebManager

/**
 * Created by kawa on 29.01.2018.
 */

class MovieListPresenterImpl(var view: MovieListView) : MovieListPresenter {

    override fun getMovie(keyWord: String, page: Int) {
        view.showLoadingProgress()
        WebManager.instance.movieService
                .getMovieList(page, keyWord)
                .subscribe({ resp ->
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
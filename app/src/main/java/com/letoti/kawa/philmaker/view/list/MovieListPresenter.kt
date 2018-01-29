package com.letoti.kawa.philmaker.view.list

/**
 * Created by kawa on 29.01.2018.
 */

interface MovieListPresenter {
    fun getMovie(keyWord: String, page: Int)
}
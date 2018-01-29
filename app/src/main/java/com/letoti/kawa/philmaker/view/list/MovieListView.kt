package com.letoti.kawa.philmaker.view.list

import com.letoti.kawa.philmaker.view.common.IView
import com.letoti.kawa.philmaker.web.entity.MovieDto

/**
 * Created by kawa on 29.01.2018.
 */

interface MovieListView : IView {
    fun showMovieList(item: MovieDto)
    fun addMovieList(item: MovieDto)
}
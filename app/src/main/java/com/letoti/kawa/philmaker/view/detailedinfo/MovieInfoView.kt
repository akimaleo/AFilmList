package com.letoti.kawa.philmaker.view.detailedinfo

import com.letoti.kawa.philmaker.view.common.IView

/**
 * Created by kawa on 29.01.2018.
 */
interface MovieInfoView : IView {
    fun showMovieInfo(item: MovieDetailed)
}
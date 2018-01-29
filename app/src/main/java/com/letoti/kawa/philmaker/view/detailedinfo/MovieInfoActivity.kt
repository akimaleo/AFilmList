package com.letoti.kawa.philmaker.view.detailedinfo

import android.os.Bundle
import com.letoti.kawa.philmaker.R
import com.letoti.kawa.philmaker.view.common.BaseActivity

class MovieInfoActivity : BaseActivity(), MovieInfoView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_info)

    }

    override fun showMovieInfo(item: MovieDetailed) {
    }
}

package com.letoti.kawa.philmaker.view.detailedinfo

import android.app.Activity
import android.os.Bundle
import com.google.gson.Gson
import com.letoti.kawa.philmaker.R
import com.letoti.kawa.philmaker.view.common.BaseActivity
import com.letoti.kawa.philmaker.web.WebManager
import com.letoti.kawa.philmaker.web.entity.MovieItem
import kotlinx.android.synthetic.main.activity_movie_info.*

class MovieInfoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_info)

        val movieInfo = Gson().fromJson(intent.getStringExtra("short_info"), MovieItem::class.java)

        initToolbar()
        showMovieInfo(movieInfo)
    }

    private fun initToolbar() {
        setSupportActionBar(main_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        main_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        main_toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun showMovieInfo(item: MovieItem) {
        description.text = item.overview
        main_toolbar.title = item.title
        WebManager.ImageLoader.loadImage(movie_logo,
                item.poster_path ?: "",
                WebManager.ImageLoader.Size.THUMBNAIL,
                0)

        if (item.backdrop_path.isNullOrEmpty())
            WebManager.ImageLoader.loadImage(mainbackdrop,
                    item.backdrop_path!!,
                    WebManager.ImageLoader.Size.MEDIUM,
                    R.drawable.background_gradient)
        else
            WebManager.ImageLoader.loadImage(mainbackdrop,
                    item.poster_path ?: "",
                    WebManager.ImageLoader.Size.MEDIUM,
                    R.drawable.background_gradient)
    }

    override fun showErrorMessage() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}

package com.letoti.kawa.philmaker.web.entity

/**
 * Created by kawa on 29.01.2018.
 */
data class MovieDto(var page: Int,
                    var total_results: Int,
                    var total_pages: Int,
                    var results: ArrayList<MovieItem> = ArrayList())

data class MovieItem(
        var vote_count: Int,
        var id: Int,
        var video: Boolean,
        var vote_average: Float,
        var title: String,
        var poster_path: String
)
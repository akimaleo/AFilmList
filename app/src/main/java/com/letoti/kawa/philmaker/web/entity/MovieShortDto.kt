package com.letoti.kawa.philmaker.web.entity

/**
 * Created by kawa on 29.01.2018.
 */
data class MovieShortDto(var page: Int = 0,
                         var total_results: Int = 0,
                         var total_pages: Int = 0,
                         var results: ArrayList<MovieItem> = ArrayList())

data class MovieItem(var vote_count: Int = 0,
                     var id: Int = 0,
                     var video: Boolean = false,
                     var vote_average: Float = 0F,
                     var title: String = "",
                     var poster_path: String? = "",
                     var overview: String = "",
                     var backdrop_path: String? = ""
)
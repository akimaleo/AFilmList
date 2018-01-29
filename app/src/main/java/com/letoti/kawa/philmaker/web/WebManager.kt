package com.letoti.kawa.philmaker.web

import android.content.Context
import android.support.annotation.DrawableRes
import android.util.Log
import android.widget.ImageView

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.letoti.kawa.philmaker.BuildConfig
import com.letoti.kawa.philmaker.util.UserData
import com.letoti.kawa.philmaker.web.api.MovieAPI
import com.letoti.kawa.philmaker.web.service.MovieService
import com.squareup.picasso.Picasso

import java.util.Locale
import java.util.concurrent.TimeUnit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by akimaleo on 03.02.17.
 */
class WebManager private constructor() {

    private val INTERCEPTOR_QUERY = Interceptor { chain ->
        var request = chain.request()

        //LOCALIZATION
        val url = request.url().newBuilder()
                .addQueryParameter("locale", Locale.getDefault().language)
        val reqBuilder = request.newBuilder()

        //API KEY QUERY
        url.addQueryParameter("api_key", UserData.instance.accessToken)
        request = reqBuilder
                .url(url.build())
                .build()
        chain.proceed(request)
    }

    private var _retrofit: Retrofit? = null
    val retrofit: Retrofit
        get() {
            if (_retrofit == null) {
                _retrofit = Retrofit.Builder()
                        .baseUrl(URL_HOST)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
            }
            return _retrofit!!
        }

    private var client: OkHttpClient? = null
        get() {
            if (field == null) {
                val b = OkHttpClient.Builder()
                b.connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                b.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
                b.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                b.addNetworkInterceptor(INTERCEPTOR_QUERY)
                if (BuildConfig.DEBUG) {
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.level = HttpLoggingInterceptor.Level.BODY
                    b.addInterceptor(interceptor)
                }
                field = b.build()
            }
            return field!!
        }

    private val gson: Gson
        get() = GsonBuilder()
                .setLenient()
                .create()

    /**
     * Services list for HTTP requests
     */
    private var _movieService: MovieAPI? = null
    val movieService: MovieAPI
        get() {
            if (_movieService == null) {
                _movieService = MovieService()
            }
            return _movieService!!
        }

    companion object {
        private val URL_HOST = "https://api.themoviedb.org/4/"
        private val IMAGE_PREFIX = "https://image.tmdb.org/t/p/%s"

        private val CONNECT_TIMEOUT = 5
        private val WRITE_TIMEOUT = 50
        private val READ_TIMEOUT = 10

        val instance: WebManager by lazy { Holder.INSTANCE }
    }

    private object Holder {
        val INSTANCE = WebManager()
    }

    object ImageLoader {
        fun loadImage(context: Context, path: String, imageView: ImageView, imageSize: Size, @DrawableRes placeholder: Int) {
            Picasso.with(context)
                    .load(IMAGE_PREFIX.format(imageSize.rawValue) + path)
                    .placeholder(placeholder)
                    .into(imageView)

            if (BuildConfig.DEBUG)
                Log.i("IMAGE URL", IMAGE_PREFIX.format(imageSize.rawValue) + path)
        }

        enum class Size(val rawValue: String) {
            THUMBNAIL("w150"),
            MEDIUM("w500"),
            BIG("w1280")
        }
    }
}

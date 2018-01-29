package com.letoti.kawa.philmaker.view.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.letoti.kawa.philmaker.R
import com.letoti.kawa.philmaker.web.WebManager
import com.letoti.kawa.philmaker.web.entity.MovieItem

/**
 * Created by kawa on 29.01.2018.
 */

class MovieListAdapter(private var mDataSet: ArrayList<MovieItem>) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var mOnItemClickListener: ((position: Int, item: MovieItem) -> (Unit)) = { position, item -> }

    var dataSet: ArrayList<MovieItem>
        get() {
            return mDataSet
        }
        set(value) {
            mDataSet = value
        }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val background: View = view.findViewById(R.id.movie_item_background)
        val icon: ImageView = view.findViewById(R.id.movie_icon)
        val title: TextView = view.findViewById(R.id.movie_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.list_item_movie, parent, false)
        val vh = ViewHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mDataSet[position]
        WebManager.ImageLoader.loadImage(holder.icon.context,
                item.poster_path,
                holder.icon,
                WebManager.ImageLoader.Size.THUMBNAIL,
                R.drawable.ic_placeholder)
        holder.title.text = item.title
        holder.background.setOnClickListener { mOnItemClickListener(position, item) }
    }
}


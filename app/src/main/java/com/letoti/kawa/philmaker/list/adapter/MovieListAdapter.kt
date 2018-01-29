package com.letoti.kawa.philmaker.list.adapter

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.letoti.kawa.philmaker.R
import com.letoti.kawa.philmaker.web.entity.MovieDto
import com.letoti.kawa.philmaker.web.entity.MovieItem

/**
 * Created by kawa on 29.01.2018.
 */

class MovieListAdapter(private var mDataSet: ArrayList<MovieItem>) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var dataSet: ArrayList<MovieItem>
        get() {

            return mDataSet
        }
        set(value) {
            mDataSet = value
            notifyDataSetChanged()
        }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = mDataSet[position]
        holder!!.icon.setImageURI(Uri.parse(item.poster_path))
        holder.title.text = item.title
    }
}


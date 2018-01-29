package com.letoti.kawa.philmaker.common

import android.support.v7.widget.SearchView


/**
 * Created by kawa on 29.01.2018.
 */

open class BaseOnQueryTextListener : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}
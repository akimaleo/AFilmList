package com.letoti.kawa.philmaker.list

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import com.letoti.kawa.philmaker.R
import com.letoti.kawa.philmaker.common.BaseActivity

class MovieListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    //    SEARCH VIEW
    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }
        if (searchView != null) {
            searchView!!.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        initSearchView()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                title = ""
                return false
            }
            else -> {
            }
        }
//        initSearchView()
        return super.onOptionsItemSelected(item)
    }

    private fun initSearchView() {

        queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                //if want to do request on text change
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
//                receiveAllData()
                val view = currentFocus
                if (view != null) {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
                return true
            }
        }
        searchView!!.setOnQueryTextListener(queryTextListener)
        searchView!!.setOnCloseListener {
            //            setToolbarTitle(title)
//            receiveAllData()
            false
        }
    }

}

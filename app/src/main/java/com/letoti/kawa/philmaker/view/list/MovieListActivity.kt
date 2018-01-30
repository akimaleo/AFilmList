package com.letoti.kawa.philmaker.view.list

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import com.google.gson.Gson
import com.letoti.kawa.philmaker.R
import com.letoti.kawa.philmaker.view.common.BaseActivity
import com.letoti.kawa.philmaker.view.common.BaseOnQueryTextListener
import com.letoti.kawa.philmaker.view.common.dialog.DialogFactory
import com.letoti.kawa.philmaker.view.detailedinfo.MovieInfoActivity
import com.letoti.kawa.philmaker.view.list.adapter.EndlessRecyclerViewScrollListener
import com.letoti.kawa.philmaker.view.list.adapter.MovieListAdapter
import com.letoti.kawa.philmaker.web.entity.MovieShortDto
import kotlinx.android.synthetic.main.activity_main.*

class MovieListActivity : BaseActivity(), MovieListView {

    private lateinit var mListAdapter: MovieListAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var searchView: SearchView

    private lateinit var presenter: MovieListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MovieListPresenterImpl(this)

        mListAdapter = MovieListAdapter(ArrayList())
        mRecyclerView = recycler_view
        mLayoutManager = LinearLayoutManager(this)

        mRecyclerView.adapter = mListAdapter
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(mLayoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                getMore(page + 1)
            }
        })
        mListAdapter.mOnItemClickListener = { position, item ->
            val intent = Intent(this@MovieListActivity, MovieInfoActivity::class.java)
            intent.putExtra("movie_id", item.id)
            intent.putExtra("short_info", Gson().toJson(item))
            startActivity(intent)
        }
        refresh_layout.setOnRefreshListener { receiveCommonData() }
        receiveCommonData()
    }

    /**
     * Init search view
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
            if (::searchView.isInitialized)
                searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        val queryTextListener = object : BaseOnQueryTextListener() {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideSoftwareKeyboard()
                receiveCommonData()
                return true
            }
        }
        searchView.setOnQueryTextListener(queryTextListener)
        searchView.setOnCloseListener {
            receiveCommonData()
            false
        }
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Get first data
     */
    private fun receiveCommonData() {
        mListAdapter.dataSet.clear()
        mListAdapter.notifyDataSetChanged()
        presenter.getMovie(query, 1)
    }

    /**
     * User for requests with pagination
     */
    private fun getMore(page: Int) {
        presenter.getMovie(query, page)
        Log.i("PAGINATION", "page:= $page")
    }

    private val query: String
        get() = if (::searchView.isInitialized) searchView.query.toString() else ""

    override fun showMovieList(item: MovieShortDto) {
        mListAdapter.dataSet.clear()
        mListAdapter.dataSet = item.results
        mListAdapter.notifyDataSetChanged()
    }

    override fun addMovieList(item: MovieShortDto) {
        mListAdapter.dataSet.addAll(item.results)
        mListAdapter.notifyDataSetChanged()
    }

    override fun showLoadingProgress() {
        refresh_layout.isRefreshing = true
    }

    override fun hideLoadingProgress() {
        refresh_layout.isRefreshing = false
    }
}

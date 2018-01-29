package com.letoti.kawa.philmaker.list

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.letoti.kawa.philmaker.R
import com.letoti.kawa.philmaker.common.BaseActivity
import com.letoti.kawa.philmaker.common.BaseOnQueryTextListener
import com.letoti.kawa.philmaker.list.adapter.MovieListAdapter
import com.letoti.kawa.philmaker.web.entity.MovieDto
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

        refresh_layout.setOnRefreshListener { receiveCommonData() }

        receiveCommonData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
            if (::searchView.isInitialized)
                searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        initSearchView()
        return super.onCreateOptionsMenu(menu)
    }

    private fun receiveCommonData() {
        presenter.getMovie("", 1)
    }

    override fun showMovieList(item: MovieDto) {
        mListAdapter.dataSet.clear()
        mListAdapter.dataSet = item.results
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                return false
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initSearchView() {
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
    }

    override fun showLoadingProgress() {
        refresh_layout.isRefreshing = true
    }

    override fun hideLoadingProgress() {
        refresh_layout.isRefreshing = false
    }
}

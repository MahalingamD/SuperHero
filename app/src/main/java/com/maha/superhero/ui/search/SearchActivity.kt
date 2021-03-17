package com.maha.superhero.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maha.superhero.R
import com.maha.superhero.adapter.SuperHeroAdapter
import com.maha.superhero.data.model.SuperHeroResult
import com.maha.superhero.helper.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    lateinit var mSearchViewModel: SearchViewModel

    lateinit var mSuperHeroAdapter: SuperHeroAdapter

    var mSuperHeroList: ArrayList<SuperHeroResult> = arrayListOf()

    var mSearchText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        mSearchViewModel =
            ViewModelProvider(this, SearchViewModelFactory()).get(SearchViewModel::class.java)

        setAdapter()

        mSearchViewModel.mSuperHeroLiveDataList.observe(this, Observer<ArrayList<SuperHeroResult>> {

            if (mSearchText.isEmpty()) {
                no_superhero_txt.visibility = View.VISIBLE
                mSuperHeroAdapter.update(arrayListOf())
            } else {
                if (it.size > 0) {
                    no_superhero_txt.visibility = View.GONE
                    mSuperHeroAdapter.update(it ?: arrayListOf())
                } else {
                    no_superhero_txt.visibility = View.VISIBLE
                    mSuperHeroAdapter.update(arrayListOf())
                }
            }

            progbar_search_superhero.hide()
        })


        mSearchViewModel.aSuperHeroLiveData.observe(this, Observer<SuperHeroResult> {

            if (mSearchText.isEmpty()) {
                mSuperHeroAdapter.update(arrayListOf())
                no_superhero_txt.visibility = View.VISIBLE
            } else {
                if (it.error.isNotEmpty()) {
                    no_superhero_txt.visibility = View.VISIBLE
                    mSuperHeroAdapter.update(arrayListOf())
                } else {
                    no_superhero_txt.visibility = View.GONE
                    mSuperHeroList.clear()
                    mSuperHeroList.add(it)
                    mSuperHeroAdapter.update(mSuperHeroList)
                }
            }

            progbar_search_superhero.hide()
        })

        setSearchView()

        clickListener()
    }

    private fun clickListener() {

        img_info.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.res_string)))
            startActivity(Intent.createChooser(intent, "Choose browser"))
        }
    }


    private fun setSearchView() {

        edit_search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                try {
                    mSearchText = newText ?: ""
                    if (TextUtils.isEmpty(mSearchText)) {
                        mSuperHeroAdapter.update(arrayListOf())
                        progbar_search_superhero.hide()
                    } else {

                        if (isInternetOn(this@SearchActivity)) {
                            if (checkString(mSearchText)) {
                                checkProgressBar(progbar_search_superhero)
                                mSearchViewModel.getSuperHeroDetail(newText ?: "")
                            } else {
                                checkProgressBar(progbar_search_superhero)
                                mSearchViewModel.callSearchResult(mSearchText)
                            }
                        } else {
                            showAlert(this@SearchActivity, getString(R.string.alert_check_internet))
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                return true
            }
        })
    }


    private fun setAdapter() {
        try {

            no_superhero_txt.visibility = View.VISIBLE

            val myLayoutManager = LinearLayoutManager(this)
            superhero_rv.layoutManager = myLayoutManager
            superhero_rv.setHasFixedSize(true)
            mSuperHeroAdapter = SuperHeroAdapter(this, arrayListOf())
            superhero_rv.adapter = mSuperHeroAdapter
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
package com.studiounknown.ui.main

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.studiounknown.R
import com.studiounknown.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.cardSearch
import kotlinx.android.synthetic.main.activity_main.etSearch
import kotlinx.android.synthetic.main.activity_main.pbSearching
import kotlinx.android.synthetic.main.activity_main.rvHistory
import kotlinx.android.synthetic.main.activity_main.tvHistory
import kotlinx.android.synthetic.main.activity_main.tvSearchResult
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>() {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override val layoutId: Int = R.layout.activity_main

    override val viewModel by viewModel<MainViewModel>()

    private lateinit var weatherAdapter: WeatherAdapter

    override fun setupUI() {
        super.setupUI()
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                viewModel.search(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        weatherAdapter = WeatherAdapter {
            // TODO: handle item click
        }
        rvHistory.apply {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun setupViewModel() {
        super.setupViewModel()
        viewModel.recentWeathers.observe(this, Observer {
            if (it.isEmpty()) {
                tvHistory.visibility = View.INVISIBLE
                rvHistory.visibility = View.INVISIBLE
            } else {
                tvHistory.visibility = View.VISIBLE
                rvHistory.visibility = View.VISIBLE
            }
            weatherAdapter.submitList(it)
        })
        viewModel.weather.observe(this, Observer {
            if (it == null) {
                cardSearch.visibility = View.GONE
            } else {
                cardSearch.visibility = View.VISIBLE
                tvSearchResult.text = it.name
            }
        })
        viewModel.loading.observe(this, Observer {
            if (it.isLoading) {
                pbSearching.visibility = View.VISIBLE
            } else {
                pbSearching.visibility = View.INVISIBLE
            }
        })
    }
}

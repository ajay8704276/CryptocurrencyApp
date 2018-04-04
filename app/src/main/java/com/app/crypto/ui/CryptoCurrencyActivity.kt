package com.app.crypto.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.app.crypto.R
import com.app.crypto.data.Cryptocurrency
import com.app.crypto.utils.Constants
import com.app.crypto.utils.InfiniteScrollListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class CryptoCurrencyActivity : AppCompatActivity() {
    @Inject
    lateinit var cryptocurrenciesViewModelFactory : CryptocurrenciesViewModelFactory

    var adapter = CryptoCurrencyAdapter(ArrayList())
    lateinit var cryptoCurrencyViewModel: CryptoCurrencyViewModel
    var currentPage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        initializeRecycler()

        cryptoCurrencyViewModel = ViewModelProviders.of(this,cryptocurrenciesViewModelFactory).get(CryptoCurrencyViewModel::class.java)

        progressBar.visibility = View.VISIBLE

        loadData()

        cryptoCurrencyViewModel.cryptocurrenciesResult().observe(this, Observer<List<Cryptocurrency>> {
            if (it !=null){
                val position = adapter.itemCount
                adapter.addCryptocurrencies(it)
                recycler.adapter = adapter
                recycler.scrollToPosition(position - Constants.LIST_SCROLLING)
            }
        })


        cryptoCurrencyViewModel.cryptocurrenciesError().observe(this, Observer<String> {
            if (it != null) {
                Toast.makeText(this, resources.getString(R.string.cryptocurrency_error_message) + it,
                        Toast.LENGTH_SHORT).show()
            }
        })

        cryptoCurrencyViewModel.cryptocurrenciesLoader().observe(this, Observer<Boolean> {
            if (it == false) progressBar.visibility = View.GONE
        })

    }



    private fun initializeRecycler() {

        val gridLayoutManager = GridLayoutManager(this,1)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            addOnScrollListener(InfiniteScrollListener({ loadData() }, gridLayoutManager))
        }
    }

    private fun loadData() {

        cryptoCurrencyViewModel.loadCryptocurrencies(Constants.LIMIT,currentPage*Constants.OFFSET)
        currentPage++
    }

    override fun onDestroy() {
        cryptoCurrencyViewModel.disposeElements()
        super.onDestroy()
        
    }

}

package com.app.crypto.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.app.crypto.R
import com.app.crypto.data.Cryptocurrency

class CryptoCurrencyAdapter(crytoCurrencies: List<Cryptocurrency>?) : RecyclerView.Adapter<CryptoCurrencyAdapter.CryptoCurrencyViewHolder>() {

    private var mCryptoCurrencyList = ArrayList<Cryptocurrency>()

    /**
     * Initialise the variable using constructor using init keyword
     */
    init {
        this.mCryptoCurrencyList = crytoCurrencies as ArrayList<Cryptocurrency>
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):CryptoCurrencyViewHolder {
        var itemView = LayoutInflater.from(parent?.context).inflate(R.layout.cryptocurrency_list_item,
                parent, false)
        return CryptoCurrencyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mCryptoCurrencyList.size
    }

    override fun onBindViewHolder(holder: CryptoCurrencyViewHolder?, position: Int) {

        val cryptocurrencyItem = mCryptoCurrencyList[position]
        holder?.cryptocurrencyListItem(cryptocurrencyItem)
    }


    fun addCryptocurrencies(cryptocurrencies: List<Cryptocurrency>) {
        val initPosition = mCryptoCurrencyList.size
        mCryptoCurrencyList.addAll(cryptocurrencies)
        notifyItemRangeInserted(initPosition, mCryptoCurrencyList.size)
    }

    class CryptoCurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cryptocurrencyId = itemView.findViewById<TextView>(R.id.cryptocurrency_id)

        fun cryptocurrencyListItem(cryptocurrencyItem: Cryptocurrency) {
            cryptocurrencyId.text = cryptocurrencyItem.id.toString()

        }

    }

}
package com.app.crypto.data.source.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.app.crypto.data.Cryptocurrency

/**
 * Created by admin on 04/04/18.
 */

@Dao
interface CryptoCurrencyDao {

    @Query("SELECT * FROM cryptocurrency")
    fun queryCryptoCurrencies(limit: Int, offset: Int):LiveData<List<Cryptocurrency>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCryptoCurrencies(cryptocurrency: Cryptocurrency)
}
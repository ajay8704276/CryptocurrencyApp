package com.app.crypto.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.app.crypto.data.Cryptocurrency

/**
 * Created by admin on 04/04/18.
 */

@Database(entities = arrayOf(Cryptocurrency::class),version = 1)
abstract class Database : RoomDatabase() {

    abstract fun cryptoCurrencyDao(): CryptoCurrencyDao
}
package com.example.financeapp.data.database

import android.content.Context
import androidx.room.*
import androidx.room.TypeConverters
import com.example.financeapp.data.dao.TransactionDao
import com.example.financeapp.data.dao.UserDao
import com.example.financeapp.data.model.Transaction
import com.example.financeapp.data.model.User
import com.example.financeapp.utils.Converters

@Database(entities = [User::class, Transaction::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "finance_app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


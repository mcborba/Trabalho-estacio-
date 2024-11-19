package com.example.financeapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.financeapp.data.model.Transaction
import java.util.Date

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction)

    @Update
    suspend fun update(transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)

    @Query("SELECT * FROM transactions WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getTransactionsBetween(startDate: Date, endDate: Date): LiveData<List<Transaction>>

    @Query("SELECT SUM(amount) FROM transactions WHERE type = :type AND date BETWEEN :startDate AND :endDate")
    fun getTotalAmountByType(type: String, startDate: Date, endDate: Date): LiveData<Double>
}


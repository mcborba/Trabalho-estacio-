package com.example.financeapp.data.repository

import androidx.lifecycle.LiveData
import com.example.financeapp.data.dao.TransactionDao
import com.example.financeapp.data.model.Transaction
import java.util.Date

class TransactionRepository(private val transactionDao: TransactionDao) {

    fun getTransactionsBetween(startDate: Date, endDate: Date): LiveData<List<Transaction>> {
        return transactionDao.getTransactionsBetween(startDate, endDate)
    }

    fun getTotalAmountByType(type: String, startDate: Date, endDate: Date): LiveData<Double> {
        return transactionDao.getTotalAmountByType(type, startDate, endDate)
    }

    suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    suspend fun update(transaction: Transaction) {
        transactionDao.update(transaction)
    }

    suspend fun delete(transaction: Transaction) {
        transactionDao.delete(transaction)
    }
}


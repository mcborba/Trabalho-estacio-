package com.example.financeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.data.model.Transaction
import com.example.financeapp.data.repository.TransactionRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import java.util.Date

class TransactionViewModel(private val repository: TransactionRepository) : ViewModel() {

    fun getTransactionsBetween(startDate: Date, endDate: Date): LiveData<List<Transaction>> {
        return repository.getTransactionsBetween(startDate, endDate)
    }

    fun getTotalAmountByType(type: String, startDate: Date, endDate: Date): LiveData<Double> {
        return repository.getTotalAmountByType(type, startDate, endDate)
    }

    fun insert(transaction: Transaction) {
        viewModelScope.launch {
            repository.insert(transaction)
        }
    }

    fun update(transaction: Transaction) {
        viewModelScope.launch {
            repository.update(transaction)
        }
    }

    fun delete(transaction: Transaction) {
        viewModelScope.launch {
            repository.delete(transaction)
        }
    }
}


package com.example.financeapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String, // "income" or "expense"
    val amount: Double,
    val category: String,
    val date: Date,
    val description: String?
)


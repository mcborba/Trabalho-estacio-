package com.example.financeapp.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.financeapp.R
import com.example.financeapp.data.database.AppDatabase
import com.example.financeapp.data.repository.TransactionRepository
import com.example.financeapp.ui.viewmodel.TransactionViewModel
import com.example.financeapp.ui.viewmodel.TransactionViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val transactionViewModel: TransactionViewModel by viewModels {
        TransactionViewModelFactory(TransactionRepository(AppDatabase.getDatabase(this).transactionDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        btnAddTransaction.setOnClickListener {
            startActivity(Intent(this, AddTransactionActivity::class.java))
        }

        btnBudget.setOnClickListener {
            startActivity(Intent(this, BudgetActivity::class.java))
        }

        btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        // Define o intervalo de tempo para a exibição das transações
        val calendar = Calendar.getInstance()
        val endDate = calendar.time
        calendar.add(Calendar.MONTH, -1)
        val startDate = calendar.time

        transactionViewModel.getTransactionsBetween(startDate, endDate).observe(this, Observer { transactions ->
            // Atualize a UI com a lista de transações
            // Por exemplo, usando um RecyclerView
        })

        // Exemplo de visualização de total de receitas e despesas
        transactionViewModel.getTotalAmountByType("income", startDate, endDate).observe(this, Observer { totalIncome ->
            tvTotalIncome.text = "Receitas: R$ ${String.format("%.2f", totalIncome ?: 0.0)}"
        })

        transactionViewModel.getTotalAmountByType("expense", startDate, endDate).observe(this, Observer { totalExpense ->
            tvTotalExpense.text = "Despesas: R$ ${String.format("%.2f", totalExpense ?: 0.0)}"
        })
    }
}


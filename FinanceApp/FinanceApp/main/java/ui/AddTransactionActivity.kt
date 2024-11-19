package com.example.financeapp.ui.main

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp.R
import com.example.financeapp.data.database.AppDatabase
import com.example.financeapp.data.model.Transaction
import com.example.financeapp.data.repository.TransactionRepository
import com.example.financeapp.ui.viewmodel.TransactionViewModel
import com.example.financeapp.ui.viewmodel.TransactionViewModelFactory
import kotlinx.android.synthetic.main.activity_add_transaction.*
import java.text.SimpleDateFormat
import java.util.*

class AddTransactionActivity : AppCompatActivity() {

    private val transactionViewModel: TransactionViewModel by viewModels {
        TransactionViewModelFactory(TransactionRepository(AppDatabase.getDatabase(this).transactionDao()))
    }

    private var selectedDate: Date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        // Configurar Spinner para tipo de transação
        val types = listOf("Receita", "Despesa")
        val typeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerType.adapter = typeAdapter

        // Configurar Spinner para categorias
        val categories = listOf("Alimentação", "Transporte", "Saúde", "Lazer", "Outros")
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = categoryAdapter

        // Configurar DatePicker
        btnSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    selectedDate = calendar.time
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    tvSelectedDate.text = sdf.format(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        btnSaveTransaction.setOnClickListener {
            val type = if(spinnerType.selectedItem.toString() == "Receita") "income" else "expense"
            val amount = etAmount.text.toString().toDoubleOrNull()
            val category = spinnerCategory.selectedItem.toString()
            val description = etDescription.text.toString()

            if(amount != null && amount > 0) {
                val transaction = Transaction(
                    type = type,
                    amount = amount,
                    category = category,
                    date = selectedDate,
                    description = description
                )
                transactionViewModel.insert(transaction)
                Toast.makeText(this, "Transação adicionada com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Por favor, insira um valor válido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


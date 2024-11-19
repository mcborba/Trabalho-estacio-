// Placeholder content for BudgetActivity.ktpackage com.example.financeapp.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp.R
import com.example.financeapp.data.database.AppDatabase
import com.example.financeapp.data.repository.TransactionRepository
import com.example.financeapp.ui.viewmodel.TransactionViewModel
import com.example.financeapp.ui.viewmodel.TransactionViewModelFactory
import kotlinx.android.synthetic.main.activity_budget.*

class BudgetActivity : AppCompatActivity() {

    private val transactionViewModel: TransactionViewModel by viewModels {
        TransactionViewModelFactory(TransactionRepository(AppDatabase.getDatabase(this).transactionDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget)

        btnSetBudget.setOnClickListener {
            val category = spinnerCategory.selectedItem.toString()
            val amount = etBudgetAmount.text.toString().toDoubleOrNull()

            if(amount != null && amount > 0) {
                // Implementar lógica para salvar o orçamento definido
                // Por exemplo, armazenar em SharedPreferences ou em uma tabela específica
                Toast.makeText(this, "Orçamento definido para $category: R$ $amount", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, insira um valor válido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

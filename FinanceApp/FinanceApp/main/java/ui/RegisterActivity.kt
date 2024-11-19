package com.example.financeapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp.R
import com.example.financeapp.data.database.AppDatabase
import com.example.financeapp.data.model.User
import com.example.financeapp.data.repository.UserRepository
import com.example.financeapp.ui.viewmodel.UserViewModel
import com.example.financeapp.ui.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserRepository(AppDatabase.getDatabase(this).userDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if(username.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if(password == confirmPassword) {
                    val user = User(username = username, password = password)
                    userViewModel.register(user)
                    Toast.makeText(this, "Registro bem-sucedido!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


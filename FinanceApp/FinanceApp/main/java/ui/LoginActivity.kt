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
import com.example.financeapp.ui.main.MainActivity
import com.example.financeapp.ui.viewmodel.UserViewModel
import com.example.financeapp.ui.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserRepository(AppDatabase.getDatabase(this).userDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if(username.isNotEmpty() && password.isNotEmpty()) {
                userViewModel.login(username, password)
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        userViewModel.loginResult.observe(this, { user ->
            if(user != null) {
                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
            }
        })
    }
}


package com.example.financeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.data.model.User
import com.example.financeapp.data.repository.UserRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<User?>()
    val loginResult: LiveData<User?> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val user = repository.login(username, password)
            _loginResult.postValue(user)
        }
    }

    fun register(user: User) {
        viewModelScope.launch {
            repository.register(user)
        }
    }
}


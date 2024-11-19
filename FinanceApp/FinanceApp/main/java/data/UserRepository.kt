package com.example.financeapp.data.repository

import com.example.financeapp.data.dao.UserDao
import com.example.financeapp.data.model.User

class UserRepository(private val userDao: UserDao) {

    suspend fun login(username: String, password: String): User? {
        return userDao.login(username, password)
    }

    suspend fun register(user: User) {
        userDao.insert(user)
    }
}


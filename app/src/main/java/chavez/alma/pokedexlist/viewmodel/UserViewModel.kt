package com.tu_paquete.pokedexlist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tu_paquete.pokedexlist.model.data.Users

class UserViewModel(private val users: Users) : ViewModel() {

    var loginError by mutableStateOf(false)
        private set

    var errorDescription by mutableStateOf("")
        private set

    fun login(email: String, pass: String): Boolean {
        val user = users.loginService(email, pass)
        return if (user != null) {
            users.saveUser(user.email, user.name)
            loginError = false
            errorDescription = ""
            true
        } else {
            loginError = true
            errorDescription = "Usuario o contraseña incorrectos"
            false
        }
    }

    fun isLoggedIn(): Boolean {
        return users.isLoggedIn()
    }

    fun logout() {
        users.logout()
    }
}
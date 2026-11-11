package com.tu_paquete.pokedexlist.model.data

import android.content.Context
import com.tu_paquete.pokedexlist.model.preferences.UserPreferences

class Users(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences(
        UserPreferences.ARCHIVO,
        Context.MODE_PRIVATE
    )

    // Lista con al menos 3 usuarios predefinidos
    private val usersList = listOf(
        User("ash@ketchum.com", "pikachu123", "Ash Ketchum"),
        User("misty@cerulean.com", "starmie123", "Misty"),
        User("brock@pewter.com", "onix123", "Brock")
    )

    fun getUserByMail(mail: String): User? {
        return usersList.find { it.email.equals(mail, ignoreCase = true) }
    }

    fun loginService(email: String, pass: String): User? {
        val user = getUserByMail(email)
        return if (user != null && user.pass == pass) user else null
    }

    fun saveUser(user: String, name: String) {
        sharedPreferences.edit().apply {
            putString(UserPreferences.USER, user)
            putString(UserPreferences.USERNAME, name)
            apply()
        }
    }

    fun isLoggedIn(): Boolean {
        val email = sharedPreferences.getString(UserPreferences.USER, null)
        val name = sharedPreferences.getString(UserPreferences.USERNAME, null)
        return !email.isNullOrEmpty() && !name.isNullOrEmpty()
    }

    fun logout() {
        sharedPreferences.edit().apply {
            putString(UserPreferences.USER, null)
            putString(UserPreferences.USERNAME, null)
            apply()
        }
    }
}
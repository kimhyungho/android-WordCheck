package com.appdev.wordcheck.util

import com.appdev.wordcheck.WordCheckApplication

object LoginPreference {
    fun isAutoLoginSet(): Boolean =
        WordCheckApplication.preferences.getBoolean("auto_login", false)

    fun setAutoLogin(boolean: Boolean) {
        WordCheckApplication.preferences.setBoolean("auto_login", boolean)
    }

    fun setUserPreference(account_token: String) {
        WordCheckApplication.preferences.setString("account_token", account_token)
    }

    fun getUserAccountToken(): String {
        return WordCheckApplication.preferences.getString("account_token", "null")
    }

    fun logout() {
        WordCheckApplication.preferences.allDelete()
    }
}
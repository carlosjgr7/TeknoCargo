package com.delivery.tecnokargo.core.data.preferences

data class PreferencesKey<T>(val key: String, val defaultValue: T) {
    companion object {
        val USER_NAME = PreferencesKey("user_name", "")
        val IS_USER_LOGGED_IN = PreferencesKey("is_user_logged_in", false)
    }
}


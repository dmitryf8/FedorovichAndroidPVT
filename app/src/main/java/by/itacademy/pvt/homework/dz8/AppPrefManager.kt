package by.itacademy.pvt.homework.dz8

import android.content.Context
import android.content.SharedPreferences

private const val SHARED_PREFS_NAME = "kldnnsnnndlssk"
private const val TEXT_KEY = "jdnvdksk"

class AppPrefManager(private val context: Context) {

    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    fun saveUserText(text: String) {
        sharedPrefs
            .edit()
            .putString(TEXT_KEY, text)
            .apply()
    }

    fun getUserText(): String {
        return sharedPrefs.getString(TEXT_KEY, "") ?: ""
    }
}
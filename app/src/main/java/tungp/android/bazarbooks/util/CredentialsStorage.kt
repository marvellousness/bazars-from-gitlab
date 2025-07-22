package tungp.android.bazarbooks.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import androidx.core.content.edit

class CredentialsStorage(context: Context) {
    private val sharedPreferences: SharedPreferences

    companion object {
        private const val PREFS_NAME = "bazarbooks_credentials"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
    }

    init {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        sharedPreferences = EncryptedSharedPreferences.create(
            PREFS_NAME,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveCredentials(email: String?, password: String?) {
        sharedPreferences.edit {
            putString(KEY_EMAIL, email)
                .putString(KEY_PASSWORD, password)
        }
    }

    fun getCredentials(): Pair<String, String>? {
        val email = sharedPreferences.getString(KEY_EMAIL, null)
        val password = sharedPreferences.getString(KEY_PASSWORD, null)
        return if (email != null && password != null) Pair(email, password) else null
    }

    fun clearCredentials() {
        sharedPreferences.edit { clear() }
    }
} 
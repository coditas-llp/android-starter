package com.coditas.example.data.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.coditas.example.utils.AppConstants.PREF_TOKEN_FILE
import com.coditas.example.utils.AppConstants.USER_ID
import com.coditas.example.utils.AppConstants.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AccessTokenSharedPreference @Inject constructor(@ApplicationContext context: Context) {

    private val mMasterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

     private var mSharedPreference = EncryptedSharedPreferences.create(
        PREF_TOKEN_FILE,
        mMasterKey,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveToken(token: String?) {
        val editor = mSharedPreference.edit()
        editor.run {
            putString(USER_TOKEN, token)
            apply()
        }
    }

    fun deleteToken() {
        val editor = mSharedPreference.edit()
        editor.run {
            clear()
            apply()
        }
    }

    fun saveUserId(userId: Int?) {
        val editor = mSharedPreference.edit()
        editor.run {
            putInt(USER_ID, userId ?: 0)
            apply()
        }
    }

    fun getToken(): String? {
        return mSharedPreference.getString(USER_TOKEN, null)
    }

    fun getUserId(): Int {
        return mSharedPreference.getInt(USER_ID, 0)
    }
}
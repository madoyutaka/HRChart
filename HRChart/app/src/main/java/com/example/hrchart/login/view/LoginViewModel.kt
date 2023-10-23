package com.example.hrchart.login.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *  ログイン画面 ViewModel
 *  画面ID:
 *
 *  @author Y.Sato
 *  created on 2023/10/10
 */
class LoginViewModel : ViewModel() {

    companion object {
        /** TAG */
        private const val TAG = "LoginViewModel"
    }

    // パスワード入力の検知
    private val _isEnabled: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().also { mutableLiveData ->
            mutableLiveData.value = false
        }
    val isEnabled: LiveData<Boolean>
        get() = _isEnabled

    private val _buttonText: MutableLiveData<String> =
        MutableLiveData<String>().also { mutableLiveData ->
            mutableLiveData.value = "パスワードを入力してください。"
        }
    val buttonText: LiveData<String>
        get() = _buttonText

    // ボタン切り替え
    fun updateButton(isBlank: Boolean) {
        _isEnabled.value = !isBlank

        if (!isBlank) {
            _buttonText.value = "ログイン"
        } else {
            _buttonText.value = "パスワードを入力してください。"
        }
    }

    // ログインボタン押下時
    fun onLoginClicked(inputPassword: String): String {
        Log.d(TAG, "onLoginClicked")
        // パスワードの正規表現
        val regex = Regex(pattern = "[a-z0-9!@;:]{6,}")
        val isMatched = regex.containsMatchIn(inputPassword)
        return if (!isMatched) {
            "noMatch"
        } else {
            passwordVerify(inputPassword)
        }

    }

    // パスワード確認
    private fun passwordVerify(inputPassword: String): String {
        // テストデータ用のパスワード(DBから取得予定)
        val userPassword = "gwuser"
        val adminPassword = "adminuser"

        return when (inputPassword) {
            userPassword -> {
                "user"
            }

            adminPassword -> {
                "admin"
            }

            else -> {
                "error"
            }
        }
    }
}
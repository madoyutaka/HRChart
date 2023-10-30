package com.example.hrchart.login.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hrchart.login.model.LoginRepository
import kotlinx.coroutines.launch

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

    /** LoginRepository */
    private val loginRepository: LoginRepository = LoginRepository()

    /** PasswordList */
    private val passwordList: MutableList<String> = mutableListOf()

    /**
     * init
     */
    init {
        Log.d(TAG, "init")
        // DB内のデータ取得
        getPasswordList()
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
        if (!isMatched) {
            "noMatch"
        }
        return passwordVerify(inputPassword)
    }

    //サーバーからデータ取得
    private fun getPasswordList(){
        viewModelScope.launch {
            try {
                Log.d(TAG, "getPasswordList")
                // デシリアライズしたJsonデータを取得
                val passwordData = loginRepository.getLoginPasswordData()
                passwordList.add(passwordData.general)
                passwordList.add(passwordData.admin)
                Log.d(TAG, "$passwordList")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // パスワード確認
    private fun passwordVerify(inputPassword: String): String {
        Log.d(TAG, "passwordVerify")
        val generalPasswordData = passwordList[0]
        val adminPasswordData = passwordList[1]
        val loginType = when (inputPassword) {
            generalPasswordData -> {
                "user"
            }

            adminPasswordData -> {
                "admin"
            }

            else -> {
                "error"
            }
        }
        Log.d(TAG,"$loginType")
        return loginType
    }
}
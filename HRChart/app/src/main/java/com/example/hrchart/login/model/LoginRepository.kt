package com.example.hrchart.login.model

import android.util.Log
import com.example.hrchart.login.data.Passwords
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * ログイン用リポジトリ
 *
 * @author Y.Sato
 * created on 2023/10/23
 */
class LoginRepository()  {
    private val loginService: LoginService

    init {
        // Moshi初期化
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        // Retrofit初期化
        val retrofit = Retrofit.Builder()
            .baseUrl("https://hrcarte-default-rtdb.firebaseio.com/") // json取得先URL
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        loginService = retrofit.create(LoginService::class.java)
    }

    /**
     * getLoginPasswordData
     * InterfaceのGETメソッドを返す
     */
    suspend fun getLoginPasswordData(): Passwords {
        Log.d("Repository", "getLoginPasswordData")
        return loginService.getLoginData()
    }
}
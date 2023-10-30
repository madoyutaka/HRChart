package com.example.hrchart.login.model

import com.example.hrchart.login.data.Passwords
import retrofit2.http.GET

/**
 * WebAPI通信用Interface
 *
 * @author Y.Sato
 * created on 2023/10/23
 */
interface LoginService {

    /**
     * getLoginData
     * パスワードデータを取得
     */
    @GET("/passwords.json")
    suspend fun getLoginData(): Passwords

}
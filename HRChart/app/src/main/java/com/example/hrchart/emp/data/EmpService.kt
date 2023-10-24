package com.example.hrchart.emp.data

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * WebAPI通信用Interface
 *
 * @author K.Takahashi
 * created on 2023/10/23
 */
interface EmpService {

    /**
     * getEmpData
     * 全従業員データを取得
     */
    @GET("/.json")
    suspend fun getEmpData(): EmpDataResponse

    /**
     * getEmpInfo
     * 選択した従業員データを取得
     */
    @GET("/empData/u{id}.json")
    suspend fun getEmpInfo(@Path("id") id: Int): EmpData
}
package com.example.hrchart.emp.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * 従業員画面用リポジトリ
 *
 * @author K.Takahashi
 * created on 2023/10/23
 */
class EmpRepository {

    private val empService: EmpService

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

        empService = retrofit.create(EmpService::class.java)
    }

    /**
     * getEmpListData
     * InterfaceのGETメソッドを返す(全従業員リスト)
     */
    suspend fun getEmpListData(): EmpDataResponse {
        return empService.getEmpData()
    }

    /**
     * getEmpInfo
     * InterfaceのGETメソッドを返す(従業員詳細情報)
     */
    suspend fun getEmpInfo(id: Int): EmpData {
        return empService.getEmpInfo(id)
    }

    /**
     * getStatuses
     * InterfaceのGETメソッドを返す(ステータス)
     */
    suspend fun getStatuses(): Statuses {
        return empService.getStatuses()
    }

    /**
     * getAreas
     * InterfaceのGETメソッドを返す(エリア)
     */
    suspend fun getAreas(): Areas {
        return empService.getAreas()
    }

    /**
     * getJobs
     * InterfaceのGETメソッドを返す(職種)
     */
    suspend fun getJobs(): Jobs {
        return empService.getJobs()
    }
}
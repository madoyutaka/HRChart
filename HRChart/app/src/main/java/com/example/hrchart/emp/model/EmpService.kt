package com.example.hrchart.emp.model

import com.example.hrchart.emp.data.Areas
import com.example.hrchart.emp.data.EmpData
import com.example.hrchart.emp.data.Jobs
import com.example.hrchart.emp.data.Statuses
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
    @GET("/employees.json")
    suspend fun getEmpData(): EmpDataResponse

    /**
     * getEmpInfo
     * 選択した従業員詳細情報を取得
     */
    @GET("/employees/empData/u{id}.json")
    suspend fun getEmpInfo(@Path("id") id: Int): EmpData

    /**
     * getStatuses
     * ステータスを取得
     */
    @GET("/statuses.json")
    suspend fun getStatuses(): Statuses

    /**
     * getAreas
     * エリアを取得
     */
    @GET("/areas.json")
    suspend fun getAreas(): Areas

    /**
     * getJobs
     * 職種を取得
     */
    @GET("/jobs.json")
    suspend fun getJobs(): Jobs
}
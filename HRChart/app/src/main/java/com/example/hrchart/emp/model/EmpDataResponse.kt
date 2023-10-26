package com.example.hrchart.emp.model

import com.example.hrchart.emp.data.EmpData

/**
 * 従業員データレスポンス用データクラス
 * ※Jsonデータのルート要素がオブジェクトのため作成
 *
 * @author K.Takahashi
 * created on 2023/10/23
 */
data class EmpDataResponse(
    val empData: Map<String, EmpData>
)
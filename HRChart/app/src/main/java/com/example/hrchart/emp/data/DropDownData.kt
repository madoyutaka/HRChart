package com.example.hrchart.emp.data

/**
 * 従業員検索画面 ドロップダウンリスト用データクラス
 *
 * @author K.Takahashi
 * created on 2023/10/25
 */
data class DropDownData(
    var statuses: Statuses, // ステータス
    var areas: Areas, // エリア
    var jobs: Jobs // 職種
)

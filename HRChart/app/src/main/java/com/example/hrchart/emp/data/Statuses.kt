package com.example.hrchart.emp.data

/**
 * ステータス データクラス
 *
 * @author K.Takahashi
 * created on 2023/10/25
 */
data class Statuses (
    val statusAll: String, // すべて
    val enrollment: String, // 在職
    val retirement: String, // 退職
    val loa: String, // 休職
    val decline: String, // 辞退
    val decision: String, // 内定
    val likelihood: String // 見込み
)
package com.example.hrchart.emp.data

/**
 * 職種 データクラス
 *
 * @author K.Takahashi
 * created on 2023/10/25
 */
data class Jobs(
    val jobsAll: String, // すべて
    val engineer: String, // 開発エンジニア
    val sales: String, // 営業
    val management : String, // 管理
    val network: String // ネットワーク
)

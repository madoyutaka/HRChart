package com.example.hrchart.emp.data


/**
 * 従業員画面用データクラス
 *
 * @author K.Takahashi
 * created on 2023/10/10
 */
data class EmpData(
    var id: Int, // ID
    var empName: String, // 名前
    var phonetic: String, // フリガナ
    var birth: String, // 生年月日
    var gender: String, // 性別
    var age: String, // 年齢
    var media: String, // 媒体
    var job: String, // 職種
    var area: String, // エリア
    var status: String, // ステータス
    var skill: String, // スキル
    var solution: String, // ソリューション
    var empStatus: String, // 雇用形態
    var remarks: String, // 備考欄
    var officePhone: String, // 会社用携帯
    var officeMail: String, // 会社用メール
    var privatePhone: String, // 個人用携帯
    var privateMail: String, // 個人用メール
    var pic1: String, // 面接担当者1
    var pic2: String, // 面接担当者2
    var applicationDate: String, // 応募日
    var interviewDate: String, // 面談日
    var joinedDate: String, // 入社日
    var retirementDate: String, // 退社日
    var acceptedDate: String, // 内定承諾日
    var decisionMonth: String, // 決定月
    var divApplication: String, // 応募区分
    var rank: String, // ランク
)

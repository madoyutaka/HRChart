package com.example.hrchart.emp.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hrchart.emp.data.EmpData

/**
 * 従業員情報画面 ViewModel
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/10
 */
class EmpInfoViewModel : ViewModel() {

    companion object {
        /** TAG */
        private const val TAG = "EmpInfoViewModel"
    }

    /** 従業員情報(LiveData) */
    val empInfo: MutableLiveData<EmpData> = MutableLiveData()
    /** ID */
    private var id: Int = 0

    /**
     * showInfo
     * 従業員一覧画面で選択された従業員情報を表示
     */
    fun showInfo(id: Int) {
        Log.d(TAG, "showInfo")
        this.id = id

        // テストデータ(DB代わり)
        val list = arrayListOf(
            EmpData(1, "山田 太郎", "ヤマダ タロウ", "2001/08/09", "男", "22", "マイナビ新卒", "開発エンジニア", "東京", "在職" ,
                "", "", "社員","基本給￥〇〇", "", "", "", "", "", "",
                "", "", "2024/04/01", "", "", "2023/07/01", "新卒" ,"C"),
            EmpData(2, "鈴木 一郎", "スズキ イチロウ", "1977/04/25", "男", "46", "エン転職", "営業", "東京", "在職" ,
                "", "", "社員","契約社員", "", "", "", "", "", "",
                "", "", "2023/12/01", "", "", "2023/09/13", "媒体" ,"S"),
        )

        // 選択されたIDと一致する従業員情報をデータクラスにセット
        val selEmp = list.find { it.id == id }
        if (selEmp != null) {
            val empData = EmpData(
                selEmp.id, selEmp.empName, selEmp.phonetic, selEmp.birth,
                selEmp.gender, selEmp.age, selEmp.media, selEmp.job, selEmp.area, selEmp.status,
                selEmp.skill, selEmp.solution, selEmp.empStatus, selEmp.remarks, selEmp.officePhone,selEmp.officeMail,
                selEmp.privatePhone, selEmp.privateMail, selEmp.pic1, selEmp.pic2, selEmp.applicationDate, selEmp.interviewDate,
                selEmp.joinedDate, selEmp.retirementDate, selEmp.acceptedDate, selEmp.decisionMonth, selEmp.divApplication, selEmp.rank
            )
            empInfo.postValue(empData)
        } else {
            // IDが見つからない場合
            // 落ちるので一旦コメントアウト
//            throw Exception("Employee not found with ID $id")
        }
    }

}
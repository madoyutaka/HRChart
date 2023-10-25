package com.example.hrchart.emp.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hrchart.emp.data.EmpData
import com.example.hrchart.emp.data.EmpRepository
import kotlinx.coroutines.launch

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
    /** EmpRepository */
    private val empRepository: EmpRepository = EmpRepository()

    /**
     * showInfo
     * 従業員一覧画面で選択された従業員情報を表示
     */
    fun showInfo(id: Int) {
        Log.d(TAG, "showInfo")
        this.id = id

        // 非同期処理
        viewModelScope.launch {
            try {
                // デシリアライズしたJsonデータを取得
                val data = empRepository.getEmpInfo(id)
                empInfo.postValue(data)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }

    }

}
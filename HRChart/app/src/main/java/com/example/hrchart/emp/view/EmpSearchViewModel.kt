package com.example.hrchart.emp.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hrchart.common.Event
import com.example.hrchart.emp.data.Areas
import com.example.hrchart.emp.data.DropDownData
import com.example.hrchart.emp.data.EmpRepository
import kotlinx.coroutines.launch


/**
 * 従業員検索画面 ViewModel
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/12
 */
class EmpSearchViewModel: ViewModel() {

    companion object {
        /** TAG */
        private const val TAG = "EmpSearchViewModel"
    }

    /** EmpRepository */
    private val empRepository: EmpRepository = EmpRepository()

    /** 検索条件(LiveData) */
    private val onSearchArray: MutableLiveData<Event<Array<String>>> = MutableLiveData()

    /**
     * getOnSearchArray
     * 検索条件をFragmentに渡す
     */
    fun getOnSearchArray(): MutableLiveData<Event<Array<String>>> {return onSearchArray}

    /** ドロップダウンのアイテム(LiveData) */
    private val dropDownData: MutableLiveData<Event<DropDownData>> = MutableLiveData()

    /**
     * getDropDownData
     * ドロップダウンのアイテムをFragmentに渡す
     */
    fun getDropDownData(): MutableLiveData<Event<DropDownData>> {return dropDownData}

    /**
     * getDropDownItem
     * ドロップダウンのアイテムをDBから取得する
     */
    fun getDropDownItem() {
        Log.d(TAG, "getDropDownItem")
        // 非同期処理
        viewModelScope.launch {
            try {
                // デシリアライズしたJsonデータを取得
                val statuses = empRepository.getStatuses() // ステータス
                val areas = empRepository.getAreas() // エリア
                val jobs = empRepository.getJobs() // 職種

                dropDownData.postValue(Event(DropDownData(statuses, areas, jobs)))

            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    /**
     * onClickSearch
     * 検索ボタンのクリックイベント
     * @param searchArray Array<String>
     */
    fun onClickSearch(searchArray: Array<String>) {
        Log.d(TAG, "onClickSearch")
        onSearchArray.postValue(Event(searchArray))
    }

}
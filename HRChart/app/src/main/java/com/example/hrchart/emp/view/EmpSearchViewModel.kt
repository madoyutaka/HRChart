package com.example.hrchart.emp.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hrchart.common.Event


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

    /**
     * 検索条件(LiveData)
     */
    private val onSearchArray: MutableLiveData<Event<Array<String>>> = MutableLiveData()

    /**
     * getOnSearchArray
     * 検索条件をFragmentに渡す
     */
    fun getOnSearchArray(): MutableLiveData<Event<Array<String>>> {return onSearchArray}


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
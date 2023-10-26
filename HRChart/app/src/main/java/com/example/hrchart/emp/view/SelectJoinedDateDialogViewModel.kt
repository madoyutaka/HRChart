package com.example.hrchart.emp.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hrchart.common.Event

/**
 * 入社日選択カレンダーダイアログ ViewModel
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/25
 */
class SelectJoinedDateDialogViewModel : ViewModel() {

    /** OKボタン押下判定(LiveData) */
    private val onClickPositive = MutableLiveData<Event<String>>()

    /**
     * getOnClickPositive
     * OKボタンが押下された判定を呼び元に渡す
     */
    fun getOnClickPositive(): MutableLiveData<Event<String>> { return onClickPositive }

    /**
     * onClickPositive
     * OKボタンタップ時のイベント
     */
    fun onClickPositive(selectDate: String){
        onClickPositive.postValue(Event(selectDate))
    }
}
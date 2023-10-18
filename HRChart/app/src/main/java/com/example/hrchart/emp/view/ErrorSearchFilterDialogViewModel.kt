package com.example.hrchart.emp.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hrchart.common.Event

/**
 * エラーダイアログ(検索結果0件) ViewModel
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/18
 */
class ErrorSearchFilterDialogViewModel: ViewModel(){

    /** OKボタン押下判定(LiveData) */
    private val onClickOk = MutableLiveData<Event<Boolean>>()

    /**
     * getOnClickOk
     * OKボタンが押下された判定を呼び元に渡す
     */
    fun getOnClickOk(): MutableLiveData<Event<Boolean>> { return onClickOk }

    /**
     * onClickOK
     * OKボタンタップ時のイベント
     */
    fun onClickOK(){
        onClickOk.postValue(Event(true))
    }
}
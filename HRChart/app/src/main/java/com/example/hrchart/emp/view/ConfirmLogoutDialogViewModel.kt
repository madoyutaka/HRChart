package com.example.hrchart.emp.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hrchart.common.Event

/**
 * ログアウト確認ダイアログ ViewModel
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/19
 */
class ConfirmLogoutDialogViewModel: ViewModel(){

    /** "はい"ボタン押下判定(LiveData) */
    private val onClickPositive = MutableLiveData<Event<Boolean>>()

    /**
     * getOnClickPositive
     * "はい"ボタンが押下された判定を呼び元に渡す
     */
    fun getOnClickPositive(): MutableLiveData<Event<Boolean>> { return onClickPositive }

    /**
     * onClickPositive
     * "はい"ボタンタップ時のイベント
     */
    fun onClickPositive(){
        onClickPositive.postValue(Event(true))
    }
}
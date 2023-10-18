package com.example.hrchart.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

/**
 *  OKボタンのみのAlertDialog
 *  画面ID:
 *
 * @param title ダイアログのタイトル
 * @param message ダイアログのメッセージ
 * @param onPressOK OKボタンを押したときの動作
 * @author Y.Sato
 * created on 2023/10/18
 */
class SimpleAlertDialogFragment(title: String, message: String, onPressOK: () -> Unit = {}) :
    DialogFragment() {
    private val _title = title
    private val _message = message
    private val _onPressOK = onPressOK

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setTitle(_title)
            .setMessage(_message)
            .setPositiveButton("OK") { _, _ ->
                _onPressOK()
            }

        return builder.create()
    }

    /**
     * 画面回転時のメモリリーク対策用
     */
    override fun onPause() {
        super.onPause()
        dismiss()
    }
}
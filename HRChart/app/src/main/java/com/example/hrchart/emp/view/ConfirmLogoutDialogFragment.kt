package com.example.hrchart.emp.view

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.hrchart.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * ログアウト確認ダイアログ Fragment
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/19
 */
class ConfirmLogoutDialogFragment : DialogFragment() {

    companion object {
        /** TAG */
        private const val TAG = "ConfirmLogoutDialogFragment"
    }

    /** ViewModel */
    val viewModel: ConfirmLogoutDialogViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(TAG, "onCreateDialog Start")

        val builder = MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.confirm_logout)
            .setPositiveButton(R.string.yes) { dialog, _ ->
                // "はい"ボタンが押下された時の処理
                viewModel.onClickPositive()
                dialog.dismiss()
            }
            .setNegativeButton(R.string.no) { dialog, _ ->
                // "いいえ"ボタンが押下された時の処理
                dialog.dismiss()
            }
            // 戻るボタン無効
            .setCancelable(false)

        // 枠外タップ無効
        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)

        return dialog
    }
}
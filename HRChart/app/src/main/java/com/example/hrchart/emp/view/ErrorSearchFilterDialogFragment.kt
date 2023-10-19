package com.example.hrchart.emp.view

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.hrchart.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * エラーダイアログ(検索結果0件) Fragment
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/18
 */
class ErrorSearchFilterDialogFragment : DialogFragment() {

    companion object {
        /** TAG */
        private const val TAG = "ErrorSearchFilterDialogFragment"
    }

    /** ViewModel */
    val viewModel: ErrorSearchFilterDialogViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(TAG, "onCreateDialog Start")

        val builder = MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.error_search_filter_title)
            .setMessage(R.string.error_search_filter_msg)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                // OKボタンがタップされたときの処理
                viewModel.onClickPositive()
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
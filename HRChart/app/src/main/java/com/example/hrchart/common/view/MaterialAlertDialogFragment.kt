package com.example.hrchart.common.view

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder


/**
 * AlertDialog(ひな形) Fragment
 *
 * @author K.Takahashi
 * created on 2023/10/18
 */
open class MaterialAlertDialogFragment : DialogFragment() {

    companion object {
        /** TAG */
        private const val TAG = "MaterialAlertDialogFragment"
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(TAG, "onCreateDialog Start")

        val builder = MaterialAlertDialogBuilder(requireContext())
            .setTitle("アラートタイトル")
            .setMessage("アラートメッセージ")
            .setPositiveButton("OK") { _, _ ->
                // OKボタン押下時の処理
            }
            .setNegativeButton("キャンセル") { _, _ ->
                // キャンセルボタン押下時の処理
            }
            // 戻るボタン無効 ※有効はtrue
            .setCancelable(false)

        // 枠外タップ無効 ※有効はtrue
        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)

        return dialog
    }

}
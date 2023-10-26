package com.example.hrchart.emp.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.hrchart.R
import java.util.Calendar

/**
 * 入社日選択カレンダーダイアログ Fragment
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/25
 */
class SelectJoinedDateDialogFragment : DialogFragment() {

    companion object {
        /** TAG */
        private const val TAG = "SelectJoinedDateDialogFragment"
    }

    /** ViewModel */
    val viewModel: SelectJoinedDateDialogViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(TAG, "onCreateDialog Start")
        // 現在の日付を取得
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), R.style.CalendarDatePickerStyle,
            { _, selectedYear, selectedMonth, selectedDay ->
            // OKボタン押下時の処理
            // 選択した日付をyyyy/MM/dd形式のStringに変換 ※月は0から始まるので+1が必要
            val selectedDate = String.format("%04d/%02d/%02d", selectedYear, selectedMonth + 1, selectedDay)
            // ViewModelに日付を渡す
            viewModel.onClickPositive(selectedDate)
            }, year, month, day)

        // 必要ならタイトルをセット
//        datePickerDialog.setTitle("入社日を選択してください")

        Log.d(TAG, "onCreateDialog End")
        return datePickerDialog
    }
}
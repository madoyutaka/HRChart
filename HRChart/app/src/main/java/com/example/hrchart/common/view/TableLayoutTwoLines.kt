package com.example.hrchart.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.hrchart.databinding.TableLayoutTwoLinesBinding

/**
 * テーブルレイアウト(2行) カスタムビュー
 *
 * @author K.Takahashi
 * created on 2023/10/11
 */
class TableLayoutTwoLines(context: Context, attrs: AttributeSet? = null)
    : ConstraintLayout(context, attrs)  {

    /** 1行目のTextView */
    private val textFirstRow: TextView
    /** 2行目のTextView */
    private val textSecondRow: TextView
    /** binding */
    private val binding: TableLayoutTwoLinesBinding

    init {
        val inflater = LayoutInflater.from(context)
        binding = TableLayoutTwoLinesBinding.inflate(inflater, this, true)
        textFirstRow = binding.firstRowText
        textSecondRow = binding.secondRowText
    }

    /**
     * setFirstRowText
     * 1行目のテキストをセットする
     * @param firstRowText firstRowText
     */
    fun setFirstRowText(firstRowText: String) {
        textFirstRow.text = firstRowText
    }

    /**
     * setSecondRowText
     * 2行目のテキストをセットする
     * @param secondRowText secondRowText
     */
    fun setSecondRowText(secondRowText: String?) {
        textSecondRow.text = secondRowText
    }

}
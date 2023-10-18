package com.example.hrchart.common

import android.text.InputFilter

/**
 * InputFilters
 * InputFilterのメソッドをまとめたクラス
 *
 * @author K.Takahashi
 * created on 2023/10/18
 */
class InputFilters {
    companion object {
        const val ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]*\$"
        const val EMAIL_PATTERN = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        private const val kanaKanjiAlphabetPattern = "^[ぁ-んァ-ヶ一-龠a-zA-Z]*\$"

        /**
         * getKanaFilter
         * ひらがな、カタカナ、漢字、アルファベットのみのInputFilter
         */
        fun getKanaFilter(): InputFilter {
            val pattern = kanaKanjiAlphabetPattern.toRegex()
            return InputFilter { source, _, _, _, _, _ ->
                if (source.toString().matches(pattern)) {
                    return@InputFilter source
                }
                return@InputFilter ""
            }
        }

    }
}
package com.example.hrchart.emp.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.hrchart.R
import com.example.hrchart.common.EventObserver
import com.example.hrchart.common.InputFilters
import com.example.hrchart.databinding.FragmentEmpSearchBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * 従業員検索画面 Fragment
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/12
 */
class EmpSearchFragment : BottomSheetDialogFragment() {

    companion object {
        /** TAG */
        private const val TAG = "EmpSearchFragment"
    }

    /** viewModel */
    private val viewModel: EmpSearchViewModel by activityViewModels()
    /** binding */
    private lateinit var binding: FragmentEmpSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView Start")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_emp_search, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()

        // クリアボタン
        binding.empSearchClearButton.setOnClickListener {
            binding.empSearchNameEt.text.clear()

            // ドロップダウン初期化
            val statusDropdown : AutoCompleteTextView = binding.empSearchStatusDropdownItem
            val areasDropdown : AutoCompleteTextView = binding.empSearchAreaDropdownItem
            val jobsDropdown : AutoCompleteTextView = binding.empSearchJobDropdownItem
            statusDropdown.setText(R.string.all)
            areasDropdown.setText(R.string.all)
            jobsDropdown.setText(R.string.all)
            initDropdown(statusDropdown, areasDropdown, jobsDropdown)

            // フォーカスクリア
            binding.empSearchNameEt.clearFocus()
            statusDropdown.clearFocus()
            areasDropdown.clearFocus()
            jobsDropdown.clearFocus()
        }

        // キャンセルボタン
        binding.empSearchCloseButton.setOnClickListener {
            dismiss()
        }

        // 検索ボタン
        binding.empSearchButton.setOnClickListener {

            val name = binding.empSearchNameEt.text.toString()
            val status = binding.empSearchStatusDropdownItem.text.toString()
            val area = binding.empSearchAreaDropdownItem.text.toString()
            val job = binding.empSearchJobDropdownItem.text.toString()

            val searchArray = arrayOf(name, status, area, job)
            // 検索結果をViewModelへ配列で渡す
            viewModel.onClickSearch(searchArray)
            dismiss()
        }

        val etName = binding.empSearchNameEt
        // キーボード制御
        keyBoardCtrl(etName)

        Log.d(TAG, "onCreateView End")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated Start")
        this.isCancelable = false
        setDialogMatchParent()
        Log.d(TAG, "onViewCreated End")
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), theme)
        Log.d(TAG, "onCreateDialog Start")
        bottomSheetDialog.setOnShowListener { dialog ->
            val bottomSheet = (dialog as BottomSheetDialog).findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                BottomSheetBehavior.from(bottomSheet).apply {
                    state = BottomSheetBehavior.STATE_EXPANDED
                    skipCollapsed = true
                    isHideable = true
                }
            }
        }
        Log.d(TAG, "onCreateDialog End")
        return bottomSheetDialog
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Start")
        //ドロップダウンアイテムをDBから取得
        viewModel.getDropDownItem()
        // ドロップダウン初期化
        val statusDropdown : AutoCompleteTextView = binding.empSearchStatusDropdownItem
        val areasDropdown : AutoCompleteTextView = binding.empSearchAreaDropdownItem
        val jobsDropdown : AutoCompleteTextView = binding.empSearchJobDropdownItem
        initDropdown(statusDropdown, areasDropdown, jobsDropdown)
        Log.d(TAG, "onResume End")
    }

    /**
     * ダイアログのMatchParentの設定
     */
    private fun setDialogMatchParent() {
        dialog?.window?.let {
            Log.d(TAG, "setDialogMatchParent")
            val params = it.attributes
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            it.attributes = params
            it.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    /**
     * initDropdown
     * ドロップダウン初期化メソッド
     * @param statusDropdown ステータス
     * @param areasDropdown エリア
     * @param jobsDropdown 職種
     */
    private fun initDropdown(statusDropdown: AutoCompleteTextView, areasDropdown: AutoCompleteTextView, jobsDropdown: AutoCompleteTextView) {
        Log.d(TAG, "initDropdown")

        viewModel.getDropDownData().observe(viewLifecycleOwner, EventObserver {
            Log.d(TAG, "getDropDownData")
            // ステータス
            val statusArray = arrayOf(it.statuses.statusAll, it.statuses.enrollment, it.statuses.retirement,
                it.statuses.loa, it.statuses.decline, it.statuses.decision, it.statuses.likelihood)
            // エリア
            val areasArray = arrayOf(it.areas.areasAll, it.areas.tokyo, it.areas.osaka, it.areas.aichi,
                it.areas.miyagi, it.areas.fukuoka)
            // 職種
            val jobsArray = arrayOf(it.jobs.jobsAll, it.jobs.engineer, it.jobs.sales, it.jobs.management, it.jobs.network)

            val statusArrayAdapter = ArrayAdapter(statusDropdown.rootView.context, R.layout.item_drop_down, statusArray)
            statusDropdown.setAdapter(statusArrayAdapter)
            val areasArrayAdapter = ArrayAdapter(areasDropdown.rootView.context, R.layout.item_drop_down, areasArray)
            areasDropdown.setAdapter(areasArrayAdapter)
            val jobsArrayAdapter = ArrayAdapter(jobsDropdown.rootView.context, R.layout.item_drop_down, jobsArray)
            jobsDropdown.setAdapter(jobsArrayAdapter)
        })
    }

    /**
     * hideIME
     * ソフトキーボードを非表示にする
     * @param et EditText
     */
    private fun hideIME(et: EditText) {
        Log.d(TAG, "hideIME")
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(et.windowToken, 0)
    }

    /**
     * keyBoardCtrl
     * ソフトキーボードの処理(EditText1つのみ)
     * @param et EditText
     */
    private fun keyBoardCtrl(et: EditText) {
        Log.d(TAG, "keyBoardCtrl")

        // InputFilterを設定
        val kanaFilter = InputFilters.getKanaFilter()
        et.filters = arrayOf(kanaFilter)

        // ソフトキーボードのDone押下時処理
        et.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // フォーカスクリア
                et.clearFocus()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        // フォーカスアウト処理
        et.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                hideIME(et)
            }
        }
        // 背景をタップしたらソフトキーボードを閉じる
        binding.root.setOnClickListener {
            hideIME(et)
        }
    }

}
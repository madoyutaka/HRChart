package com.example.hrchart.emp.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.hrchart.R
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
    private lateinit var viewModel: EmpSearchViewModel
    /** binding */
    private lateinit var binding: FragmentEmpSearchBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_emp_search, container, false)
        viewModel = ViewModelProvider(this)[EmpSearchViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.empSearchCloseButton.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.isCancelable = true
        setDialogMatchParent()
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), theme)

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
        return bottomSheetDialog
    }

    override fun onResume() {
        super.onResume()
        // ドロップダウン初期化
        val statusDropdown : AutoCompleteTextView = binding.empSearchStatusDropdownItem
        val areasDropdown : AutoCompleteTextView = binding.empSearchAreaDropdownItem
        val jobsDropdown : AutoCompleteTextView = binding.empSearchJobDropdownItem
        initDropdown(statusDropdown, areasDropdown, jobsDropdown)
    }

    /**
     * ダイアログのMatchParentの設定
     */
    private fun setDialogMatchParent() {
        dialog?.window?.let {
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

        // テストデータ
        val statusArray = arrayOf("在職", "退職", "休職", "辞退", "内定", "見込み")
        val areasArray = arrayOf("東京", "大阪", "愛知", "宮城", "福岡")
        val jobsArray = arrayOf("開発エンジニア", "営業", "管理", "ネットワーク")

        val statusArrayAdapter = ArrayAdapter(statusDropdown.rootView.context, R.layout.item_drop_down, statusArray)
        statusDropdown.setAdapter(statusArrayAdapter)
        val areasArrayAdapter = ArrayAdapter(areasDropdown.rootView.context, R.layout.item_drop_down, areasArray)
        areasDropdown.setAdapter(areasArrayAdapter)
        val jobsArrayAdapter = ArrayAdapter(jobsDropdown.rootView.context, R.layout.item_drop_down, jobsArray)
        jobsDropdown.setAdapter(jobsArrayAdapter)
    }

}
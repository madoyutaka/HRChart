package com.example.hrchart.emp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.hrchart.R
import com.example.hrchart.databinding.FragmentEmpInfoBinding

/**
 * 従業員情報画面 Fragment
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/10
 */
class EmpInfoFragment : Fragment() {

    companion object {
        /** TAG */
        private const val TAG = "EmpInfoFragment"
    }

    /** viewModel */
    private lateinit var viewModel: EmpInfoViewModel
    /** binding */
    private lateinit var binding: FragmentEmpInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        Log.d(TAG, "onCreateView Start")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_emp_info, container, false)
        viewModel = ViewModelProvider(this)[EmpInfoViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // SafeArgsの受け取り(id)
        val args: EmpInfoFragmentArgs by navArgs()

        // 受け取った値をViewModelに渡す
        viewModel.showInfo(args.id)

        Log.d(TAG, "onCreateView End")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
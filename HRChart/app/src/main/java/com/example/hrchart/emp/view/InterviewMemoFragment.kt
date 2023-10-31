package com.example.hrchart.emp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hrchart.R
import com.example.hrchart.databinding.FragmentInterviewMemoBinding

/**
 * 面談メモ画面 Fragment
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/31
 */
class InterviewMemoFragment : Fragment() {

    companion object {
        /** TAG */
        private const val TAG = "InterviewMemoFragment"
    }

    /** viewModel */
    private val viewModel: InterviewMemoViewModel by viewModels()
    /** binding */
    private var _binding: FragmentInterviewMemoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView Start")

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_interview_memo, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // ViewModelにnavControllerをセット
        viewModel.navController(findNavController())

        // SafeArgsの受け取り(id)
        val args: InterviewMemoFragmentArgs by navArgs()
        // test
        binding.interviewMemoId.text = resources.getString(R.string.interview_memo_text, args.id.toString())

        Log.d(TAG, "onCreateView End")
        return binding.root
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView Start")
        super.onDestroyView()
        // メモリ解放
        _binding = null
        Log.d(TAG, "onDestroyView End")
    }

}
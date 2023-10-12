package com.example.hrchart.emp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hrchart.R
import com.example.hrchart.common.EventObserver
import com.example.hrchart.databinding.FragmentEmpListBinding
import com.example.hrchart.emp.widget.EmpListAdapter

/**
 * 従業員一覧画面 Fragment
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/06
 */
class EmpListFragment : Fragment() {

    companion object {
        /** TAG */
        private const val TAG = "EmpListFragment"
    }

    /** viewModel */
    private lateinit var viewModel: EmpListViewModel
    /** binding */
    private lateinit var binding: FragmentEmpListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView Start")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_emp_list, container, false)
        viewModel = ViewModelProvider(this)[EmpListViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // ViewModelにnavControllerをセット
        viewModel.navController(findNavController())

        // observe処理
        viewModelObservers()

        Log.d(TAG, "onCreateView End")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * viewModelObservers
     * ViewModelのデータ変更を監視するobserve処理をまとめる
     */
    private fun viewModelObservers() {

        // RecyclerView表示処理(observe)
        viewModel.getEmpList().observe(viewLifecycleOwner, Observer { list ->
            Log.d(TAG, "getEmpList")
            // RecyclerViewのサイズは固定
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.adapter = EmpListAdapter(requireContext(), list, object : EmpListAdapter.OnItemClickListener {
                override fun onItemClick(id: Int) {
                    viewModel.listToInfo(id)
                }
            })
            binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
            // リストの境界線
            val itemDecoration = DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL)
            binding.recyclerView.addItemDecoration(itemDecoration)
        })

        // 検索ボタンのobserve処理
        viewModel.getSearch().observe(viewLifecycleOwner, EventObserver {
            Log.d(TAG, "getSearch")
            if (it) {
                val empSearchFragment = EmpSearchFragment()
                empSearchFragment.show(childFragmentManager, "EmpSearchFragment")
            }
        })
    }

}
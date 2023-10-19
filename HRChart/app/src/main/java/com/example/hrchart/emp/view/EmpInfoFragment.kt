package com.example.hrchart.emp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
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

        // ActionBarのメニュー制御
        setupMenuBar()

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

    /**
     * setupMenuBar
     * ActionBarのメニュー制御
     */
    private fun setupMenuBar() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            }

            override fun onPrepareMenu(menu: Menu) {
                // ログアウトボタン非表示
                menu.findItem(R.id.logout_menu_item).isVisible = false
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // 戻るボタン押下
                if (menuItem.itemId == android.R.id.home) {
                    // 前画面に戻る
                    activity?.onBackPressed()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}
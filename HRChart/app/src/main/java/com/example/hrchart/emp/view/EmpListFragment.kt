package com.example.hrchart.emp.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
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
    private val viewModel: EmpListViewModel by viewModels()
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
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // ViewModelにnavControllerをセット
        viewModel.navController(findNavController())

        // ActionBarのメニュー制御
        setupMenuBar()

        // フッターアイコンの表示処理
        setFooterIcon()

        // observe処理
        viewModelObservers()

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
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // ログアウトボタン押下
                if(menuItem.itemId == R.id.logout_menu_item) {
                    // ログアウト確認ダイアログを表示
                    val confirmLogoutDialogFragment = ConfirmLogoutDialogFragment()
                    confirmLogoutDialogFragment.show(parentFragmentManager, "confirmLogoutDialogFragment")
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Start")
        // 遷移先から戻ってきた時にRecyclerViewをセットする
        viewModel.setEmpList()
        Log.d(TAG, "onResume End")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Start")
        Log.d(TAG, "onPause End")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Start")
        Log.d(TAG, "onStop End")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView Start")
        Log.d(TAG, "onDestroyView End")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Start")
        Log.d(TAG, "onDestroy End")
    }

    /**
     * viewModelObservers
     * ViewModelのデータ変更を監視するobserve処理をまとめる
     */
    private fun viewModelObservers() {

        // RecyclerView表示処理(observe)
        viewModel.getEmpList().observe(viewLifecycleOwner, EventObserver {
            Log.d(TAG, "getEmpList")
            // RecyclerViewのサイズは固定
            binding.empListRecyclerView.setHasFixedSize(true)
            binding.empListRecyclerView.adapter = EmpListAdapter(requireContext(), it, object : EmpListAdapter.OnItemClickListener {
                override fun onItemClick(id: Int) {
                    viewModel.listToInfo(id)
                }
            })
            binding.empListRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
            // リストの境界線
            val itemDecoration = DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL)
            binding.empListRecyclerView.addItemDecoration(itemDecoration)
        })

        // 検索ボタンのobserve
        viewModel.getOnClickSearch().observe(viewLifecycleOwner, EventObserver {
            Log.d(TAG, "getOnSearch")
            if (it) {
                // 検索画面を表示する
                val empSearchFragment = EmpSearchFragment()
                empSearchFragment.show(parentFragmentManager, "EmpSearchFragment")
            }
        })

        // 検索画面のobserve
        val searchViewModel: EmpSearchViewModel by activityViewModels()
        searchViewModel.getOnSearchArray().observe(viewLifecycleOwner, EventObserver {
            Log.d(TAG, "getOnSearchArray")
            // 検索条件をViewModelに渡す
            viewModel.runSearch(it[0], it[1], it[2], it[3], it[4])
        })
        // 検索結果0件ダイアログの表示
        viewModel.getShowErrorSearchFilter().observe(viewLifecycleOwner, EventObserver {
            Log.d(TAG, "getShowErrorSearchFilter")
            if (it) {
                // 検索結果が見つからないダイアログを表示する
                val errorSearchFilterDialogFragment = ErrorSearchFilterDialogFragment()
                errorSearchFilterDialogFragment.show(parentFragmentManager, "ErrorSearchFilterDialogFragment")
            }
        })

        // 検索結果0件ダイアログのobserve
        val errorSearchFilterDialogViewModel: ErrorSearchFilterDialogViewModel by activityViewModels()
        errorSearchFilterDialogViewModel.getOnClickPositive().observe(viewLifecycleOwner, EventObserver {
            Log.d(TAG, "getOnClickPositive")
            if (it) {
                // 検索画面を表示する
                val empSearchFragment = EmpSearchFragment()
                empSearchFragment.show(parentFragmentManager, "EmpSearchFragment")
            }
        })

        // ログアウトダイアログのobserve
        val confirmLogoutDialogViewModel: ConfirmLogoutDialogViewModel by activityViewModels()
        confirmLogoutDialogViewModel.getOnClickPositive().observe(viewLifecycleOwner, EventObserver {
            Log.d(TAG, "getOnClickPositive")
            if (it) {
                // Todo ログアウト処理
                Toast.makeText(activity, "ログアウトしました", Toast.LENGTH_SHORT).show()
            }
        })

    }

    /**
     * setFooterIcon
     * フッターアイコンとテキストとDrawableの設定
     * 該当画面ではない場合はグレーに設定する
     */
    private fun setFooterIcon() {
        Log.d(TAG, "setFooterIcon")
        val recruitDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_calendar_gray)
        binding.empListFooterRecruitButton.text = getString(R.string.recruitment_list)
        binding.empListFooterRecruitButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
        binding.empListFooterRecruitButton.setCompoundDrawablesWithIntrinsicBounds(null, recruitDrawable, null, null)

        val addDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_add_gray)
        binding.empListFooterAddEmpButton.text = getString(R.string.add_emp)
        binding.empListFooterAddEmpButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
        binding.empListFooterAddEmpButton.setCompoundDrawablesWithIntrinsicBounds(null, addDrawable, null, null)
    }

}
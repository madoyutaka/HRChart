package com.example.hrchart.addemp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.hrchart.R
import com.example.hrchart.common.EventObserver
import com.example.hrchart.databinding.FragmentAddEmpBinding
import com.example.hrchart.emp.EmpActivity
import com.example.hrchart.emp.view.ConfirmLogoutDialogFragment
import com.example.hrchart.emp.view.ConfirmLogoutDialogViewModel
import com.example.hrchart.login.LoginActivity
import com.example.hrchart.yearadoption.YearAdoptionActivity

/**
 * 新規登録画面 Fragment
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/27
 */
class AddEmpFragment : Fragment() {

    companion object {
        /** TAG */
        private const val TAG = "AddEmpFragment"
    }

    /** viewModel */
    private val viewModel: AddEmpViewModel by viewModels()

    /** binding */
    private lateinit var binding: FragmentAddEmpBinding

    /** loginType */
    private var loginType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView Start")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_emp, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // ViewModelにnavControllerをセット
        viewModel.navController(findNavController())

        // loginType受け取り
        loginType = requireActivity().intent.getStringExtra("loginType")
        // test(Toast表示) loginTypeによって従業員情報画面の遷移を変える予定(参照のみor編集可能)
        Toast.makeText(activity, loginType, Toast.LENGTH_SHORT).show()

        // ActionBarのメニュー制御
        setupMenuBar()

        // フッターアイコンの表示処理
        setFooterIcon()

        // observe処理
        viewModelObservers()

        // フッターアイコンからの遷移(従業員一覧画面)
        binding.addEmpFooterListButton.setOnClickListener {
            // YearAdoptionActivityへ遷移
            val intent = Intent(context, EmpActivity::class.java)
            // loginTypeを渡す
            intent.putExtra("loginType", loginType)
            startActivity(intent)
        }

        // フッターアイコンからの遷移(年間採用一覧画面)
        binding.addEmpFooterRecruitButton.setOnClickListener {
            // YearAdoptionActivityへ遷移
            val intent = Intent(context, YearAdoptionActivity::class.java)
            // loginTypeを渡す
            intent.putExtra("loginType", loginType)
            startActivity(intent)
        }

        Log.d(TAG, "onCreateView End")
        return binding.root
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

        // ログアウトダイアログのobserve
        val confirmLogoutDialogViewModel: ConfirmLogoutDialogViewModel by activityViewModels()
        confirmLogoutDialogViewModel.getOnClickPositive().observe(viewLifecycleOwner, EventObserver {
            Log.d(TAG, "getOnClickPositive")
            if (it) {
                // ログアウト処理
                loginType = null
                // バックスタックを削除してログイン画面に戻る
                val intent = Intent(context, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
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
        binding.addEmpFooterRecruitButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
        binding.addEmpFooterRecruitButton.setCompoundDrawablesWithIntrinsicBounds(null, recruitDrawable, null, null)

        val listDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_list_gray)
        binding.addEmpFooterListButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
        binding.addEmpFooterListButton.setCompoundDrawablesWithIntrinsicBounds(null, listDrawable, null, null)
    }
}
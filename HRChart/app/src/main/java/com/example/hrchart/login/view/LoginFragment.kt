package com.example.hrchart.login.view

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
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.NavHostFragment
import com.example.hrchart.R
import com.example.hrchart.databinding.FragmentLoginBinding
import com.example.hrchart.dialog.SimpleAlertDialogFragment

class LoginFragment : Fragment() {

    /**
     *  ログイン画面 Fragment
     *  画面ID:
     *
     *  @author Y.Sato
     *  created on 2023/10/06
     */
    companion object {
        /** TAG */
        private const val TAG = "LoginFragment"
    }

    /** binding */
    private lateinit var binding: FragmentLoginBinding

    /** viewModel */
    private lateinit var viewModel: LoginViewModel

    /** 入力password*/
    private var inputPassword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView Start")
        // アクションバー非表示
        setupMenuBar()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // パスワード入力のListener
        binding.editTextPassword.addTextChangedListener { text ->
            viewModel.updateButton(text.isNullOrBlank())
        }

        // ログインボタン押下のListener
        binding.loginButton.setOnClickListener { v ->
            // 入力されたパスワード受け渡し
            inputPassword = binding.editTextPassword.text.toString()
            // パスワード確認
            var loginType = viewModel.onLoginClicked(inputPassword)

            // ログイン画面から従業員一覧画面へ画面遷移(仮)
            when (loginType) {
                //
                "user", "admin" -> {
                    val navHostFragment =
                        requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

                    // NavController取得
                    val navController = navHostFragment.navController
                    val action =
                        LoginFragmentDirections.actionLoginFragmentToNavGraphEmp()
                    navController.navigate(action)
                }
                // それ以外(パスワード不一致など)
                else -> {
                    // パスワード入力欄を空にして、エラーダイアログを表示
                    binding.editTextPassword.text = null
                    // ダイアログ表示処理
                    val dialog =  SimpleAlertDialogFragment("ログイン失敗", "パスワードが異なります。再入力してください。") {
                        // OK時の処理
                    }
                    dialog.show(requireActivity().supportFragmentManager, "dlg_msg")
                }
            }
        }
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
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}
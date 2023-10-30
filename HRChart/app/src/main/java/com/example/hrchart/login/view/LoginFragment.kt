package com.example.hrchart.login.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hrchart.R
import com.example.hrchart.databinding.FragmentLoginBinding
import com.example.hrchart.dialog.SimpleAlertDialogFragment
import com.example.hrchart.emp.EmpActivity


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

        // キーボードの表示管理
        binding.editTextPassword.setOnEditorActionListener { _, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH ||
                i == EditorInfo.IME_ACTION_DONE ||
                keyEvent != null &&
                keyEvent.action == KeyEvent.ACTION_DOWN &&
                keyEvent.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                if (keyEvent == null || !keyEvent.isShiftPressed) {
                    // フォーカスを外す
                    binding.editTextPassword.clearFocus()
                    // キーボード非表示
                    showOffKeyboard()
                    return@setOnEditorActionListener true
                } else {
                    return@setOnEditorActionListener false
                }
            } else {
                return@setOnEditorActionListener false
            }
        }

        // 背景をタップしたらソフトキーボードを閉じてフォーカスアウトする
        binding.root.setOnClickListener {
            showOffKeyboard()
            binding.editTextPassword.clearFocus()
        }

        // ログインボタン押下のListener
        binding.loginButton.setOnClickListener {
            // 入力されたパスワード受け渡し
            inputPassword = binding.editTextPassword.text.toString()

            // ログイン画面から従業員一覧画面へ画面遷移
            when (val loginType = viewModel.onLoginClicked(inputPassword)) {
                "user", "admin" -> {
                    // EmpActivityへ遷移
                    val intent = Intent(context, EmpActivity::class.java)
                    // loginTypeを渡す
                    intent.putExtra("loginType", loginType)
                    startActivity(intent)

                    // 画面遷移前にクリアされないように少しdelayをかける
                    Handler(Looper.getMainLooper()).postDelayed({
                        // パスワード欄とフォーカスはクリアする
                        binding.editTextPassword.text.clear()
                        binding.editTextPassword.clearFocus()
                    }, 3000)
                }
                // それ以外(パスワード不一致など)
                else -> {
                    // パスワード入力欄を空にして、エラーダイアログを表示
                    binding.editTextPassword.text.clear()
                    // ダイアログ表示処理
                    val dialog =
                        SimpleAlertDialogFragment("ログイン失敗", "パスワードが異なります。再入力してください。") {
                            // OK時の処理
                        }
                    dialog.show(requireActivity().supportFragmentManager, "dlg_msg")
                }
            }
        }
    }

    /**
     * showOffKeyboard
     * キーボード非表示
     */
    private fun showOffKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Start")
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

}
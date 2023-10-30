package com.example.hrchart.login

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hrchart.R
import com.example.hrchart.login.view.LoginFragment

/**
 *  ログイン画面 Activity
 *  画面ID:
 *
 *  @author Y.Sato
 *  created on 2023/10/06
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // アクションバー設定
        supportActionBar?.title = getString(R.string.login_title)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.light_gray)))
        
        val loginFragment = LoginFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, loginFragment)
        transaction.commit()

    }
}
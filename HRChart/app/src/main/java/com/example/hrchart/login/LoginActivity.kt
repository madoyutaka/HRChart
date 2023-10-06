package com.example.hrchart.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hrchart.R
import com.example.hrchart.login.view.LoginFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }
    }
}
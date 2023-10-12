package com.example.hrchart.emp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.hrchart.R

/**
 * 従業員画面 Activity
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/06
 */
class EmpActivity : AppCompatActivity() {

    // NavController初期化
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emp)

        navController = findNavController(R.id.nav_host_fragment)

        // ActionBarにNavigation機能を追加する
        setupActionBarWithNavController(navController)
    }

    /**
     * onSupportNavigateUpメソッド(オーバーライド)
     * 戻るボタンが前画面に戻るように機能させる
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
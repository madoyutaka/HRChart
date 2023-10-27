package com.example.hrchart.addemp

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.hrchart.R

/**
 * 新規登録 Activity
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/27
 */
class AddEmpActivity : AppCompatActivity() {

    // NavController初期化
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_emp)

        navController = findNavController(R.id.nav_host_fragment)

        // アクションバーの背景色
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.light_gray)))

        // ActionBarにNavigation機能を追加する
        setupActionBarWithNavController(navController)

        // ActionBarのメニュー制御
        setupMenuBar()
    }

    /**
     * onSupportNavigateUp(オーバーライド)
     * 戻るボタンが前画面に戻るように機能させる
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    /**
     * setupMenuBar
     * ActionBarのメニュー制御
     */
    private fun setupMenuBar() {
        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.custom_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // アイコンタップ時の処理はFragment側で制御するためfalseを返す
                return false
            }
        })
    }
}
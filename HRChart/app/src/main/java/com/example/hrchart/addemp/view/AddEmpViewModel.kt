package com.example.hrchart.addemp.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

/**
 * 新規登録画面 ViewModel
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/27
 */
class AddEmpViewModel: ViewModel() {

    companion object {
        /** TAG */
        private const val TAG = "AddEmpViewModel"
    }

    /** navController */
    private lateinit var navController: NavController

    /**
     * navController
     * FragmentからnavControllerを受け取る
     * @param navController NavController
     */
    fun navController(navController: NavController) {
        Log.d(TAG, "navController")
        this.navController = navController
    }

}
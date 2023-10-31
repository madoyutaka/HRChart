package com.example.hrchart.emp.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

/**
 * 面談メモ画面 ViewModel
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/31
 */
class InterviewMemoViewModel :  ViewModel() {

    companion object {
        /** TAG */
        private const val TAG = "InterviewMemoViewModel"
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
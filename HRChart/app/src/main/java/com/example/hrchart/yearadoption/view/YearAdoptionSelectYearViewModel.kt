package com.example.hrchart.yearadoption.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

/**
 * 年度選択画面 ViewModel
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/27
 */
class YearAdoptionSelectYearViewModel : ViewModel()  {

    companion object {
        /** TAG */
        private const val TAG = "YearAdoptionSelectYearViewModel"
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
package com.example.hrchart.emp.view

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.hrchart.emp.data.EmpData

/**
 * 従業員一覧画面 ViewModel
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/06
 */
class EmpListViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        /** TAG */
        private const val TAG = "EmpListViewModel"
    }

    /** ID */
    private var id: Int = 0
    /**
     * 従業員リスト(LiveData)
     */
    private var empData = MutableLiveData<ArrayList<EmpData>>()
    /**
     *  getEmpList
     *  リストをFragmentへ渡してobserveする
     */
    fun getEmpList(): MutableLiveData<ArrayList<EmpData>> { return empData }

    /** navController */
    private lateinit var navController: NavController
    /**
     * navController
     * FragmentからnavControllerを受け取る
     * @param navController NavController
     */
    fun navController(navController: NavController) {
        this.navController = navController
    }

    /**
     * init
     */
    init {
        Log.d(TAG, "init")
        // テスト用 リストアイテム表示
//        Handler(Looper.getMainLooper()).postDelayed({
        setEmpList()
//        }, 5000)
    }

    /**
     * setEmpList
     * リストアイテムをFragmentにLiveDataで渡す
     */
    private fun setEmpList() {
        Log.d(TAG, "setEmpList")
        // テストデータ
        val list = arrayListOf(
            EmpData(1, "山田 太郎", "22", "開発エンジニア", "東京", "在職"),
            EmpData(2, "鈴木 一郎", "46", "営業", "大阪", "退職"),
            EmpData(3, "佐藤 二郎", "51", "開発エンジニア", "宮城", "休職")
        )
        empData.postValue(list)
    }

    /**
     * listToInfo
     * 従業員情報画面へ遷移
     * @param id 選択した従業員のID
     */
    fun listToInfo(id: Int) {
        Log.d(TAG, "listToInfo")
        this.id = id
        val action = EmpListFragmentDirections.actionEmpListToEmpInfo(id)
        navController.navigate(action)
    }

}
package com.example.hrchart.emp.view


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.hrchart.common.Event
import com.example.hrchart.emp.data.EmpData

/**
 * 従業員一覧画面 ViewModel
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/06
 */
class EmpListViewModel: ViewModel() {

    companion object {
        /** TAG */
        private const val TAG = "EmpListViewModel"

        // テストデータ
        val list = arrayListOf(
            EmpData(1, "山田 太郎", "22", "開発エンジニア", "東京", "在職"),
            EmpData(2, "鈴木 一郎", "46", "営業", "大阪", "退職"),
            EmpData(3, "佐藤 二郎", "51", "開発エンジニア", "宮城", "休職"),
            EmpData(4, "test01", "26", "ネットワーク", "東京", "在職"),
            EmpData(5, "test02", "32", "開発エンジニア", "東京", "在職"),
            EmpData(6, "test03", "38", "開発エンジニア", "福岡", "在職"),
            EmpData(7, "test04", "44", "営業", "東京", "在職"),
            EmpData(8, "test05", "24", "開発エンジニア", "東京", "在職"),
            EmpData(9, "test06", "48", "管理", "東京", "在職"),
            EmpData(10, "test07", "52", "開発エンジニア", "愛知", "在職")
        )
    }

    /** ID */
    private var id: Int = 0
    /** 名前 */
    private var name: String = ""
    /** ステータス */
    private var status: String = ""
    /** エリア */
    private var area: String = ""
    /** 職種 */
    private var job: String = ""

    /**
     * 従業員リスト(LiveData)
     */
    private var empData = MutableLiveData<Event<ArrayList<EmpData>>>()

    /**
     *  getEmpList
     *  リストをFragmentへ渡す
     */
    fun getEmpList(): MutableLiveData<Event<ArrayList<EmpData>>> { return empData }

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

    /**
     * 検索ボタン押下(LiveData)
     */
    private var onClickSearch = MutableLiveData<Event<Boolean>>()

    /**
     * getOnClickSearch
     * 検索ボタンが押下されたかの判定をFragmentに渡す
     */
    fun getOnClickSearch(): MutableLiveData<Event<Boolean>> { return onClickSearch }

    /**
     * 検索結果が見つからないダイアログの表示(LiveData)
     */
    private var showErrorSearchFilter = MutableLiveData<Event<Boolean>>()

    /**
     * getShowErrorSearchFilter
     * 検索ボタンが押下されたかの判定をFragmentに渡す
     */
    fun getShowErrorSearchFilter(): MutableLiveData<Event<Boolean>> { return showErrorSearchFilter }


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
    fun setEmpList() {
        Log.d(TAG, "setEmpList")
        empData.postValue(Event(list))
    }

    fun runSearch(name: String, status: String, area: String, job: String) {
        Log.d(TAG, "runSearch Start")
        this.name = name
        this.status = status
        this.area = area
        this.job = job

        // 各項目が空欄の場合も含めてand条件で検索
        // 名前は部分一致
        val filteredEmp: ArrayList<EmpData> = ArrayList(list.filter {
            (this.name.isEmpty() || it.empName.contains(this.name)) &&
            (this.status.isEmpty() || ((this.status == "すべて")) || it.status == this.status) &&
            (this.area.isEmpty() || ((this.area == "すべて")) || it.area == this.area) &&
            (this.job.isEmpty() || ((this.job == "すべて")) || it.job == this.job)
        })

        // 見つかった場合は検索結果を表示
        if(filteredEmp.isNotEmpty()) {
            empData.postValue(Event(filteredEmp))
        } else {
            // 見つからない場合はエラーダイアログ表示
            showErrorSearchFilter.postValue(Event(true))
        }

        Log.d(TAG, "runSearch End")
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

    /**
     * onClickSearchButton
     * 検索ボタン押下時のイベント
     */
    fun onClickSearchButton() {
        Log.d(TAG, "onClickSearchButton")
        onClickSearch.postValue(Event(true))
    }

}
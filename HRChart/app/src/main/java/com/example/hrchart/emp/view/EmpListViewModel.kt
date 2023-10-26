package com.example.hrchart.emp.view


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.hrchart.common.Event
import com.example.hrchart.emp.data.EmpData
import com.example.hrchart.emp.model.EmpRepository
import kotlinx.coroutines.launch

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
    /** 入社日 */
    private var joinedDate: String = ""
    /** 従業員リスト */
    private var list: List<EmpData> = emptyList()
    /** EmpRepository */
    private val empRepository: EmpRepository = EmpRepository()

    /** 従業員リスト(LiveData) */
    private var empData = MutableLiveData<Event<List<EmpData>>>()

    /**
     *  getEmpList
     *  リストをFragmentへ渡す
     */
    fun getEmpList(): MutableLiveData<Event<List<EmpData>>> { return empData }

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

    /** 検索ボタン押下(LiveData) */
    private var onClickSearch = MutableLiveData<Event<Boolean>>()

    /**
     * getOnClickSearch
     * 検索ボタンが押下されたかの判定をFragmentに渡す
     */
    fun getOnClickSearch(): MutableLiveData<Event<Boolean>> { return onClickSearch }

    /** 検索結果0件ダイアログの表示(LiveData) */
    private var showErrorSearchFilter = MutableLiveData<Event<Boolean>>()

    /**
     * getShowErrorSearchFilter
     * 検索ボタン0件ダイアログの表示判定をFragmentに渡す
     */
    fun getShowErrorSearchFilter(): MutableLiveData<Event<Boolean>> { return showErrorSearchFilter }


    /**
     * init
     */
    init {
        Log.d(TAG, "init")
        // リストアイテム表示
        setEmpList()
    }

    /**
     * runSearch
     * 検索条件に応じたデータを検索して表示する
     * @param name 名前
     * @param status ステータス
     * @param area エリア
     * @param job 職種
     * @param joinedDate 入社日
     */
    fun runSearch(searchArray: Array<String>) {
        Log.d(TAG, "runSearch")
        this.name = searchArray[0]
        this.status = searchArray[1]
        this.area = searchArray[2]
        this.job = searchArray[3]
        this.joinedDate = searchArray[4]

        // 名前(部分一致)、ステータス、エリア、職種、入社日でフィルタリング
        val filteredEmp: List<EmpData> = ArrayList(list.filter {
            (this.name.isEmpty() || it.empName.contains(this.name)) &&
            (this.status.isEmpty() || ((this.status == "すべて")) || it.status == this.status) &&
            (this.area.isEmpty() || ((this.area == "すべて")) || it.area == this.area) &&
            (this.job.isEmpty() || ((this.job == "すべて")) || it.job == this.job) &&
            (this.joinedDate.isEmpty() || it.joinedDate == this.joinedDate)
        })

        // 見つかった場合は検索結果を表示
        if(filteredEmp.isNotEmpty()) {
            empData.postValue(Event(filteredEmp))
        } else {
            // 見つからない場合はエラーダイアログ表示
            showErrorSearchFilter.postValue(Event(true))
        }
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


    /**
     * setEmpList
     * API通信で取得したリストを表示する
     */
    fun setEmpList() {
        Log.d(TAG, "setEmpList")
        // 非同期処理
        viewModelScope.launch {
            try {
                // デシリアライズしたJsonデータを取得
                val data = empRepository.getEmpListData().empData
                // Firebaseから取得したデータはmapのためlistへ変換
                list = data.values.toList() // 検索で使用できるように変数で保持
                empData.postValue(Event(list))

            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
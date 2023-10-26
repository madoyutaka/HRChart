package com.example.hrchart.emp.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hrchart.R
import com.example.hrchart.databinding.ItemEmpListBinding
import com.example.hrchart.emp.data.EmpData

/**
 * 従業員一覧画面 Adapter
 * 画面ID:
 *
 * @author K.Takahashi
 * created on 2023/10/10
 */
class EmpListAdapter(private val context: Context, private val empData: List<EmpData>, private val listener: OnItemClickListener) : RecyclerView.Adapter<EmpListAdapter.EmpListViewHolder>() {

    class EmpListViewHolder(val binding: ItemEmpListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEmpListBinding.inflate(inflater, parent, false)
        return EmpListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmpListViewHolder, position: Int) {
        val item = empData[position]
        // 名前
        holder.binding.empListName.text = item.empName
        // ステータス
        holder.binding.empListStatus.text = item.status
            // ステータスに応じてTextViewに背景色を適用
            val colorResId = when (item.status) {
                // 在職
                context.getString(R.string.enrollment) -> R.color.blue
                // 退職
                context.getString(R.string.retirement) -> R.color.red
                else -> R.color.gray
            }
        holder.binding.empListStatus.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, colorResId))
        // エリア
        holder.binding.empListArea.text = item.area
        // 年齢
            // 年齢をフォーマットして"〇歳"として表示
            val formattedAge = "${item.age}歳"
        holder.binding.empListAge.text = formattedAge
        // 職種
        holder.binding.empListJob.text = item.job
        // 入社日
            val formattedJoined = "入社日：${item.joinedDate}"
        holder.binding.empListJoined.text = formattedJoined

        // クリックイベント処理用リスナー
        holder.itemView.setOnClickListener {
            listener.onItemClick(item.id)
        }
    }

    /**
     * クリックイベント処理用のインターフェース
     */
    interface OnItemClickListener {
        fun onItemClick(id: Int)
    }

    override fun getItemCount(): Int {
        return empData.size
    }
}


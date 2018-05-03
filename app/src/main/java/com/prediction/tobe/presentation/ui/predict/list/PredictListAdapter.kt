package com.prediction.tobe.presentation.ui.predict.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prediction.tobe.R
import com.prediction.tobe.domain.dto.PredictDto
import kotlinx.android.synthetic.main.item_predict.view.*

class PredictListAdapter constructor(private val list: List<PredictDto>) : RecyclerView.Adapter<PredictListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_predict, parent, false))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
        fun bindData(dto: PredictDto) {
            itemView.txtPredictText.text = dto.predictModel.text
        }
    }
}


package com.prediction.tobe.presentation.ui.predict.list

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prediction.tobe.R
import com.prediction.tobe.domain.Answer
import com.prediction.tobe.domain.PredictBean
import kotlinx.android.synthetic.main.f_predict_list.*
import java.util.*

class PredictListFragment : Fragment(), IPredictListView {
    private lateinit var adapter: PredictListAdapter
    private lateinit var ctx: Context

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater!!.inflate(R.layout.f_predict_list, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ctx = activity

        adapter = PredictListAdapter(initTempList())
        rvPredicts.adapter = adapter
        rvPredicts.layoutManager = LinearLayoutManager(ctx)
        rvPredicts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    fabAddPredict.hide()
                } else {
                    fabAddPredict.show()
                }
            }
        })
    }

    private fun initTempList() = mutableListOf(
            PredictBean(1, "Temp text fish here", Date().time, 2, Answer.YES, true),
            PredictBean(2, "Another text Lorem ipsum that's fine", Date().time, 40, Answer.NO, false),
            PredictBean(5, "Thay will be enough let's have this many", Date().time, 1200, Answer.NO, false)
    )

}
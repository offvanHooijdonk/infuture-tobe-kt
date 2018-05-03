package com.prediction.tobe.presentation.ui.predict.list

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prediction.tobe.AppToBe
import com.prediction.tobe.R
import com.prediction.tobe.domain.dto.PredictDto
import com.prediction.tobe.presentation.presenter.PredictListPresenter
import kotlinx.android.synthetic.main.f_predict_list.*
import org.jetbrains.anko.longToast
import javax.inject.Inject

class PredictListFragment : Fragment(), IPredictListView {

    @Inject
    lateinit var presenter: PredictListPresenter

    private lateinit var adapter: PredictListAdapter
    private lateinit var ctx: Context
    private val predictList = mutableListOf<PredictDto>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater!!.inflate(R.layout.f_predict_list, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AppToBe.di.predictComponent().inject(this)
        ctx = activity
        presenter.attachView(this)

        adapter = PredictListAdapter(predictList)
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

    override fun onStart() {
        super.onStart()

        presenter.loadPredicts()
    }

    override fun onPredictsLoaded(predicts: List<PredictDto>) {
        predictList.clear()
        predictList.addAll(predicts)
        longToast("ListLoaded")

        adapter.notifyDataSetChanged()
    }

    override fun onLoadError(th: Throwable) {
        longToast("Error! " + th.toString())
    }
}
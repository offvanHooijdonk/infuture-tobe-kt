package com.prediction.tobe.presentation.ui.predict.edit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import android.widget.DatePicker
import android.widget.TimePicker
import com.prediction.tobe.AppToBe
import com.prediction.tobe.R
import com.prediction.tobe.domain.model.PredictModel
import com.prediction.tobe.presentation.presenter.PredictEditPresenter
import kotlinx.android.synthetic.main.activity_edit_predict.*
import org.jetbrains.anko.longToast
import java.util.*
import java.util.Calendar.HOUR_OF_DAY
import java.util.Calendar.MINUTE
import javax.inject.Inject

class PredictEditActivity : AppCompatActivity(), IPredictEditView, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @Inject
    lateinit var presenter: PredictEditPresenter

    private lateinit var predictModel: PredictModel
    private val predictDate: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_predict)

        AppToBe.di.predictComponent().inject(this)
        presenter.attachView(this)

        fabSave.setOnClickListener({
            if (validateAndPopulatePredictBean()) {
                it.isEnabled = false
                presenter.savePredict(predictModel)
            }
        })

        txtDateTill.setOnClickListener({
            showDateTimePicker()
        })
    }

    private fun validateAndPopulatePredictBean(): Boolean {
        var isValid = true

        if (inputText.text.isBlank()) {
            til.error = getString(R.string.valid_error_text_empty)
            isValid = false
        }

        if (!radioPositive.isChecked && !radioNegative.isChecked) {
            // todo show some error message
            isValid = false
        }

        if (isValid) {
            predictModel = PredictModel(null, inputText.text.toString(), predictDate.timeInMillis, 0,
                    if (radioPositive.isChecked) PredictModel.Answer.YES else PredictModel.Answer.NO, false)
        }

        return isValid
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        predictDate.set(Calendar.YEAR, year)
        predictDate.set(Calendar.MONTH, month)
        predictDate.set(Calendar.DAY_OF_YEAR, dayOfMonth)

        TimePickerDialog(this, this,
                12,
                0, true).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        predictDate.set(HOUR_OF_DAY, hourOfDay)
        predictDate.set(MINUTE, minute)

        txtDateTill.text = getString(R.string.datetime_template,
                DateFormat.getMediumDateFormat(this).format(predictDate.time),
                DateFormat.getTimeFormat(this).format(predictDate.time))
    }

    override fun onPredictSaved() {
        // todo close popup
        longToast("Saved.")
        this.finish()
    }

    override fun onSaveError(throwable: Throwable) {
        longToast("Error Saving! " + throwable.toString())
    }


    private fun showDateTimePicker() {
        val startDate = Calendar.getInstance()
        startDate.add(Calendar.DAY_OF_MONTH, 1)

        val dialog = DatePickerDialog(this, this,
                startDate.get(Calendar.YEAR),
                startDate.get(Calendar.MONTH),
                startDate.get(Calendar.DAY_OF_MONTH))
        dialog.datePicker.minDate = startDate.timeInMillis

        //startDate.add(Calendar.YEAR, )
        //dialog.datePicker.maxDate = startDate.getTimeInMillis()
        dialog.show()
    }
}
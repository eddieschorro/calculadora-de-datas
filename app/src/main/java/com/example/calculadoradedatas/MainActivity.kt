package com.example.calculadoradedatas

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_datePicker.setOnClickListener { view ->
            openCalendar(view)
        }
    }

    private fun openCalendar(view: View) {

        // these are the parameters required for the onDateSetListener method.***
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        var dpd = DatePickerDialog(
            this,
            { view, selectedYear, selectedMonth, selectedDayOfMonth ->

                // this is the code executed when the date is selected in the dialog
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$year"
                txtv_selectedDate.setText(selectedDate)

                // need to format the string to a date format in order to treat it as a date
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

                // and then parse it to the selectedDate variable. So the selectedDate var is now an instance of a date object.
                val theDate = simpleDateFormat.parse(selectedDate)

                // the return is in miliseconds. so it needs a proper calculation in order to transform into a time other than this
                val selectedDateInMinutes = theDate.time / 60000

                // now the current time for the calculation
                val currentTime = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))

                // finnaly the time to display
                val resultInMinutes = (currentTime.time / 60000) - selectedDateInMinutes

                txtv_result.setText(resultInMinutes.toString())

            },
            year,
            month,
            day //these are the ones intantiated before***
        )

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()

    }
}

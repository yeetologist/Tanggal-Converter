package com.dateconverter.tanggalconverter

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
            clickDatePicker(it)
        }
    }
    fun clickDatePicker(view: View) {
        val myCal = Calendar.getInstance()
        val year = myCal.get(Calendar.YEAR)
        val month = myCal.get(Calendar.MONTH)
        val day = myCal.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, {
                view, selectedYear, selectedMonth, selectedDay ->
            run {
                val tvSelectedDate = findViewById<TextView>(R.id.selectedDate)
                val tvConversionResult = findViewById<TextView>(R.id.conversionResult)

                Toast.makeText(this, "Year:$selectedYear Month:$selectedMonth Day: $selectedDay", Toast.LENGTH_LONG).show()
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val date = dateFormat.parse(selectedDate)
                tvSelectedDate.text = selectedDate.toString()
                val selectedDateInMin = (date?.time ?: 0) / 60000
                val currentDate = dateFormat.parse(dateFormat.format(System.currentTimeMillis()))
                val currenDateinMin = (currentDate?.time ?: 0) / 60000
                tvConversionResult.text = (currenDateinMin - selectedDateInMin).toString()

            }
        },year,month,day)
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}


package edu.uw.ischool.harmaz.tipcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var amountEditText: EditText
    private lateinit var calculateTipButton: Button
    private lateinit var tipPercentageSpinner: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        amountEditText = findViewById(R.id.amountEditText)
        calculateTipButton = findViewById(R.id.calculateTipButton)
        tipPercentageSpinner = findViewById(R.id.tipPercentageSpinner)

        amountEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()
                calculateTipButton.isEnabled = input.isNotEmpty()
            }




            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        calculateTipButton.setOnClickListener {
            val amount = amountEditText.text.toString().toDoubleOrNull()

            amount?.let {
                val tipPercentage = tipPercentageSpinner.selectedItem.toString().removeSuffix("%").toDouble() / 100
                val tip = it * tipPercentage


                val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
                Toast.makeText(this, formattedTip, Toast.LENGTH_LONG).show()
            }
        }
    }
}

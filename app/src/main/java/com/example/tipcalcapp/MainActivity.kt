package com.example.tipcalcapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalcapp.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tipButton.setOnClickListener { calculateTip() }
    }


    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.tip_twenty_percent -> 0.20
            R.id.tip_eighteen_percent -> 0.18
            else -> 0.15
        }
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }
        var tip = tipPercentage * cost
        if (binding.roundupSwitch.isChecked) {
            tip = ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}
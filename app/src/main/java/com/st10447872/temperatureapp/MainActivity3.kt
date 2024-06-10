package com.st10447872.temperatureapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity3 : AppCompatActivity() {

    private lateinit var temperatureInputs: DoubleArray
    private lateinit var back_button: Button
    private lateinit var temperature_text_view: TextView
    private lateinit var average_temperature_text_view: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        back_button = findViewById(R.id.back_button)
        temperature_text_view = findViewById(R.id.temperatures_text_view)
        average_temperature_text_view = findViewById(R.id.average_temperature_text_view)

        temperatureInputs = intent.getDoubleArrayExtra("temperatureInputs") ?: DoubleArray(7)

        displayDetails()

        back_button.setOnClickListener {
            finish()
        }

        val intent = intent
        val bundle = intent.extras
        if (bundle != null) {
            val temperatureInputsArray = bundle.getDoubleArray("average_temperature")
            if (temperatureInputsArray != null) {
                temperatureInputs = temperatureInputsArray
            }
        }
    }

    private fun displayDetails() {
        val days =
            arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

        temperature_text_view.text = buildString {
            for (i in temperatureInputs.indices) {
                append("${days[i]}: ${temperatureInputs[i]}°C\n") // Display degrees Celsius
            }
        }

        val averageTemperature = temperatureInputs.average()
        average_temperature_text_view.text =
            "Average temperature: %.2f°C".format(averageTemperature) // Update units
    }
}
package com.st10447872.temperatureapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private lateinit var clear_button: Button
    private lateinit var view_temperatures_button: Button
    private lateinit var monday_input: EditText
    private lateinit var tuesday_input: EditText
    private lateinit var wednesday_input: EditText
    private lateinit var thursday_input: EditText
    private lateinit var friday_input: EditText
    private lateinit var saturday_input: EditText
    private lateinit var sunday_input: EditText

    private lateinit var inputs: Array<EditText>
    private var temperatureInputs: DoubleArray = DoubleArray(7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        clear_button = findViewById(R.id.clear_button)
        view_temperatures_button = findViewById(R.id.view_temperatures_button)
        monday_input = findViewById(R.id.monday_input)
        tuesday_input = findViewById(R.id.tuesday_input)
        wednesday_input = findViewById(R.id.wednesday_input)
        thursday_input = findViewById(R.id.thursday_input)
        friday_input = findViewById(R.id.friday_input)
        saturday_input = findViewById(R.id.saturday_input)
        sunday_input = findViewById(R.id.sunday_input)

        inputs = arrayOf(
            monday_input,
            tuesday_input,
            wednesday_input,
            thursday_input,
            friday_input,
            saturday_input,
            sunday_input
        )

        clear_button.setOnClickListener {
            clearInputs()
        }

        view_temperatures_button.setOnClickListener {
            if (validateInputs()) {
                temperatureInputs = inputs.map { it.text.toString().toDouble() }.toDoubleArray()

                val intent = Intent(this, MainActivity3::class.java)
                intent.putExtra("temperatureInputs", temperatureInputs)
                startActivity(intent)

            } else {

                Toast.makeText(this, "Please enter valid numbers for all days.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun clearInputs() {

        for (input in inputs) {
            input.text.clear()

        }
    }

    private fun validateInputs(): Boolean {

        for (input in inputs) {
            if (input.text.isEmpty() || input.text.toString().toDoubleOrNull() == null) {
                return false
            }
        }
        return true
    }
}
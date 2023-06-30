package ru.kpfu.itis.lobanov.task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import ru.kpfu.itis.lobanov.task1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var clickButton: Button? = null
    private var name: String? = null
    private var height: Int? = null
    private var weight: Double? = null
    private var age: Int? = null
    private lateinit var textField: EditText
    private lateinit var heightField: EditText
    private lateinit var weightField: EditText
    private lateinit var ageField: EditText
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val newBinding = ActivityMainBinding.inflate(layoutInflater)
        binding = newBinding
        setContentView(newBinding.root)

        if (savedInstanceState != null) {
            name = savedInstanceState.getString("NAME_ARG")
            height = savedInstanceState.getInt("HEIGHT_ARG")
            weight = savedInstanceState.getDouble("WEIGHT_ARG")
            age = savedInstanceState.getInt("AGE_ARG")
        }

        textField = binding.etNameInput
        heightField = binding.etHeightInput
        weightField = binding.etWeightInput
        ageField = binding.etAgeInput
        val text = binding.info

        if (!isValidInput()) {
            text.text = "Введите свои значения"
        } else {
            val value = name!!.length + height!! + weight!! + age!!
            text.text = "Ответ: $value"
        }

        textField.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (textField.text.isNullOrEmpty()) {
                    binding.etNameInput.error = "Введите имя"
                } else {
                    if (textField.text.toString().length > 50) {
                        binding.etNameInput.error = "Имя введено некорректно"
                    } else binding.etNameInput.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        heightField.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (heightField.text.isNullOrEmpty()) {
                    binding.etHeightInput.error = "Введите рост"
                } else {
                    if (heightField.text.toString().toInt() !in 0..250) {
                        binding.etHeightInput.error = "Рост введён некорректно"
                    } else binding.etHeightInput.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        weightField.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (weightField.text.isNullOrEmpty()) {
                    binding.etWeightInput.error = "Введите вес"
                } else {
                    if (weightField.text.toString().toDouble() !in 0.0..250.0) {
                        binding.etWeightInput.error = "Вес введён некорректно"
                    } else binding.etWeightInput.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        ageField.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (ageField.text.isNullOrEmpty()) {
                    binding.etAgeInput.error = "Введите возраст"
                } else {
                    if (ageField.text.toString().toInt() !in 0..150) {
                        binding.etAgeInput.error = "Возраст введён некорректно"
                    } else binding.etAgeInput.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        clickButton = findViewById(R.id.button)
        clickButton?.setOnClickListener {
            if (!isValidInput()) {
                text.text = "Данные введены некорректно"
            } else {
                val value = name!!.length + height!! + weight!! + age!!
                text.text = "Ответ: $value"
            }

        }

    }

    private fun isValidInput(): Boolean {
        name = textField.text.toString()
        height = heightField.text.toString().toIntOrNull()
        weight = weightField.text.toString().toDoubleOrNull()
        age = ageField.text.toString().toIntOrNull()
        if (name.isNullOrEmpty() || height == null || weight == null || age == null) {
            return false
        }
        if (name!!.length > 50 || height!! <= 0 || height!! >= 250 || weight!! <= 0 ||
            weight!! >= 250 || age!! <= 0 || age!! >= 150
        ) {
            return false
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("NAME_ARG", name)
        height?.let { outState.putInt("HEIGHT_ARG", it) }
        weight?.let { outState.putDouble("WEIGHT_ARG", it) }
        age?.let { outState.putInt("AGE_ARG", it) }
    }
}

package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private val error = "Ошибка"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //обработчик событий, который сработывает когда мы нажимаем на кнопку с id btn_0
        btn_0.setOnClickListener { setTextFields("0") }
        btn_1.setOnClickListener { setTextFields("1") }
        btn_2.setOnClickListener { setTextFields("2") }
        btn_3.setOnClickListener { setTextFields("3") }
        btn_4.setOnClickListener { setTextFields("4") }
        btn_5.setOnClickListener { setTextFields("5") }
        btn_6.setOnClickListener { setTextFields("6") }
        btn_7.setOnClickListener { setTextFields("7") }
        btn_8.setOnClickListener { setTextFields("8") }
        btn_9.setOnClickListener { setTextFields("9") }

        btn_min.setOnClickListener { setTextFields("-") }
        btn_sum.setOnClickListener { setTextFields("+") }
        btn_multiplication.setOnClickListener { setTextFields("*") }
        btn_division.setOnClickListener { setTextFields("/") }
        btn_float.setOnClickListener { setTextFields(".") }

        btn_clean.setOnClickListener {
            math_operation.text=""
            result_text.text = ""
        }

        btn_equals.setOnClickListener {
            try {
                val ex = ExpressionBuilder(math_operation.text.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if(result == longRes.toDouble()) {
                    result_text.text = longRes.toString()
                }
                else
                    result_text.text = result.toString()

                result_text.setTextIsSelectable(true)

            } catch (e:Exception) {
                result_text.text = error
            }
        }

    }

    private fun setTextFields(str: String) {
        if(result_text.text == error){
            math_operation.text = ""
            result_text.text = ""
        }

        if(result_text.text != "") {
            result_text.setTextIsSelectable(false)
            math_operation.text = result_text.text.toString()
            result_text.text = ""
        }



        return math_operation.append(str)
    }
}
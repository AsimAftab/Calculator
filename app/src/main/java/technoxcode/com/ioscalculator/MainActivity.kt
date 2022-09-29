package technoxcode.com.ioscalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var tvscreen: TextView? = null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
//Assigning with find view by id method
        tvscreen = findViewById(R.id.tvscreen)

    }

    fun onDigit(view: View) {
       // Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
        tvscreen?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View) {
        tvscreen?.text = ""
    }
    // fun backspace(view: View){
    // var s: String = tvscreen?.text.toString()
    // s = s.substring(0, s.length - 1)
    //  tvscreen?.text = s //It crashes the program so I did it other way

    //  }
    fun backspacev2(view: View) {
        var sd: String = tvscreen?.text.toString()
        if (sd.isNotEmpty()) {
            sd = sd.substring(0, (sd.length - 1))
            tvscreen?.text = sd
        }
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            tvscreen?.append(".")
            lastNumeric = false
            lastDot = true

        }
    }

    fun OnOperator(view: View) {
        tvscreen?.text.let {

            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvscreen?.append((view as Button).text)
                lastNumeric= false
                lastDot = false
            }
        }

    }
    fun onEqual(view: View){
        if(lastNumeric){
            var tvvalue = tvscreen?.text.toString()
            var prefix = ""
            try {
                if (tvvalue.startsWith("-")){
                    prefix = "-"
                    tvvalue = tvvalue.substring(1)
                }
                if (tvvalue.contains("-")){
                val splitvalue = tvvalue.split("-") //Splitting the string
                var one = splitvalue[0]
                var two = splitvalue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                tvscreen?.text = removeZeroafterDot((one.toDouble() - two.toDouble()).toString())
                }else if(tvvalue.contains("+")){
                    val splitvalue = tvvalue.split("+") //Splitting the string
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvscreen?.text = removeZeroafterDot((one.toDouble() + two.toDouble()).toString())
                }else if(tvvalue.contains("X")){
                    val splitvalue = tvvalue.split("X") //Splitting the string
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvscreen?.text = removeZeroafterDot((one.toDouble() * two.toDouble()).toString())
                }else if(tvvalue.contains("÷")){
                    val splitvalue = tvvalue.split("÷") //Splitting the string
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvscreen?.text = removeZeroafterDot((one.toDouble() / two.toDouble()).toString())
                }

            }catch (e:java.lang.ArithmeticException){
                e.printStackTrace()
            }

        }
    }
    private fun removeZeroafterDot(result: String): String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0,result.length -2)
        return value

    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("X") || value.contains("÷") || value.contains("+") || value.contains("-")
        }
    }


}
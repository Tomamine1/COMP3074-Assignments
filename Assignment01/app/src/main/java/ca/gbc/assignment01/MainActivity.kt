package ca.gbc.assignment01


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hoursInput = findViewById<EditText>(R.id.hoursInput)
        val rateInput = findViewById<EditText>(R.id.rateInput)
        val taxInput = findViewById<EditText>(R.id.taxInput)
        val resultText = findViewById<TextView>(R.id.resultText)

        findViewById<Button>(R.id.calculateButton).setOnClickListener {
            val hours = hoursInput.text.toString().toDoubleOrNull()
            val rate = rateInput.text.toString().toDoubleOrNull()
            val taxRate = taxInput.text.toString().toDoubleOrNull()

            if (hours == null || rate == null || taxRate == null ||
                hours < 0 || rate < 0 || taxRate < 0) {
                Toast.makeText(this, "Enter valid positive numbers", Toast.LENGTH_SHORT).show()
            } else {
                val pay: Double
                val overtimePay: Double

                if (hours <= 40) {
                    pay = hours * rate
                    overtimePay = 0.0
                } else {
                    pay = 40 * rate
                    overtimePay = (hours - 40) * rate * 1.5
                }

                val totalPay = pay + overtimePay
                val tax = pay * (taxRate / 100)

                resultText.text = """
                    Regular Pay: $${"%.2f".format(pay)}
                    Overtime Pay: $${"%.2f".format(overtimePay)}
                    Total Pay: $${"%.2f".format(totalPay)}
                    Tax: $${"%.2f".format(tax)}
                """.trimIndent()
            }
        }

        findViewById<Button>(R.id.aboutButton).setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
    }
}

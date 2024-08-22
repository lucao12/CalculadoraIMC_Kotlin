package br.unisanta.View

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.unisanta.Dao.IMCDaoImplement
import br.unisanta.Models.IMC
import br.unisanta.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    val dao = IMCDaoImplement()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val btn = findViewById<Button>(R.id.btnCalcular)
        val btnLimpar = findViewById<Button>(R.id.btnLimpar)
        val edtAltura = findViewById<EditText>(R.id.txtAltura)
        val edtPeso = findViewById<EditText>(R.id.txtPeso)

        btnLimpar.setOnClickListener{
            edtPeso.setText("")
            edtAltura.setText("")
        }

        edtAltura.addTextChangedListener(object : TextWatcher {
            private var isUpdating: Boolean = false
            private val pointChar = '.'

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) return

                isUpdating = true
                var text = s.toString()

                text = text.replace(pointChar.toString(), "")

                if (text.length > 3) {
                    text = text.substring(0, 3)
                }

                if (text.length >= 2) {
                    text = text.substring(0, 1) + pointChar + text.substring(1)
                }

                edtAltura.setText(text)
                edtAltura.setSelection(text.length)

                isUpdating = false
            }
        })

        btn.setOnClickListener {
            val altura = edtAltura.text.toString().toDoubleOrNull()
            val peso = edtPeso.text.toString().toDoubleOrNull()

            if (altura != null && peso != null) {
                val imc = IMC(altura, peso)
                dao.salvarImc(imc)

                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, insira valores válidos para altura e peso.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package br.unisanta.View

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.unisanta.Dao.IMCDaoImplement
import br.unisanta.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity2 : AppCompatActivity(R.layout.activity_main2) {
    val dao = IMCDaoImplement()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val infoNome = findViewById<TextView>(R.id.txtResult)
        infoNome.text = dao.obterCalculo().toString()
        val fab = findViewById<Button>(R.id.btnVoltar)
        fab.setOnClickListener{
            finish()
        }
    }
}
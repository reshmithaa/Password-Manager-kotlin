package com.example.pass

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlite.DBHelper
import com.google.android.material.textfield.TextInputEditText

class StartApp : AppCompatActivity() {
    lateinit var db: DBHelper
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_app)

        db = DBHelper(this)
        val etPass = findViewById<TextInputEditText>(R.id.etStartAppPass)
        val btnEnter = findViewById<Button>(R.id.buttonEnter)

        btnEnter.setOnClickListener {
            if (db.isUserCorrect(etPass.text.toString())){
                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
                val newPage = Intent(this, Page1::class.java)
                startActivity(newPage)
                finish()
            }
        }
    }
}
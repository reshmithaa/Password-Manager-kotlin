package com.example.pass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.sqlite.DBHelper
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var db: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate()")
        db = DBHelper(this)
        var newPage: Intent
        if (db.isFreshUser()){
            setContentView(R.layout.activity_main)
            val edPass1 = findViewById<TextInputEditText>(R.id.etPass1)
            val edPass2 = findViewById<TextInputEditText>(R.id.etPass2)
            val btnContinue = findViewById<Button>(R.id.buttonContinue)

            btnContinue.setOnClickListener {
                Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show()
                if (edPass1.text.toString() == edPass2.text.toString()){
                    db.insert(edPass2.text.toString())
                    Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
                    newPage = Intent(this, Page1::class.java)
                    startActivity(newPage)
                    finish()
                }
            }
        }
        else{
            newPage = Intent(this, StartApp::class.java)
            startActivity(newPage)
        }
    }

    override fun onPause() {
        super.onPause()
        println("onPause()")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart()")
    }

    override fun onStart() {
        super.onStart()
        println("onStart()")
    }

    override fun onStop() {
        super.onStop()
        println("onStop()")
    }
    override fun onResume() {
        super.onResume()
        println("onResume()")
    }
}
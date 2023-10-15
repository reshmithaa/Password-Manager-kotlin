package com.example.pass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlite.DBHelper

class Page1 : AppCompatActivity() {
    lateinit var db: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page1)


    }
}
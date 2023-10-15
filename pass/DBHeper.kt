package com.example.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        const val DATABASE_NAME = "PasswordManager"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "my_data"
        const val NAME = "name"
        const val PASSWORD = "password"
        const val USER_TABLE = "user"
        const val USER_PASSWORD = "u_password"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        println("onCreate()")
        db?.execSQL("create table if not exists $USER_TABLE ($USER_PASSWORD TEXT)")
        db?.execSQL("create table if not exists $TABLE_NAME ($NAME TEXT, $PASSWORD TEXT)")
    }

    fun insert(name: String, password: String): Long {
        val db = writableDatabase
        val value = ContentValues()
        value.put(NAME, name)
        value.put(PASSWORD, password)
        val s = db.insert(TABLE_NAME, null, value)
        db.close()
        return s
    }
    @SuppressLint("Range")
    fun display(): ArrayList<Data> {
        val contactList = ArrayList<Data>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                if (cursor.getColumnIndex(NAME) >=0 && cursor.getColumnIndex(PASSWORD)>=0) {
                    val name = cursor.getString(cursor.getColumnIndex(NAME))
                    val password = cursor.getString(cursor.getColumnIndex(PASSWORD))
                    val contact = Data(name, password)
                    contactList.add(contact)
                    cursor.moveToNext()
                }
            }
        }

        cursor?.close()
        db.close()
        return contactList
    }
    fun isUserCorrect(password: String): Boolean {
        val db = readableDatabase
        val s = db.rawQuery("select count(*) from $USER_TABLE where $USER_PASSWORD='$password'", null)
        s.moveToFirst()
        val count = s.getInt(0)
        return count == 1
    }
    fun insert(password: String): Long {
        val db = writableDatabase
        val value = ContentValues()
        value.put(USER_PASSWORD, password)
        val s = db.insert(USER_TABLE, null, value)
        db.close()
        return s
    }
    @SuppressLint("Recycle")
    fun isFreshUser(): Boolean {
        val db = this.readableDatabase
        val s = db.rawQuery("select count(*) from $USER_TABLE", null)
        s.moveToFirst()
        val count = s.getInt(0)
        return count == 0
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        println("onUpgrade()")
    }
}
package com.example.databasetest

import android.app.NotificationManager
import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val dbHelper = MyDatabaseHelper(this, "BookStore.db", 3)

        /*createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }
        contentValuesOf("" to "")
        addData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                // 開始組裝第一條數據
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert("Book", null, values1) // 插入第一條數據
            val values2 = ContentValues().apply {
                // 開始組裝第二條數據
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2) // 插入第二條數據
        }
        updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            val rows = db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
            Toast.makeText(this, "rows is $rows", Toast.LENGTH_SHORT).show()
        }
        deleteData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("500"))
        }
        queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            // 查詢Book表中所有的數據
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    // 遍歷Cursor對象，取出數據並打印
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.d("MainActivity", "book name is $name")
                    Log.d("MainActivity", "book author is $author")
                    Log.d("MainActivity", "book pages is $pages")
                    Log.d("MainActivity", "book price is $price")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        replaceData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction() // 開啟事務
            try {
                db.delete("Book", null, null)
//                if (true) {
//                    // 在這里手動拋出一個異常，讓事務失敗
//                    throw NullPointerException()
//                }
                val values = cvOf("name" to "Game of Thrones", "author" to "George Martin", "pages" to 720, "price" to 20.85)
                db.insert("Book", null, values)
                db.setTransactionSuccessful() // 事務已經執行成功
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction() // 結束事務
            }
        }*/
    }

}

package com.example.sqlite

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var engEt: EditText
    lateinit var korEt: EditText
    lateinit var insertBt: Button
    lateinit var deleteBt: Button
    lateinit var selectBt: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)
        engEt = findViewById(R.id.engEt)
        korEt = findViewById(R.id.korEt)
        insertBt = findViewById(R.id.insertBt)
        deleteBt = findViewById(R.id.deleteBt)
        selectBt = findViewById(R.id.selectBt)

        init()

        selectBt.setOnClickListener {
            var db: SQLiteDatabase = openOrCreateDatabase("sql_test.db", Context.MODE_PRIVATE, null)
            val c: Cursor = db.rawQuery("SELECT * FROM voca", null)
            Log.d("aabb", "count: " + c.getCount()) // 전체 줄수를 가져온다

            c.moveToFirst()
            while (c.isAfterLast() === false) {
                Log.d(
                    "aabb",
                    "idx: " + c.getInt(0).toString() + ", " +
                            "eng: " + c.getString(1).toString() + ", "
                            + "kor: " + c.getString(2).toString()
                )
                c.moveToNext()
            }
            c.close()
            db.close()
        }

        insertBt.setOnClickListener {
            var db: SQLiteDatabase = openOrCreateDatabase("sql_test.db", Context.MODE_PRIVATE, null)
            var s1 = "a"
            var s2 = "b"
            var str = "INSERT INTO voca (eng,kor) VALUES ('" + s1+ "','"+s2 +"')"
            Log.d("aabb","str: "+str)
            db.execSQL(str)
            db.close()
        }

        deleteBt.setOnClickListener {
            var db: SQLiteDatabase = openOrCreateDatabase("sql_test.db", Context.MODE_PRIVATE, null)
//            db.execSQL("DELETE FROM voca WHERE idx=3") //삭제
            db.execSQL("UPDATE voca SET kor = '바보' WHERE idx = 7") ;
            db.close()
        }
    }

    fun init() {
        var db: SQLiteDatabase = openOrCreateDatabase("sql_test.db", Context.MODE_PRIVATE, null)
        var sql =
            "CREATE TABLE IF NOT EXISTS voca(" +
                    "idx INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "eng TEXT, kor TEXT)"

        db.execSQL(sql)
        db.close()
    }

//        val c: Cursor = db.rawQuery("SELECT * FROM voca", null)
//        Log.d("aabb", "count: " + c.getCount()) // 전체 줄수를 가져온다
//
//        c.moveToFirst()
//        while (c.isAfterLast() === false) {
//            Log.d(
//                "aabb",
//                "eng: " + c.getString(1).toString() + ", "
//                        + "kor: " + c.getString(2).toString()
//            )
//            c.moveToNext()
//        }
//        c.close()

}
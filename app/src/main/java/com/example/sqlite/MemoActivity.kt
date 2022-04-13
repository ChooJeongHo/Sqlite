package com.example.sqlite

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MemoActivity : AppCompatActivity() {
    lateinit var ctEt: EditText
    lateinit var conEt: EditText
    lateinit var ctBtn: Button
    lateinit var conBtn: Button
    lateinit var searchBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.memo)
        ctEt = findViewById(R.id.ctEt)
        conEt = findViewById(R.id.conEt)
        ctBtn = findViewById(R.id.ctBtn)
        conBtn = findViewById(R.id.conBtn)
        searchBtn = findViewById(R.id.searchBtn)

        init()

        searchBtn.setOnClickListener {
            var db: SQLiteDatabase = openOrCreateDatabase("sql_test.db", Context.MODE_PRIVATE, null)
            val c: Cursor = db.rawQuery("SELECT * FROM list", null)
            //Log.d("aabb", "count: " + c.getCount()) // 전체 줄수를 가져온다

            c.moveToFirst()
            while (c.isAfterLast() === false) {
//                Log.d(
//                    "aabb",
//                    "idx: " + c.getInt(0).toString() + ", " +
//                            "eng: " + c.getString(1).toString() + ", "
//                            + "kor: " + c.getString(2).toString()
//                )
                c.moveToNext()
            }
            c.close()
            db.close()
        }

        ctBtn.setOnClickListener {
            var db: SQLiteDatabase = openOrCreateDatabase("sql_test.db", Context.MODE_PRIVATE, null)
//            db.execSQL("INSERT INTO voca (eng,kor) VALUES ('cat','고양이')") // 데이터 입력
            db.close()
        }

        conBtn.setOnClickListener {
            var db: SQLiteDatabase = openOrCreateDatabase("sql_test.db", Context.MODE_PRIVATE, null)
//            db.execSQL("DELETE FROM voca WHERE idx=3") //삭제
//            db.execSQL("UPDATE list SET kor = '바보' WHERE idx = 7") ;
            db.close()
        }
    }

    fun init() {
        var db: SQLiteDatabase = openOrCreateDatabase("sql_test.db", Context.MODE_PRIVATE, null)
        var sql =
            "CREATE TABLE IF NOT EXISTS list(" +
                    "idx INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "category TEXT, content TEXT)"
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
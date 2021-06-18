package com.cykei.sqlitetest

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelper(context: Context, name: String, version: Int):SQLiteOpenHelper(context, name, null,version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val create = "CREATE TABLE memo ( " +
                "no INTEGER PRIMARY KEY, " +
                "content TEXT, " +
                "datetime INTEGER " +
                ")"
        db?.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // SqliteHelper에 전달되는 버전 정보가 변경되었을 때 현재 생성되어 있는 데이터베이스의 버전과 비교해서 더 높으면 호출됩니다.
        // 버전 변경 사항이 없으면 호출되지 않습니다.
        // 책에서 다루지 않습니다.
    }

    fun insertMemo(memo: Memo) {
        val values = ContentValues() //Map 클래스처럼 사용된다.
        values.put("content", memo.content)
        values.put("datetime", memo.datetime)

        val wd = writableDatabase
        wd.insert("memo", null, values)
        wd.close()
    }

    fun selectMemo(): MutableList<Memo> {
        val list = mutableListOf<Memo>()
        val select = "select * from memo"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select, null) // select 쿼리를를 실행해서 결과를 Cursor타입으로 가져온다.

        while(cursor.moveToNext()){
            val no = cursor.getLong(cursor.getColumnIndex("no"))
            val content = cursor.getString(cursor.getColumnIndex("content"))
            val datetime = cursor.getLong(cursor.getColumnIndex("datetime"))

            list.add(Memo(no, content, datetime))
        }
        cursor.close()
        rd.close()
        return list
    }

    fun updateMemo(memo: Memo) {
        val values = ContentValues()
        values.put("content", memo.content)
        values.put("datetime", memo.datetime)

        val wd = writableDatabase
        wd.update("memo", values, "no = ${memo.no}", null)
        //wd.update("memo", values, "no = ?", arrayOf("${memo.no}")) //이런방식도 있다.
        wd.close()
    }

    fun deleteMemo(memo: Memo) {
        val delete = "delete from memo where no = ${memo.no}"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }

}

data class Memo(var no:Long?, var content: String, var datetime: Long)
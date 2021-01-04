package cn.edu.sicnu.fundiary.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

const val DB_NAME = "myappdb.db"
const val TABLE_Diary = "mydiary"

class MyDatabaseHelper(val context: Context, name: String, version:Int) : SQLiteOpenHelper(context, name, null, version){

    private val createDiary="create table mydiary(" +
            "id integer primary key autoincrement," +
            "title text," +
            "type integer," +
            "content text," +
            "time text," +
            "info text,"+
            "isOnLock integer)"
    //建表 :连接表
//
//    val title:String,
//    val type:Int,//0 1 2 3 4
//    val content:String,
//    val time :String
//    val isOnLock:Boolean//是否上锁


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createDiary)
        Toast.makeText(context,"success", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}
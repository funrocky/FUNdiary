package cn.edu.sicnu.fundiary

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import cn.edu.sicnu.fundiary.db.DB_NAME
import cn.edu.sicnu.fundiary.db.MyDatabaseHelper
import cn.edu.sicnu.fundiary.db.TABLE_Diary
import java.time.LocalDateTime

class Activity_diary_add : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_add)

        val image=findViewById<ImageView>(R.id.imageView3)
        val time=findViewById<TextView>(R.id.diarytime)

        val name=findViewById<EditText>(R.id.diaryname)

        val study=findViewById<CheckBox>(R.id.checkBox)
        val life=findViewById<CheckBox>(R.id.checkBox2)
        val work=findViewById<CheckBox>(R.id.checkBox3)
        val other=findViewById<CheckBox>(R.id.checkBox4)
        var type=0//0代表学习
        var typeNum=0

        val info=findViewById<MultiAutoCompleteTextView>(R.id.diaryinfo)

        val switch=findViewById<Switch>(R.id.diaryislock)

        val isOnLock=0
        val content=findViewById<MultiAutoCompleteTextView>(R.id.diarycontent)

        val button=findViewById<Button>(R.id.diarycommit)

        time.text= LocalDateTime.now().toString().substring(0,10)+" "+LocalDateTime.now().toString().substring(11,19)
        //只能选择一项类型
        life.setOnClickListener{
            val action=life.isChecked
            if(action){
                if(typeNum==0){
                    life.isChecked=true
                    type=1
                    typeNum++
                    image.setImageResource(R.drawable.imagelife)
                }else{
                    life.isChecked=false
                    Toast.makeText(this,"只能选择一类",Toast.LENGTH_SHORT).show()
                }
            }else{
                typeNum--
            }
        }

        work.setOnClickListener{
            val action=work.isChecked
            if(action){
                if(typeNum==0){
                    work.isChecked=true
                    type=2
                    typeNum++
                    image.setImageResource(R.drawable.imagework)
                }else{
                    work.isChecked=false
                    Toast.makeText(this,"只能选择一类",Toast.LENGTH_SHORT).show()
                }
            }else{
                typeNum--
            }
        }

        other.setOnClickListener{
            val action=other.isChecked
            if(action){
                if(typeNum==0){
                    other.isChecked=true
                    type=3
                    typeNum++
                    image.setImageResource(R.drawable.imagestudy)
                }else{
                    other.isChecked=false
                    Toast.makeText(this,"只能选择一类",Toast.LENGTH_SHORT).show()
                }
            }else{
                typeNum--
            }
        }

        study.setOnClickListener{
            val action=study.isChecked
            if (action){
                if(typeNum==0){
                    study.isChecked=true
                    type=0
                    typeNum++
                    image.setImageResource(R.drawable.imagestudy)
                }else{
                    study.isChecked=false
                    Toast.makeText(this,"只能选择一类",Toast.LENGTH_SHORT).show()
                }
            }else{
                typeNum--
            }
        }



        button.setOnClickListener{

//            AlertDialog.Builder(this)
//                .setMessage("这是一个上锁的日记\n")
//                .setTitle("输入密码:")
//                .setPositiveButton("确定", DialogInterface.OnClickListener { dialogInterface, i -> Toast.makeText(this,"您已阅读游戏说明",Toast.LENGTH_LONG).show() })
//                .setNeutralButton("取消", null)
//                .create()
//                .show()

            val dbHelper= MyDatabaseHelper(this, DB_NAME,1)

            val db=dbHelper.writableDatabase

            val timenow=LocalDateTime.now().toString().substring(0,10)+" "+LocalDateTime.now().toString().substring(11,18)

            if(name.text.toString()!="" && content.text.toString()!="" && time.text.toString()!="" ){
                Log.d("action","11111111111111")
                val values= ContentValues().apply {
                    put("title",name.text.toString())
                    put("type",type)
                    put("content",content.text.toString())
                    put("time", timenow)
                    put("info",info.text.toString())
                    put("isOnLock",isOnLock)
//                            "id integer primary key autoincrement," +
//                            "title text," +
//                            "type integer," +
//                            "content text," +
//                            "time text," +
//                            "info text"+
//                            "isOnLock int)"
                }
                Log.d("action",isOnLock.toString())
                db.insert(TABLE_Diary,null,values)//插入新连接

                Log.d("name",name.text.toString())
                Toast.makeText(this,"insert OK",Toast.LENGTH_SHORT).show()

                var intent= Intent()
                intent.putExtra("msg","yes")
                //添加到connlist 引发变化
                DiaryList.add(Diary(name.text.toString(),type,content.text.toString(),time.text.toString(),isOnLock,info.text.toString()))
                setResult(Activity.RESULT_OK,intent)

                Log.d("action", "添加时间"+LocalDateTime.now().toString())



                finish()
            }else{
                Toast.makeText(this,"标题，正文不能为空",Toast.LENGTH_SHORT).show()
            }

    }
}
}
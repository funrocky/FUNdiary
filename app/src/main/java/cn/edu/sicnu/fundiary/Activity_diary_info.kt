package cn.edu.sicnu.fundiary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import cn.edu.sicnu.fundiary.db.DB_NAME
import cn.edu.sicnu.fundiary.db.MyDatabaseHelper
import cn.edu.sicnu.fundiary.db.TABLE_Diary
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Activity_diary_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_info)

        val name=findViewById<TextView>(R.id.textView_info_name)
        val time=findViewById<TextView>(R.id.textview_info_time)
        val info=findViewById<TextView>(R.id.textview_info_info)
        val content=findViewById<TextView>(R.id.textview_info_content)
        val image=findViewById<ImageView>(R.id.imageView_info)
        var type:Int=0//类型
        val button=findViewById<FloatingActionButton>(R.id.floatingActionButton_delete)
        var isDelete=0
        //接收intent信息：
        if(intent==null){
            Log.d("有无收到intent","无")
        }else{
            name.text=intent.getStringExtra("name")
            time.text=intent.getStringExtra("time")
            info.text="——"+intent.getStringExtra("info")
            content.text=intent.getStringExtra("content")
            type=intent.getIntExtra("type",0)
        }

        if(type==0){
            image.setImageResource(R.drawable.imagestudy)
        }
        if(type==1){
            image.setImageResource(R.drawable.imagelife)
        }
        if(type==2){
            image.setImageResource(R.drawable.imagework)
        }
        if(type==3){
            image.setImageResource(R.drawable.imagestudy)

        }


        button.setOnClickListener{
            isDelete=1
            val intent= Intent()
            intent.putExtra("isDelete",isDelete)//表示要删除此连接

            val dbHelper= MyDatabaseHelper(this, DB_NAME,1)
            val db=dbHelper.writableDatabase
            db.delete(TABLE_Diary,"content = ?", arrayOf(content.text.toString()))

            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }
}
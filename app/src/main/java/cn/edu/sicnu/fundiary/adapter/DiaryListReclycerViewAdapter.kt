package cn.edu.sicnu.fundiary.adapter

import android.content.Intent
import android.database.Cursor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import cn.edu.sicnu.fundiary.Activity_diary_info
import cn.edu.sicnu.fundiary.Diary
import cn.edu.sicnu.fundiary.R

class DiaryListReclycerViewAdapter(var cursor: Cursor): RecyclerView.Adapter<DiaryListReclycerViewAdapter.ViewHolder>()  {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nametext: TextView;
        var wordtext: TextView;
        var image:ImageView

        init {
            nametext = itemView.findViewById(R.id.textView_dname)
            wordtext = itemView.findViewById(R.id.textView_dword)
            image=itemView.findViewById<ImageView>(R.id.imageView_dimage)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiaryListReclycerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.diaryitem,parent,false)
        //获取view
        val viewHolder=ViewHolder(view)
        return ViewHolder(view)
        Log.d("action","oncreateViewHolder")
    }

    override fun getItemCount(): Int {
        return cursor.count
    }

    override fun onBindViewHolder(
        holder: DiaryListReclycerViewAdapter.ViewHolder,
        position: Int
    ) {
        cursor.moveToPosition(position)
        val name=cursor.getString(cursor.getColumnIndex("title")).toString()
        val type =cursor.getInt(cursor.getColumnIndex("type"))
        val content=cursor.getString(cursor.getColumnIndex("content")).toString()
        val time=cursor.getString(cursor.getColumnIndex("time"))
        val isOnLock=cursor.getInt(cursor.getColumnIndex("isOnLock"))
        val info=cursor.getString(cursor.getColumnIndex("info")).toString()
        val diary=Diary(name,type,content,time,isOnLock,info)

        holder.nametext.text=name
        if(content.length>10){
            holder.wordtext.text=content.substring(0,10)+"......"
        }else{
            holder.wordtext.text=content
        }

        if(type==0){
            //学习
            holder.image.setImageResource(R.drawable.study)
        }
        if(type==1){
            holder.image.setImageResource(R.drawable.life)
        }
        if(type==2){
            holder.image.setImageResource(R.drawable.work)
        }
        if(type==3){
            holder.image.setImageResource(R.drawable.other)

        }

        holder.itemView.setOnClickListener{

            Log.d("action","点击位置${position}")

            //获取到点击的con对象
            Log.d("conn",diary.toString())

            //跳转
//            val name=findViewById<TextView>(R.id.textView_info_name)
//            val time=findViewById<TextView>(R.id.textview_info_time)
//            val info=findViewById<TextView>(R.id.textview_info_info)
//            val content=findViewById<TextView>(R.id.textview_info_content)
//            val image=findViewById<ImageView>(R.id.imageView_info)
//            var type:Int=0//类型

            val intent= Intent(it.context,Activity_diary_info::class.java)
            intent.putExtra("name",diary.title)
            intent.putExtra("time","time")
            intent.putExtra("info",diary.info)
            intent.putExtra("content",diary.content)
            intent.putExtra("type",diary.type)
            ContextCompat.startActivity(it.context, intent, null)
            Toast.makeText(it.context,"触发了itemview点击",Toast.LENGTH_SHORT).show()

        }

//        val title:String,
//        val type:Int,//0 1 2 3 4
//        val content:String,
//        val year:Int,
//        val month:Int,
//        val day:Int,
//        val isOnLock:Boolean//是否上锁

//        点击事件：

    }

}

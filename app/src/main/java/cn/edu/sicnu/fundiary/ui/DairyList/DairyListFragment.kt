package cn.edu.sicnu.fundiary.ui.DairyList

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.edu.sicnu.fundiary.Activity_diary_add
import cn.edu.sicnu.fundiary.R
import cn.edu.sicnu.fundiary.adapter.DiaryListReclycerViewAdapter
import cn.edu.sicnu.fundiary.db.DB_NAME
import cn.edu.sicnu.fundiary.db.MyDatabaseHelper
import cn.edu.sicnu.fundiary.db.TABLE_Diary
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DairyListFragment : Fragment() {

    private lateinit var dairyListViewModel: DairyListViewModel
    private lateinit var db: SQLiteDatabase
    private lateinit var cursor: Cursor
    private lateinit var inflater:LayoutInflater
    private lateinit var container:ViewGroup

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dairyListViewModel =
                ViewModelProvider(this).get(DairyListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_diary, container, false)


        val recycleView=root.findViewById<RecyclerView>(R.id.recyclerView_1)
        val floatingButton=root.findViewById<FloatingActionButton>(R.id.floatingActionButton_1)
        //context//获取上下文
        //LinearLayoutManager使用时和reclycerView出现点击时index溢出异常，现继承并重写LinearLayoutManager.onLayoutChildren()方法，写在myutils里面//在onViewBinder里面直接用position
        // 于上无关，2020.12.24错误仍未解除
        // 2020.12.25错误解除，用onViewBinder
        val layoutManager= LinearLayoutManager(context)
        layoutManager.orientation= LinearLayoutManager.VERTICAL


        //获取cursor
        val dbhelper= this.context?.let { MyDatabaseHelper(it, DB_NAME,1) } //context只使用this不得行
        if(dbhelper!=null){
            db=dbhelper.writableDatabase
        }
        cursor=db.query(TABLE_Diary,null,null,null,null,null,null)
        recycleView.layoutManager=layoutManager
        val adapter= DiaryListReclycerViewAdapter(cursor)
        recycleView.adapter=adapter

        dairyListViewModel.text.observe(viewLifecycleOwner, Observer {
            Log.d("call","list changed observe")
            val root = inflater.inflate(R.layout.fragment_diary, container, false)


            val recycleView=root.findViewById<RecyclerView>(R.id.recyclerView_1)
            val floatingButton=root.findViewById<FloatingActionButton>(R.id.floatingActionButton_1)
            //context//获取上下文
            //LinearLayoutManager使用时和reclycerView出现点击时index溢出异常，现继承并重写LinearLayoutManager.onLayoutChildren()方法，写在myutils里面//在onViewBinder里面直接用position
            // 于上无关，2020.12.24错误仍未解除
            // 2020.12.25错误解除，用onViewBinder
            val layoutManager= LinearLayoutManager(context)
            layoutManager.orientation= LinearLayoutManager.VERTICAL


            //获取cursor
            val dbhelper= this.context?.let { MyDatabaseHelper(it, DB_NAME,1) } //context只使用this不得行
            if(dbhelper!=null){
                db=dbhelper.writableDatabase
            }
            cursor=db.query(TABLE_Diary,null,null,null,null,null,null)
            recycleView.layoutManager=layoutManager
            val adapter= DiaryListReclycerViewAdapter(cursor)
            recycleView.adapter=adapter
//            textView.text = it
        })

        //fragment跳转到activity —————— 添加连接 requestcode=1
        floatingButton.setOnClickListener{
            if (activity==null){
                Toast.makeText(context,"跳不过去", Toast.LENGTH_SHORT).show()
            }else{
                val intent= Intent(this.activity, Activity_diary_add::class.java)
                startActivityForResult(intent,1)
            }
        }



        return root


    }
}


package cn.edu.sicnu.fundiary

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import cn.edu.sicnu.fundiary.db.DB_NAME
import cn.edu.sicnu.fundiary.db.MyDatabaseHelper
import cn.edu.sicnu.fundiary.db.TABLE_Diary

val DiaryList= mutableListOf<Diary>()
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_dashboard, R.id.navigation_home,  R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        //初始化数据库：建立连接表
        val dbhelper= MyDatabaseHelper(this, DB_NAME,1)
        val db=dbhelper.writableDatabase
        Log.d("call","MainActivityOnCreate")




    }
}
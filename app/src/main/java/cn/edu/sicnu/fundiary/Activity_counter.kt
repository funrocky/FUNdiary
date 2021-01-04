
//        val pause=findViewById<Button>(R.id.pause)
//        val start=findViewById<TextView>(R.id.start)
//        val restart= findViewById<TextView>(R.id.restart)

package cn.edu.sicnu.fundiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import cn.edu.sicnu.fundiary.R

class Activity_counter : AppCompatActivity() {

    var seconds=0
    var running=false
    var wasRunning=false
    //val time=findViewById<TextView>(R.id.textView11)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)
        if(savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds")
            running=savedInstanceState.getBoolean("running")
            wasRunning=savedInstanceState.getBoolean("wasRunning")
        }


        val pause=findViewById<Button>(R.id.pause)
        val start=findViewById<TextView>(R.id.start)
        val restart= findViewById<TextView>(R.id.restart)
        val time=findViewById<TextView>(R.id.textView11)

        runTimer(time)
        start.setOnClickListener{
            running=true
        }
        pause.setOnClickListener{
            running=false
        }
        restart.setOnClickListener{
            if(wasRunning){
                seconds=0
            }else{

                running=true
                seconds=0
            }

        }

    }
    fun runTimer(time:TextView){
        val handler=Handler()
        val runnable=object:Runnable{
            override fun run(){
                val hours=seconds/3600
                val minutes=(seconds%3600)/60
                val secs=seconds%60
                time.text=String.format("%02d:%02d:%02d",hours,minutes,secs)
                if(running){
                    seconds++
                }
                handler.postDelayed(this,1000)
            }
        }
        handler.post(runnable)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt("seconds",seconds)
        outState?.putBoolean("running",running)
        outState?.putBoolean("wasRunning",wasRunning)
    }

}

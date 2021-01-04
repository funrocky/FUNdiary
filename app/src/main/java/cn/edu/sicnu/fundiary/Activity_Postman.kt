package cn.edu.sicnu.fundiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class Activity_Postman : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__postman)

        val get=findViewById<Button>(R.id.btnget)
        val post=findViewById<Button>(R.id.btnpost)
        val ip=findViewById<EditText>(R.id.ip)
        val input=findViewById<EditText>(R.id.input)
        val res=findViewById<TextView>(R.id.response)
        var url:URL

//        get.setOnClickListener{
//            url= URL("https://"+ip.text.toString())
//            val connection = url.openConnection() as HttpURLConnection
//            connection.requestMethod="GET"
//            connection.connectTimeout=8000
//            connection.readTimeout=8000
//
//            val input=connection.inputStream
//            connection.disconnect()
//        }


    }
    private fun sendRequestWithHttpConnetion(url:URL){
        thread{
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = url
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            }
            catch (e:Exception){
                e.printStackTrace()
            }
            finally {
                connection?.disconnect()
            }
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread{
            val res=findViewById<TextView>(R.id.response)
            res.text=response
        }

    }
}
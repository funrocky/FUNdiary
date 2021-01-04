package cn.edu.sicnu.fundiary.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.edu.sicnu.fundiary.Activity_Postman
import cn.edu.sicnu.fundiary.Activity_counter
import cn.edu.sicnu.fundiary.Activity_diary_add
import cn.edu.sicnu.fundiary.R

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        val postman=root.findViewById<Button>(R.id.button)
        val counter=root.findViewById<Button>(R.id.button2)

        postman.setOnClickListener{
            val intent= Intent(this.activity, Activity_Postman::class.java)
            startActivity(intent)
        }
        counter.setOnClickListener{
            val intent= Intent(this.activity, Activity_counter::class.java)
            startActivity(intent)
        }

        return root
    }
}
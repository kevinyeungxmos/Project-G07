package com.example.project_g07.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Switch
import android.widget.Toast
import com.example.project_g07.R
import com.example.project_g07.adapter.ListAdapter
import com.example.project_g07.data.DataManagement
import com.example.project_g07.data.HandlePrefs
import com.example.project_g07.model.Course

class LessonListActivity : AppCompatActivity() {
    lateinit var listAdapter: ListAdapter
    val prefs = HandlePrefs(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)

//        val swSequential: Switch = findViewById(R.id.swSeq)
//        swSequential.isChecked = prefs.getSEQ()
//
//        val courseList: List<Course> = DataManagement.getInstance().courseList
//
//        listAdapter = ListAdapter(this, courseList)
//
//        val lvCourse: ListView = findViewById(R.id.lvCourse)
//        lvCourse.adapter = listAdapter
//
//        lvCourse.setOnItemClickListener(){adapterView, view, i, l ->
//            Log.d("LessonList", "$i and $l clicked")
//
//            if(swSequential.isChecked){
//                prefs.setSeq(swSequential.isChecked)
//                if(i-1>=0){
//                    if(courseList[i].isCompleted){
//                        //navigate
//                        val intent = Intent(this, LessonDetailsActivity::class.java)
//                        intent.putExtra("TO", i)
//                        startActivity(intent)
//                    }else{
//                        Toast.makeText(this, "Course ${courseList[i-1].course} not yet completed", Toast.LENGTH_LONG).show()
//                    }
//                }else{
//                    val intent = Intent(this, LessonDetailsActivity::class.java)
//                    intent.putExtra("TO", i)
//                    startActivity(intent)
//                }
//            }else{
//                prefs.setSeq(swSequential.isChecked)
//                val intent = Intent(this, LessonDetailsActivity::class.java)
//                intent.putExtra("TO", i)
//                startActivity(intent)
//            }
//        }
    }
    override fun onStart() {
        super.onStart()

        val swSequential: Switch = findViewById(R.id.swSeq)
        swSequential.isChecked = prefs.getSEQ()

        val newInstance = DataManagement.getInstance()

        prefs.syncPrefsAndDM(newInstance)

        val courseList: List<Course> = newInstance.courseList

        listAdapter = ListAdapter(this, courseList)

        val lvCourse: ListView = findViewById(R.id.lvCourse)
        lvCourse.adapter = listAdapter

        lvCourse.setOnItemClickListener(){adapterView, view, i, l ->
            Log.d("LessonList", "$i and $l clicked")

            if(swSequential.isChecked){
                prefs.setSeq(swSequential.isChecked)
                if(i-1>=0){
                    if(courseList[i-1].isCompleted){
                        //navigate
                        val intent = Intent(this, LessonDetailsActivity::class.java)
                        intent.putExtra("TO", i)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Course ${courseList[i-1].course} not yet completed", Toast.LENGTH_LONG).show()
                    }
                }else{
                    val intent = Intent(this, LessonDetailsActivity::class.java)
                    intent.putExtra("TO", i)
                    startActivity(intent)
                }
            }else{
                prefs.setSeq(swSequential.isChecked)
                val intent = Intent(this, LessonDetailsActivity::class.java)
                intent.putExtra("TO", i)
                startActivity(intent)
            }
        }
    }
}
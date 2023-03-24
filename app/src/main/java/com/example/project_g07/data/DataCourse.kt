package com.example.project_g07.data

import android.content.SharedPreferences

class DataCourse {

    data class Course(val course:String, val code: Int, val length: Double, val isCompleted:Boolean = false){
        var note:String = ""
    }

    var name:String? = null
    var courseList:List<Course> =
        listOf<Course>(
            Course("course1", 1, 1.0),
            Course("course2", 2, 1.5),
            Course("course3", 3, 2.5)
        )

    private constructor(){}
    companion object{
        @Volatile
        private lateinit var instance: DataCourse

        fun getInstance(): DataCourse {
            synchronized(this){
                if(!::instance.isInitialized){
                    instance = DataCourse()
                }
                return instance
            }
        }
    }

    fun updateNote( code:Int, note:String){
        courseList[code].note  = note
    }

}
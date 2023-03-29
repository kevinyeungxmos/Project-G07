package com.example.project_g07.model

class Course(var course:String, var code: Int, var length: String, var description:String,
             var videoLink:String, var isCompleted:Boolean = false){
    lateinit var note: String
}
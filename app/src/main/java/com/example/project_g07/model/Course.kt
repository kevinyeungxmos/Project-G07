package com.example.project_g07.model

class Course(var course:String, var code: Int, var length: Double, var description:String, var isCompleted:Boolean = false){
    lateinit var note: String
}
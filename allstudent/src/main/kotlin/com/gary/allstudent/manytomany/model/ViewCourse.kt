package com.gary.allstudent.manytomany.model

data class ViewCourse(
    val id: Long,
    val name: String,
    val studentsEnrolled: Iterable<String>
)

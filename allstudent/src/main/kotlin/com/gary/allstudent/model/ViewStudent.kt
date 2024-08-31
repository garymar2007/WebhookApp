package com.gary.allstudent.model

data class ViewStudent(
    val id: Long,
    val name: String,
    val coursesEnrolledIn: Iterable<String>
)

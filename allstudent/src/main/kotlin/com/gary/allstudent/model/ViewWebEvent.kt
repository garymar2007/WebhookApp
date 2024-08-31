package com.gary.allstudent.model

data class ViewWebEvent(
    val id: Int,
    val eventType: String,
    val endPointUrl: String,
    val schoolId: Int
)

package com.gary.allstudent.onetomany.model

data class ViewWebEvent(
    val id: Int,
    val eventType: String,
    val endPointUrl: String,
    val schoolId: Int
)

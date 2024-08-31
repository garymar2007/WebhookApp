package com.gary.allstudent.onetomany.model

data class ViewSchoolData(
    val id: Int,
    val schoolName: String,
    val address: String,
    val phone: String,
    val email: String,
    val website: String,
    val logo: String,
    val webhookdetailId: List<Int>,
    val pupilId: List<Int>
)

package com.gary.allstudent.repository

import com.gary.allstudent.model.SchoolData
import org.springframework.data.repository.CrudRepository

interface SchoolDataRepository : CrudRepository<SchoolData, Int> {
    fun findSchoolDataBySchoolName(schoolName: String): SchoolData?
}
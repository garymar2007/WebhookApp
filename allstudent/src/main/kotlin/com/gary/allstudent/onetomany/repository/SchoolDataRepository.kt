package com.gary.allstudent.onetomany.repository

import com.gary.allstudent.onetomany.model.SchoolData
import org.springframework.data.jpa.repository.JpaRepository

interface SchoolDataRepository : JpaRepository<SchoolData, Int> {
}
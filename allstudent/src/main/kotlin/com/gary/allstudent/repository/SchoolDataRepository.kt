package com.gary.allstudent.repository

import com.gary.allstudent.model.SchoolData
import org.springframework.data.jpa.repository.JpaRepository

interface SchoolDataRepository : JpaRepository<SchoolData, Int> {
}
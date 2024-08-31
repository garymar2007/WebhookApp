package com.gary.allstudent.onetomany.service

import com.gary.allstudent.onetomany.model.SchoolData
import com.gary.allstudent.onetomany.repository.SchoolDataRepository
import org.springframework.stereotype.Service

@Service
class SchoolDataService(
    private val schoolDataRepository: SchoolDataRepository
){
    fun addSchoolData(schoolData: SchoolData): SchoolData {
        return schoolDataRepository.save(schoolData)
    }

    fun findById(schoolId: Int): SchoolData? {
        return schoolDataRepository.findById(schoolId).orElse(null)
    }

    fun save(schoolData: SchoolData): SchoolData = schoolDataRepository.save(schoolData)
}
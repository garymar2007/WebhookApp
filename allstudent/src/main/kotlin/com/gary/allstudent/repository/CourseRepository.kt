package com.gary.allstudent.repository

import com.gary.allstudent.model.Course
import org.springframework.data.repository.CrudRepository

interface CourseRepository: CrudRepository<Course, Long> {
}
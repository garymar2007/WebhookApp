package com.gary.allstudent.manytomany.repository

import com.gary.allstudent.manytomany.model.Course
import org.springframework.data.repository.CrudRepository

interface CourseRepository: CrudRepository<Course, Long> {
}
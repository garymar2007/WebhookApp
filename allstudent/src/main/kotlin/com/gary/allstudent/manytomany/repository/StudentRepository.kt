package com.gary.allstudent.manytomany.repository

import com.gary.allstudent.manytomany.model.Student
import org.springframework.data.repository.CrudRepository

interface StudentRepository: CrudRepository<Student, Long> {
}
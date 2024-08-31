package com.gary.allstudent.repository

import com.gary.allstudent.model.Student
import org.springframework.data.repository.CrudRepository

interface StudentRepository: CrudRepository<Student, Long> {
}
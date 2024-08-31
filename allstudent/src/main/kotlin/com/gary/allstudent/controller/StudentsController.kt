package com.gary.allstudent.controller

import com.gary.allstudent.model.EnrollInCourse
import com.gary.allstudent.model.ViewStudent
import com.gary.allstudent.model.toView
import com.gary.allstudent.repository.CourseRepository
import com.gary.allstudent.repository.StudentRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/students")
class StudentsController(
    val studentRepository: StudentRepository,
    val courseRepository: CourseRepository
) {
    @GetMapping
    fun findAllStudents(): Iterable<ViewStudent> =
        studentRepository.findAll().map {it.toView()}

    @PostMapping("/{id}/enroll")
    fun enrollStudentInCourse(@PathVariable id: Long, @RequestBody enrollInCourse: EnrollInCourse) : ViewStudent {
        val student = studentRepository.findById(id).orElseThrow { throw IllegalArgumentException("Student not found") }
        val course = courseRepository.findById(enrollInCourse.courseId).orElseThrow { throw IllegalArgumentException("Course not found") }

        return studentRepository.save(
            student.copy(
                enrolledIn = student.enrolledIn.plus(course)
            )
        ).toView()
    }
}
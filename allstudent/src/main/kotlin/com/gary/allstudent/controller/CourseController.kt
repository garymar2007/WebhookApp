package com.gary.allstudent.controller

import com.gary.allstudent.model.ViewCourse
import com.gary.allstudent.model.toView
import com.gary.allstudent.repository.CourseRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/courses")
class CourseController (
    private val courseRepository: CourseRepository,
) {
    @GetMapping
    fun findAllCourses(): Iterable<ViewCourse> =
        courseRepository.findAll().map { it.toView() }
}
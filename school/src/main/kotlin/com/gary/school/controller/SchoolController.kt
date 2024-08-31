package com.gary.school.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/webhook/studentAdded")
class SchoolController{
    @GetMapping("/{studentName}")
    fun studentAdded(@PathVariable studentName: String): ResponseEntity<String> {
        println("Student added: $studentName")
        return ResponseEntity.ok("Webhook received")
    }
}
package com.example.forohub.controller;

import com.example.forohub.model.dto.CourseAddDTO;
import com.example.forohub.model.dto.CourseDTO;
import com.example.forohub.service.CourseService;
import com.example.forohub.service.impl.ICourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody @Valid CourseAddDTO courseAddDTO){
        var course = courseService.createCourse(courseAddDTO);
        var uri = UriComponentsBuilder.fromPath("/courses/{id}").buildAndExpand(course.id()).toUri();
        return ResponseEntity.created(uri).body(course);
    }
}

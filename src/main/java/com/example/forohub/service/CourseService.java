package com.example.forohub.service;

import com.example.forohub.model.dto.CourseAddDTO;
import com.example.forohub.model.dto.CourseDTO;

public interface CourseService {
    CourseDTO createCourse(CourseAddDTO courseAddDTO);
}

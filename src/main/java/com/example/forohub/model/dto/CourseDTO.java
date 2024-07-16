package com.example.forohub.model.dto;

import com.example.forohub.model.Course;

public record CourseDTO(
        Long id,
        String name,
        String category
) {
    public CourseDTO(Course course) {
        this(course.getId(), course.getName(), course.getCategory());
    }
}

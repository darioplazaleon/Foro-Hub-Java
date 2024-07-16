package com.example.forohub.service.impl;

import com.example.forohub.model.Course;
import com.example.forohub.model.dto.CourseAddDTO;
import com.example.forohub.model.dto.CourseDTO;
import com.example.forohub.repository.CourseRepository;
import com.example.forohub.service.CourseService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICourseService implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public CourseDTO createCourse(CourseAddDTO courseAddDTO) {
        if(courseRepository.existsByName(courseAddDTO.name())) {
            throw new EntityExistsException("El curso ya existe");
        }

        var course = new Course(courseAddDTO);
        courseRepository.save(course);
        return new CourseDTO(course);
    }
}

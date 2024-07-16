package com.example.forohub.model.dto;

import jakarta.validation.constraints.NotBlank;
import org.aspectj.weaver.ast.Not;

public record CourseAddDTO(
        @NotBlank
        String name,
        @NotBlank
        String category
) {
}

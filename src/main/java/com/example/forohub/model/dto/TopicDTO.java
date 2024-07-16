package com.example.forohub.model.dto;

import com.example.forohub.model.Topic;

import java.time.LocalDateTime;

public record TopicDTO(
        Long id,
        String title,
        String content,
        String author,
        String course,
        LocalDateTime createdAt
) {
    public TopicDTO(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getContent(), topic.getAuthor().getUsername(), topic.getCourse().getName(), topic.getCreatedDate());
    }
}

package com.example.forohub.service;

import com.example.forohub.model.dto.TopicAddDTO;
import com.example.forohub.model.dto.TopicDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicService {
    TopicDTO createTopic(TopicAddDTO topicAddDTO);
    Page<TopicDTO> getAllTopics(Pageable pageable);
    TopicDTO getTopicById(Long id);
    TopicDTO updateTopic(Long id, TopicAddDTO topicAddDTO);
    void deleteTopic(Long id);
}

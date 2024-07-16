package com.example.forohub.service.impl;

import com.example.forohub.model.Course;
import com.example.forohub.model.Topic;
import com.example.forohub.model.dto.TopicAddDTO;
import com.example.forohub.model.dto.TopicDTO;
import com.example.forohub.repository.CourseRepository;
import com.example.forohub.repository.TopicRepository;
import com.example.forohub.repository.UserRepository;
import com.example.forohub.service.TopicService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ITopicService implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public TopicDTO createTopic(TopicAddDTO topicAddDTO) {
        if(topicRepository.existsByTitle(topicAddDTO.title())) {
            throw new EntityExistsException("El topico ya existe");
        }

        var user = userRepository.findById(topicAddDTO.userId());
        var course = courseRepository.findById(topicAddDTO.courseId());

        if (user.isEmpty()) {
            throw new ValidationException("El usuario no existe");
        }

        if (course.isEmpty()) {
            throw new ValidationException("El curso no existe");
        }

        var topic = new Topic(topicAddDTO, user.get(), course.get());
        topicRepository.save(topic);

        return new TopicDTO(topic);
    }

    @Override
    public Page<TopicDTO> getAllTopics(Pageable pageable) {
        return topicRepository.findAll(pageable).map(TopicDTO::new);
    }

    @Override
    public TopicDTO getTopicById(Long id) {
        return topicRepository.findById(id).map(TopicDTO::new).orElse(null);
    }

    @Override
    public TopicDTO updateTopic(Long id, TopicAddDTO topicAddDTO) {
        var foundTopic = topicRepository.findById(id);

        if (foundTopic.isEmpty()){
            throw new EntityNotFoundException("El topico no existe");
        }

        Course course = null;

        if (topicAddDTO.courseId() != null) {
            var foundCourse = courseRepository.findById(topicAddDTO.courseId());
            if (foundCourse.isEmpty()) {
                throw new ValidationException("El curso no existe");
            }
            course = foundCourse.get();
        }

        var topic = foundTopic.get();
        topic.updateInfo(topicAddDTO, course);

        return new TopicDTO(topic);
    }

    @Override
    public void deleteTopic(Long id) {
        var foundTopic = topicRepository.findById(id);

        if (foundTopic.isPresent()){
            topicRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("El topico no existe");
        }
    }
}

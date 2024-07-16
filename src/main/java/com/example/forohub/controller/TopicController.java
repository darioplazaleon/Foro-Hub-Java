package com.example.forohub.controller;

import com.example.forohub.model.dto.TopicAddDTO;
import com.example.forohub.model.dto.TopicDTO;
import com.example.forohub.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicDTO> createTopic(@RequestBody @Valid TopicAddDTO topicAddDTO) {
        var topic = topicService.createTopic(topicAddDTO);
        var uri = UriComponentsBuilder.fromPath("/topics/{id}").buildAndExpand(topic.id()).toUri();
        return ResponseEntity.created(uri).body(topic);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<TopicDTO>> getAllTopics(Pageable pageable) {
        return ResponseEntity.ok(topicService.getAllTopics(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDTO> getTopicById(@PathVariable Long id) {
        var findTopic = topicService.getTopicById(id);
        if (findTopic == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findTopic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicDTO> updateTopic(@PathVariable Long id, @RequestBody TopicAddDTO topicAddDTO){
        var findTopic = topicService.getTopicById(id);
        if (findTopic == null) {
            return ResponseEntity.notFound().build();
        }
        var topic = topicService.updateTopic(id, topicAddDTO);
        return ResponseEntity.ok(topic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TopicDTO> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}

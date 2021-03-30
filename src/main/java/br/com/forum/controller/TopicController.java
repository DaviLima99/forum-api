package br.com.forum.controller;

import br.com.forum.controller.form.TopicForm;
import br.com.forum.dto.TopicDto;
import br.com.forum.model.Topic;
import br.com.forum.repository.CourseRepository;
import br.com.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<TopicDto> list(String courseName){
        if (courseName == null) {
            List<Topic> topics = topicRepository.findAll();
            return TopicDto.convert(topics);
        }

        List<Topic> topics = topicRepository.findByCourseName(courseName);
        return  TopicDto.convert(topics);
    }
    @PostMapping
    public ResponseEntity<TopicDto> create(@RequestBody TopicForm data,
                                           UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = data.convert(courseRepository);
        topicRepository.save(topic);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri())
                .body(new TopicDto(topic));
    }
}

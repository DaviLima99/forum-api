package br.com.forum.controller;

import br.com.forum.controller.form.TopicForm;
import br.com.forum.controller.form.TopicUpdateForm;
import br.com.forum.dto.TopicDetailDto;
import br.com.forum.dto.TopicDto;
import br.com.forum.model.Topic;
import br.com.forum.repository.CourseRepository;
import br.com.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
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
    @Transactional
    public ResponseEntity<TopicDto> create(@RequestBody @Valid TopicForm data,
                                           UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = data.convert(courseRepository);
        topicRepository.save(topic);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri())
                .body(new TopicDto(topic));
    }

    @GetMapping("/{id}")
    public TopicDetailDto detail(@PathVariable Long id) {
        Topic topic = topicRepository.getOne(id);
        return new TopicDetailDto(topic);
    }

    @PutMapping("{/id}")
    @Transactional
    public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid TopicUpdateForm form) {
        Topic topic = form.update(id, topicRepository);

        return ResponseEntity.ok(new TopicDto(topic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        topicRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}

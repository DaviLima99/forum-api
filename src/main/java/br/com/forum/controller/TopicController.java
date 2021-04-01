package br.com.forum.controller;

import br.com.forum.controller.form.TopicForm;
import br.com.forum.controller.form.TopicUpdateForm;
import br.com.forum.dto.TopicDetailDto;
import br.com.forum.dto.TopicDto;
import br.com.forum.model.Topic;
import br.com.forum.repository.CourseRepository;
import br.com.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public Page<TopicDto> list(@RequestParam(required = false) String courseName,
                            @RequestParam int page, @RequestParam int qty) {
        Pageable pagination = PageRequest.of(page, qty);

        if (courseName == null) {
            Page<Topic> topics = topicRepository.findAll(pagination);
            return TopicDto.convert(topics);
        }

        Page<Topic> topics = topicRepository.findByCourseName(courseName, pagination);
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
    public ResponseEntity<TopicDetailDto> detail(@PathVariable Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        return topic.map(value -> ResponseEntity.ok(
                new TopicDetailDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{/id}")
    @Transactional
    public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid TopicUpdateForm form) {
        Optional<Topic> topic = topicRepository.findById(id);
        if (topic.isPresent()) {
            Topic topicUpdated = form.update(id, topicRepository);
            return ResponseEntity.ok(new TopicDto(topicUpdated));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        if (topic.isPresent()) {
            topicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}

package br.com.forum.controller;

import br.com.forum.dto.TopicDto;
import br.com.forum.model.Topic;
import br.com.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @RequestMapping("/topics")
    public List<TopicDto> list(String courseName){
        if (courseName == null) {
            List<Topic> topics = topicRepository.findAll();
            return TopicDto.convert(topics);
        }

        List<Topic> topics = topicRepository.findByCourseName(courseName);
        return  TopicDto.convert(topics);

    }
}

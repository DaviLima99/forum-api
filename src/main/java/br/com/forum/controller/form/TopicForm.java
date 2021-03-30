package br.com.forum.controller.form;

import br.com.forum.model.Course;
import br.com.forum.model.Topic;
import br.com.forum.repository.CourseRepository;
import br.com.forum.repository.TopicRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicForm {
    
    private String title;
    private String message;
    private String courseName;

    public Topic convert(CourseRepository repository) {
        Course course = repository.findByName(this.courseName);
        return new Topic(this.title, this.message, course);
    }
}

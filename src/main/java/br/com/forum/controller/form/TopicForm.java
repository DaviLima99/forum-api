package br.com.forum.controller.form;

import br.com.forum.model.Course;
import br.com.forum.model.Topic;
import br.com.forum.repository.CourseRepository;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TopicForm {

    @NotEmpty @NotNull @Length(min = 5)
    private String title;

    @NotEmpty @NotNull @Length(min = 15)
    private String message;

    @NotEmpty @NotNull
    private String courseName;

    public Topic convert(CourseRepository repository) {
        Course course = repository.findByName(this.courseName);
        return new Topic(this.title, this.message, course);
    }
}

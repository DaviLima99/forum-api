package br.com.forum.controller.form;

import br.com.forum.model.Topic;
import br.com.forum.repository.TopicRepository;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TopicUpdateForm {

    @NotEmpty
    @NotNull
    @Length(min = 5)
    private String title;

    @NotEmpty @NotNull @Length(min = 15)
    private String message;

    public Topic update(Long id, TopicRepository topicRepository) {
        Topic topic = topicRepository.getOne(id);
        topic.setTitle(this.title);
        this.setMessage(this.message);

        return topic;
    }
}

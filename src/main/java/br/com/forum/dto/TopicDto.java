package br.com.forum.dto;

import br.com.forum.model.Topic;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TopicDto {
    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;

    public TopicDto(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.createdAt = topic.getCreatedAt();
    }

    public static List<TopicDto> convert(List<Topic> topics) {
        return topics.stream().map(TopicDto::new).collect(Collectors.toList());
    }
}

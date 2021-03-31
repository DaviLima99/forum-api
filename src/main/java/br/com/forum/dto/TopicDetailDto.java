package br.com.forum.dto;

import br.com.forum.model.Topic;
import br.com.forum.model.TopicStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TopicDetailDto {
    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;
    private String nameAuthor;
    private TopicStatus status;
    private List<ResponseDto> responses;

    public TopicDetailDto(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.createdAt = topic.getCreatedAt();
        this.nameAuthor = topic.getAuthor().getName();
        this.status = topic.getStatus();
        this.responses = new ArrayList<>();
        this.responses.addAll(topic.getResponses()
                .stream()
                .map(ResponseDto::new)
                .collect(Collectors.toList()));
    }
}

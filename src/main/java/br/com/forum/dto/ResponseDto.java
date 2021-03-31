package br.com.forum.dto;

import br.com.forum.model.Response;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseDto {

    private Long id;
    private String message;
    private LocalDateTime createdAt;
    private String nameAuthor;

    public ResponseDto(Response response) {
        this.id = response.getId();
        this.message = response.getMessage();
        this.createdAt = response.getCreatedAt();
        this.nameAuthor = response.getAuthor().getName();
    }
}

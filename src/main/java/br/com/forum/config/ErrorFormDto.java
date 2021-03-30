package br.com.forum.config;

import lombok.Getter;

@Getter
public class ErrorFormDto {

    private String field;
    private String error;

    public ErrorFormDto(String field, String error) {
        this.field = field;
        this.error = error;
    }
}

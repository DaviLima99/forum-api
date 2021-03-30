package br.com.forum.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Topic {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdAt = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private TopicStatus status = TopicStatus.NAO_RESPONDIDO;
    @ManyToOne
    private User author;
    @ManyToOne
    private Course course;
    @OneToMany(mappedBy = "topic")
    private List<Response> responses = new ArrayList<>();

    public Topic(String title, String messsage, Course course) {
        this.title = title;
        this.message = messsage;
        this.course = course;
    }

    public Topic() {

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Topic other = (Topic) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }
}

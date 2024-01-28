package ru.otus.hw.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comments")
public class Comment {
    @Id
    @Field(name = "comment_id")
    private Long id;

    private String text;

    private Book book;

    public Comment(String text, Book book) {
        this.text = text;
        this.book = book;
    }
}

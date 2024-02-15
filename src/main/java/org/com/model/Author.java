package org.com.model;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Author {
    private int id;
    private String firstName;
    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

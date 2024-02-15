package org.com.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Member {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String sex;
    private String login;
    private String password;
    private String email;

    public Member(String firstName, String lastName, int age, String sex, String login, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.login = login;
        this.password = password;
        this.email = email;
    }
}

package com.spring.psring_postman.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String userName;

    String password;

    String fullName;//ho ten

    String gender;//gioi tinh

    String address;

    String grade;

    String major;//chuyen nganh

    String department;//khoa

    String trainingSystem;//he dao tao

    String cohort;//khoa hoc

    LocalDate dob;

    @ElementCollection
    Set<String> roles;
}

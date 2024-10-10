package com.spring.psring_postman.dto.reponse;


import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Transient;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)//
public class UserReponse {
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

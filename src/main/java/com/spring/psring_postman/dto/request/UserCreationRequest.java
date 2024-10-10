package com.spring.psring_postman.dto.request;

import com.spring.psring_postman.exception.ErrorCode;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

//@Getter
//@Setter thay vi dung 2 cai nhu z cta dung DAta
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)// tat ca field se thanh private -> nhin gon gang hon
public class UserCreationRequest {

    @Size(min = 5, message = "USERNAME_INVALID")
    String userName;

    @Size(min = 4, max = 20, message = "PASSWORD_INVALID")
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

}

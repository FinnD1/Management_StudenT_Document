package com.spring.psring_postman.dto.request;

import jakarta.persistence.ElementCollection;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)//
public class UserUpdateRequest {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getGrade() {
        return grade;
    }

    public void setClass(String aClass) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTrainingSystem() {
        return trainingSystem;
    }

    public void setTrainingSystem(String trainingSystem) {
        this.trainingSystem = trainingSystem;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}

package com.unsa.applicants.domain;

import java.io.Serializable;

public class Applicant implements Serializable {

    private String document;
    private String name;
    private String lastname;
    private String birthday;
    private String school;
    private String career;

    public Applicant(String document, String name, String lastname, String birthday, String school, String career) {
        this.document = document;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.school = school;
        this.career = career;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

}

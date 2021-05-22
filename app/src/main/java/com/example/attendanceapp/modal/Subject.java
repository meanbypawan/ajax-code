package com.example.attendanceapp.modal;

public class Subject {
  private int id;
  private String subjectTitle;

    public Subject(int id, String subjectTitle) {
        this.id = id;
        this.subjectTitle = subjectTitle;
    }

    public Subject(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }
    public Subject(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }
    public String toString(){
        return subjectTitle;
    }
}

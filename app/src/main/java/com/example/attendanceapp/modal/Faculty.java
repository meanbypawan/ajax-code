package com.example.attendanceapp.modal;

public class Faculty {
  private int id;
  private String name;
  private String mobile;
  private String password;

    public Faculty(int id, String name, String mobile, String password) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.password = password;
    }
    public Faculty(){

    }

    public Faculty(String name, String mobile, String password) {
        this.name = name;
        this.mobile = mobile;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

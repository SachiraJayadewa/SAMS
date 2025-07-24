package com.ijse.cmjd111.student.model;

public class Student {
    private int student_id;
    private String name;
    private String reg_number;
    private String course;
    private String contact;
    private String password;



    public Student(int student_id, String name, String reg_number, String course, String contact) {
        this.student_id = student_id;
        this.name = name;
        this.reg_number = reg_number;
        this.course = course;
        this.contact = contact;
    }

    public Student(String name, String reg_number, String course, String contact) {
        this.name = name;
        this.reg_number = reg_number;
        this.course = course;
        this.contact = contact;
    }



    public Student() {
    }




    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public String getReg_number() {
        return reg_number;
    }

    public void setReg_number(String reg_number) {
        this.reg_number = reg_number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", name='" + name + '\'' +
                ", reg_number='" + reg_number + '\'' +
                ", course='" + course + '\'' +
                ", contact='" + contact + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

package com.ijse.cmjd111.student.util;

public class LoggedInUser {
    private static LoggedInUser instance;

    private int userId;
    private String username;
    private String role;
    private Integer studentId;
    private Integer lecturerId;

    private LoggedInUser() {}

    public static LoggedInUser getInstance() {
        if (instance == null) {
            instance = new LoggedInUser();
        }
        return instance;
    }

    public void setUserSession(int userId, String username, String role, Integer studentId, Integer lecturerId) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.studentId = studentId;
        this.lecturerId = lecturerId;
    }

    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public Integer getStudentId() { return studentId; }
    public Integer getLecturerId() { return lecturerId; }
}



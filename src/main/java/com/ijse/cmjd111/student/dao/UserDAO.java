package com.ijse.cmjd111.student.dao;

import com.ijse.cmjd111.student.model.User;
import com.ijse.cmjd111.student.util.DBConnection;

import java.sql.*;

public class UserDAO {

    public User validateUser(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));

                // ✅ These may be null
                Object studentObj = rs.getObject("student_id");
                if (studentObj != null) {
                    user.setStudentId((Integer) studentObj);
                }

                Object lecturerObj = rs.getObject("lecturer_id");
                if (lecturerObj != null) {
                    user.setLecturerId((Integer) lecturerObj);
                }

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // invalid login
    }
}



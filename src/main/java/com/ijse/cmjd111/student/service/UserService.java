package com.ijse.cmjd111.student.service;

import com.ijse.cmjd111.student.dao.UserDAO;
import com.ijse.cmjd111.student.model.User;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public User validateLogin(String username, String password) {
        return userDAO.validateUser(username, password);
    }
}


package com.masters.waterways.services;

import com.masters.waterways.daos.EmployeeDao;
import com.masters.waterways.daos.UsersDao;
import com.masters.waterways.models.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthenticationService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private EmployeeDao employeeDao;

    private final String SESSION_AUTH_KEY = "AUTH_USER";
    private final String SESSION_USER_NAME = "AUTH_USERNAME";
    private final String SESSION_ADMIN = "AUTH_ADMIN";

    public boolean authenticateUser (Users user, String password, HttpSession session) {
        if (user != null && user.getUserPassword().equals(password)) {

            int isAdmin = employeeDao.isEmployee(user.getUserId()) ? 1: 0;

            System.out.println("Checked employees:");
            System.out.println(isAdmin);

            session.setAttribute(SESSION_AUTH_KEY, user.getUserId());
            session.setAttribute(SESSION_USER_NAME, usersDao.getById(user.getUserId()).getUserName());
            session.setAttribute(SESSION_ADMIN, isAdmin);

            return true;
        } else
            return false;
    }

    public void logoutUser(HttpSession session){
        session.removeAttribute(SESSION_AUTH_KEY);
        session.removeAttribute(SESSION_USER_NAME);
        session.removeAttribute(SESSION_ADMIN);
    }

    public boolean isAuthenticated(HttpSession session){
        return getCurrentUser(session) != null;
    }

    public Integer getCurrentUser(HttpSession session){
        try {
            return Integer.parseInt(session.getAttribute(SESSION_AUTH_KEY).toString());
        } catch (Exception e){
            return null;
        }
    }

    public Boolean isAdmin(HttpSession session) {
        return Integer.parseInt(session.getAttribute(SESSION_ADMIN).toString()) == 1;
    }

//    public boolean checkCredentials(int userID, String password){
//        Users user = usersDao.getById(userID);
//        return user.getUserPassword().equals(password);
//    }
//
//    public void loginUser(HttpSession session, int userID){
//
//        int isAdmin = isAdmin(session)? 1: 0;
//
//    }

}

package com.masters.waterways.services;

import com.masters.waterways.daos.UsersDao;
import com.masters.waterways.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthenticationService {
    @Autowired
    private UsersDao usersDao;
    private String SESSION_AUTH_KEY;

    public Boolean checkCredentials(int id, String password) {
        Users user = usersDao.getById(id);
        return user.getUserPassword().equals(password);
    }

    public void loginUser(HttpSession session, int id) {
        session.setAttribute(SESSION_AUTH_KEY, id);
    }

    public void logoutUser(HttpSession session) {
        session.removeAttribute(SESSION_AUTH_KEY);
    }

    public Integer getCurrentUser(HttpSession session) {
        try {
            return Integer.parseInt(session.getAttribute(SESSION_AUTH_KEY).toString());
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean isAuthenticated(HttpSession session) {
        return getCurrentUser(session) != null;
    }

    public Boolean isAdmin(HttpSession session){
//        try{
//
//        }
        return false;
    }
}
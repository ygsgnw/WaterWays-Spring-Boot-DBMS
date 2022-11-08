package com.masters.waterways.services;

import com.masters.waterways.daos.EmployeeDao;
import com.masters.waterways.daos.UsersDao;
import com.masters.waterways.models.Employee;
import com.masters.waterways.models.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service()
public class AuthenticationService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private EmployeeDao employeeDao;

    private String SESSION_AUTH_KEY = "AUTH_USER";
    private String SESSION_USER_NAME = "AUTH_USERNAME";

//    private String SESSION_ADMIN = "AUTH_ADMIN";

    public boolean checkCredentials(int userID, String password){
        Users user = usersDao.getById(userID);
        return user.getUserPassword().equals(password);
    }

    public void loginUser(HttpSession session, int userID){

//        Boolean isadmin = isAdmin(session);
//        session.setAttribute(SESSION_ADMIN,isadmin);
        session.setAttribute(SESSION_AUTH_KEY,userID);
        session.setAttribute(SESSION_USER_NAME,usersDao.getById(userID).getUserName());
    }

    public void logoutUser(HttpSession session){
//        session.removeAttribute(SESSION_ADMIN);
        session.removeAttribute(SESSION_AUTH_KEY);
        session.removeAttribute(SESSION_USER_NAME);
    }

    public Integer getCurrentUser(HttpSession session){
        try {
            return Integer.parseInt(session.getAttribute(SESSION_AUTH_KEY).toString());
        } catch (Exception e){
            System.out.println("Exception in Authentication service yayyy :-)");
            return null;
        }
    }

    public Boolean isAuthenticated(HttpSession session){
        return getCurrentUser(session) != null;
    }

    public Boolean isAdmin(HttpSession session){

        int id = Integer.parseInt(session.getAttribute(SESSION_AUTH_KEY).toString());
        return employeeDao.isEmployee(id);
//        try{
//            emp
//            Employee user = employeeService.getEmployeeByID(Integer.parseInt(session.getAttribute(SESSION_AUTH_KEY).toString()));
//            return user.getRole()==1;
//        }
//        catch (Exception e){
//            System.out.println("Exception in isAdmin");
//            return false;
//        }
    }



}

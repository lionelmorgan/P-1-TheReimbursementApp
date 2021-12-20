package services;

import dao.UsersDaoImpl;
import dao.UsersDao;
import models.Reimbursements;
import models.Users;

import java.util.List;

public class UsersService {

    static UsersDao usersDao;

    public UsersService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public UsersService(){this.usersDao = new UsersDaoImpl();}

    public List<Users> getAllUsers() { return usersDao.getAllUsers();}

    public Users getUser(Integer id){ return usersDao.getUser(id);}

//    public Users loginUser(Users user){
//        Users user1 = usersDao.loginUser(user);
//        if(user.getPassword() == user1.getPassword()){
//            return usersDao.loginUser(user);
//        }else{
//            return null;
//        }
//
//
//    }

    //if to check password

//    public Users checkSession(Users user){ return usersDao.checkSession(user);}

    public static Boolean createUser(Users user){
        if((user.getUsername().length()) > 50) {
            return false;
        }
        else if(user.getPassword().length() > 50) {
            return false;
        }
        else if(user.getFirst_name().length() > 100) {
            return false;
        }
        else if(user.getLast_name().length() > 100) {
            return false;
        }
        else if(user.getUser_email().length() > 150) {
            return false;
        }
        else if(user.getUser_role_id() == null){
            return false;
        }
        else {
            usersDao.createUser(user);
            return true;
        }
    }
}

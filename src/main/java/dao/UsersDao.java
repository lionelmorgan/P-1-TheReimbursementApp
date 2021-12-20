package dao;

import models.Users;

import java.util.List;

public interface UsersDao {

    List<Users> getAllUsers();
    Users getUser(Integer userId);
    void createUser(Users user);
//    Users loginUser(Users user);
//    Users checkSession(Users user);
    void updateUser(Users user);
    void deleteUser(Users user);
}

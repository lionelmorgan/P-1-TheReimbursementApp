package dao;

import models.Users;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements UsersDao {

    String url;
    String username;
    String password;

    Logger logger = Logger.getLogger(ReimbursementsDaoImpl.class);

    public UsersDaoImpl(){
        this.url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/reimbursementdatabase";
        this.username =  System.getenv("RDS_USERNAME");
        this.password = System.getenv("RDS_PASSWORD");
    }

    public UsersDaoImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Users> getAllUsers() {

        List<Users> users = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "SELECT * FROM users;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            logger.info("getAllUsers");

            while (rs.next()){
                users.add(new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7)));
            }
        } catch (SQLException u){
            logger.error(u);
        }

        return users;
    }

    @Override
    public Users getUser(Integer userId) {
        Users user = null;

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM users where user_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            logger.info("getUser");

            while(rs.next()){
                user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7));
            }
        }catch (SQLException u){
            logger.error(u);
        }
        return user;
    }

    @Override
    public void createUser(Users user) {

        try{
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO users VALUES(DEFAULT, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirst_name());
            ps.setString(4, user.getLast_name());
            ps.setString(5, user.getUser_email());
            ps.setInt(6, user.getUser_role_id());

            ps.executeUpdate();

            logger.info("createUser");


        }catch(SQLException u){
            logger.error(u);
        }

    }

//    @Override
//    public Users loginUser(Users user) {
//        try{
//            Connection conn = DriverManager.getConnection(url, username, password);
//
//            String sql = "SELECT * FROM users WHERE username = ?;"; //password
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, user.getUsername());
//            //ps.setInt(2, user.getUser_role_id());
//            ps.executeUpdate();
//
//        }catch(SQLException u){
//            logger.error(u);
//        }
//        return user;
//    }
//
//    @Override
//    public Users checkSession(Users user) {
//        try {
//            Connection conn = DriverManager.getConnection(url, username, password);
//
//            String sql = "SELECT * FROM users WHERE username = ? AND user_role_id = ?;";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, user.getUsername());
//            ps.setInt(2, user.getUser_role_id());
//            ps.executeUpdate();
//
//        } catch (SQLException u) {
//            logger.error(u);
//        }
//        return user;
//    }

    @Override
    public void updateUser(Users user) {

    }

    @Override
    public void deleteUser(Users user) {

    }
}

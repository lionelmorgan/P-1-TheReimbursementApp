package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UsersDTO;
import io.javalin.http.Context;
import models.JsonResponse;
import models.Reimbursements;
import models.Users;
import dao.UsersDaoImpl;
import services.UsersService;

import java.util.List;

public class UsersController {

    static UsersService usersService = new UsersService(new UsersDaoImpl());

    public static void getAllUsers(Context context) throws JsonProcessingException {
        context.contentType("application/json");
        List<Users> usersList = usersService.getAllUsers();
        String jsonString = new ObjectMapper().writeValueAsString(usersList);
        context.result(jsonString);
    }

    public static void getUser(Context context) throws JsonProcessingException {
        context.contentType("application/json");
        Integer userId = Integer.parseInt(context.pathParam("id"));
        Users user = usersService.getUser(userId);
        context.status(200).result(new ObjectMapper().writeValueAsString(user));
    }

    public static void createUser(Context context) {
        Users user = context.bodyAsClass(Users.class);
        if(usersService.createUser(user)){
            context.status(201).result("User successfully created");
        }else{
            context.status(404).result("User creation failed");
        }

    }

//    public static void loginUser(Context context) {
//        Users user = context.bodyAsClass(Users.class);
//        System.out.println(user);
//        if(user == null){
//            context.status(404).result("user not found");
//        }else{
//            usersService.loginUser(user);
//            context.sessionAttribute("user-session", user);
//            context.json(new JsonResponse(true, "login successful", new UsersDTO(user.getUsername(), user.getUser_role_id())));
//
//        }
//
//    }

//    public static void checkSession(Context context){
//        Users user = context.sessionAttribute("user-session");
//        if(user == null){
//            context.json(new JsonResponse(false, "no session found", null));
//        }else{
//            context.json(new JsonResponse(true, "session found", new UsersDTO(user.getUsername(), user.getUser_role_id())));
//        }
//    }

}

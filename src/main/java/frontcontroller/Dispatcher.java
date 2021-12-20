package frontcontroller;
import controllers.ReimbursementsController;
import controllers.UsersController;
import io.javalin.Javalin;
import models.Users;

public class Dispatcher {

    public Dispatcher(Javalin app){

        app.get("/manager-dashboard/reimbursements", ReimbursementsController::getAllReimbursements);
        app.get("/employee-dashboard/reimbursements/{id}", ReimbursementsController::getReimbursements);
        app.delete("/reimbursements/{id}", ReimbursementsController::deleteReimbursement);
        app.post("/reimbursements/{id}", ReimbursementsController::createReimbursement);
        app.patch("/reimbursements/{id}", ReimbursementsController::updateAReimbursement);
        app.get("/users", UsersController::getAllUsers);
        app.post("/users", UsersController::createUser);
        app.get("/users/{id}", UsersController::getUser);
//        app.post("/login", UsersController::loginUser);
//        app.get("/check-session", UsersController::checkSession);
        //app.delete("/users/logout", UsersController::logoutUser);


    }
}

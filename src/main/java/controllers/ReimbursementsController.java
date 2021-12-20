package controllers;

import models.Users;
import services.ReimbursementsService;
import dao.ReimbursementsDaoImpl;
import models.Reimbursements;
import io.javalin.http.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

public class ReimbursementsController {
    static ReimbursementsService reimbursementsService = new ReimbursementsService(new ReimbursementsDaoImpl());

    public static void getAllReimbursements(Context context) throws JsonProcessingException {
        context.contentType("application/json");
        List<Reimbursements> reimbursementsList = reimbursementsService.getAllReimbursements();
        String jsonString = new ObjectMapper().writeValueAsString(reimbursementsList);
        context.result(jsonString);
    }

    public static void getReimbursements(Context context) throws JsonProcessingException {
        context.contentType("application/json");
        Integer reimbId = Integer.parseInt(context.pathParam("id"));
        Reimbursements reimbursement = reimbursementsService.getReimbursements(reimbId);
        context.status(200).result(new ObjectMapper().writeValueAsString(reimbursement));
    }

    public static void createReimbursement(Context context){
        Reimbursements reimbursement = context.bodyAsClass(Reimbursements.class);
        if (ReimbursementsService.createReimbursement(reimbursement)){
            context.status(200).result("Reimbursement was created successfully");
        }else{
            context.status(404).result("Reimbursement was not created");
        }
    }

    public static void deleteReimbursement(Context context){
        Reimbursements reimbursement = context.bodyAsClass(Reimbursements.class);
        Integer reimb_id = Integer.parseInt(context.pathParam("id"));

        reimbursementsService.deleteReimbursement(reimbursement);

        context.status(201).result("Deleted reimbursement with id " + reimb_id + " if it exists");
    }

    public static void updateAReimbursement(Context context) {
            Integer reimbId = Integer.parseInt(context.pathParam("id"));

            reimbursementsService.approveReimbursement(reimbId);

            context.status(201).result("Reimbursement with id " + reimbId + " has been approved");
        }
}


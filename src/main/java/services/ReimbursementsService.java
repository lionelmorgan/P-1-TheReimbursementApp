package services;

import dao.ReimbursementsDaoImpl;
import models.Reimbursements;
import dao.ReimbursementsDao;
import models.Users;

import java.util.List;

public class ReimbursementsService {
    static ReimbursementsDao reimbursementsDao;

    public ReimbursementsService(ReimbursementsDao reimbursementsDao){this.reimbursementsDao = reimbursementsDao;}

    public List<Reimbursements> getAllReimbursements() { return reimbursementsDao.getAllReimbursements();}

    public Reimbursements getReimbursements(Integer reimbId){ return reimbursementsDao.getReimbursements(reimbId);}

    public static boolean createReimbursement(Reimbursements reimbursement){
        if(reimbursement.getReimb_amount() <= 0.00)
            return false;
        else if (reimbursement.getReimb_description().length() > 250)
            return false;
        reimbursementsDao.createReimbursement(reimbursement);
        return true;

    }



    public void deleteReimbursement(Reimbursements reimbursement) { reimbursementsDao.deleteReimbursement(reimbursement);}

    public static void approveReimbursement(Integer reimbId){
        System.out.println("Reimbursement id: " + reimbId + " has been approved");
        reimbursementsDao.updateAReimbursement(reimbId);

    }
}

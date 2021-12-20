package dao;

import models.Reimbursements;
import models.Users;

import java.util.List;

public interface ReimbursementsDao {

    List<Reimbursements> getAllReimbursements();
    Reimbursements getReimbursements(Integer reimbId);
    void createReimbursement(Reimbursements reimbursement);
    void updateAReimbursement(Integer reimbId);
    void deleteReimbursement(Reimbursements reimbursement);
    void approveReimbursement(Reimbursements reimbursement);
    void denyReimbursement(Reimbursements reimbursement);

}

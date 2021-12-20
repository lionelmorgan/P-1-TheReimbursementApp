package dao;

import models.Reimbursements;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ReimbursementsDaoImpl implements ReimbursementsDao {

    String url;
    String username;
    String password;

    Logger logger = Logger.getLogger(ReimbursementsDaoImpl.class);

    public ReimbursementsDaoImpl() {
        this.url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/reimbursementdatabase";
        this.username = System.getenv("RDS_USERNAME");
        this.password = System.getenv("RDS_PASSWORD");
    }

    public ReimbursementsDaoImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Reimbursements> getAllReimbursements() {

        List<Reimbursements> reimbursements = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT * FROM reimbursement;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            logger.info("getAllReimbursements");

            while (rs.next()) {
                reimbursements.add(new Reimbursements(rs.getInt(1), rs.getDouble(2), rs.getTime(3), rs.getTime(4),
                        rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
            }
        } catch (SQLException r) {
            logger.error(r);
        }
        System.out.println(reimbursements);
        return reimbursements;
    }
    

    @Override
    public Reimbursements getReimbursements(Integer reimbId) {

        Reimbursements reimbursement = null;

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM reimbursement WHERE reimb_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reimbId);
            ResultSet rs = ps.executeQuery();

            logger.info("getReimbursements");

            while (rs.next()){
                reimbursement = new Reimbursements(rs.getInt(1), rs.getDouble(2), rs.getTime(3), rs.getTime(4),
                        rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
            }
        }catch (SQLException r){
            logger.error(r);
        }
        return reimbursement;
    }

    @Override
    public void createReimbursement(Reimbursements reimbursement) {

        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "INSERT INTO reimbursement VALUES(DEFAULT, ?, DEFAULT, DEFAULT, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, reimbursement.getReimb_amount());
            ps.setString(2, reimbursement.getReimb_description());
            ps.setInt(3, reimbursement.getReimb_author());
            ps.setInt(4, reimbursement.getReimb_resolver());
//            ps.setInt(5, reimbursement.getReimb_status_id());
//            ps.setInt(6, reimbursement.getReimb_type_id());

            ps.executeUpdate();

            logger.info("createReimbursement");

        } catch(SQLException r){
            logger.error(r);

        }

    }

    @Override
    public void updateAReimbursement(Integer reimbId) {

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "UPDATE reimbursement SET reimb_status_id = 1 WHERE reimb_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //set value of question mark. the parameter index is which question mark you want to assign
            ps.setInt(1, reimbId);

            //execute the update
            ps.executeUpdate();

            logger.info("updateAReimbursement");

        }catch (SQLException r){
            logger.error(r);
        }
    }

    @Override
    public void deleteReimbursement(Reimbursements reimbursement) {

        try( Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "DELETE FROM reimbursement WHERE reimb_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, reimbursement.getReimb_id());
            //ps.setInt(2, reimbursement.getReimb_author());

            ps.executeUpdate();

        }catch (SQLException r) {
            logger.error(r);
        }
    }

    @Override
    public void approveReimbursement(Reimbursements reimbursement) {

    }

    @Override
    public void denyReimbursement(Reimbursements reimbursement) {

    }
}

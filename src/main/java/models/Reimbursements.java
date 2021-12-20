package models;

import java.sql.Time;

public class Reimbursements {

    private Integer reimb_id;
    private Double reimb_amount;
    private Time reimb_submitted;
    private Time reimb_resolved;
    private String reimb_description;
    private Integer reimb_author;
    private Integer reimb_resolver;
    private Integer reimb_status_id;
    private Integer reimb_type_id;

    public Reimbursements(){

    }

    public Reimbursements(Integer reimb_id, Double reimb_amount, Time reimb_submitted, Time reimb_resolved, String reimb_description,
                          Integer reimb_author, Integer reimb_resolver, Integer reimb_status_id, Integer reimb_type_id) {
        this.reimb_id = reimb_id;
        this.reimb_amount = reimb_amount;
        this.reimb_submitted = reimb_submitted;
        this.reimb_resolved = reimb_resolved;
        this.reimb_description = reimb_description;
        this.reimb_author = reimb_author;
        this.reimb_resolver = reimb_resolver;
        this.reimb_status_id = reimb_status_id;
        this.reimb_type_id = reimb_type_id;
    }

    public Reimbursements(int reimb_id, double reimb_amount, String s, String s1, String gas_expense, int reimb_author, int reimb_resolver, int reimb_status_id, int reimb_type_id) {
    }

    public Integer getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(Integer reimb_id) {
        this.reimb_id = reimb_id;
    }

    public Double getReimb_amount() {
        return reimb_amount;
    }

    public void setReimb_amount(Double reimb_amount) {
        this.reimb_amount = reimb_amount;
    }
    public Time getReimb_submitted() {
        return reimb_submitted;
    }

    public void setReimb_submitted(Time reimb_submitted) {
        this.reimb_submitted = reimb_submitted;
    }

    public Time getReimb_resolved() {
        return reimb_resolved;
    }

    public void setReimb_resolved(Time reimb_resolved) {
        this.reimb_resolved = reimb_resolved;
    }

    public String getReimb_description() {
        return reimb_description;
    }

    public void setReimb_description(String reimb_description) {
        this.reimb_description = reimb_description;
    }

    public Integer getReimb_author() {
        return reimb_author;
    }

    public void setReimb_author(Integer reimb_author) {
        this.reimb_author = reimb_author;
    }

    public Integer getReimb_resolver() {
        return reimb_resolver;
    }

    public void setReimb_resolver(Integer reimb_resolver) {
        this.reimb_resolver = reimb_resolver;
    }

    public Integer getReimb_status_id() {
        return reimb_status_id;
    }

    public void setReimb_status_id(Integer reimb_status_id) {
        this.reimb_status_id = reimb_status_id;
    }

    public Integer getReimb_type_id() {
        return reimb_type_id;
    }

    public void setReimb_type_id(Integer reimb_type_id) {
        this.reimb_type_id = reimb_type_id;
    }


    @Override
    public String toString() {
        return "Reimbursements{" +
                "reimb_id=" + reimb_id +
                ", reimb_amount=" + reimb_amount +
                ", reimb_submitted=" + reimb_submitted +
                ", reimb_resolved=" + reimb_resolved +
                ", reimb_description='" + reimb_description + '\'' +
                ", reimb_author=" + reimb_author +
                ", reimb_resolver=" + reimb_resolver +
                ", reimb_status_id=" + reimb_status_id +
                ", reimb_type_id=" + reimb_type_id +
                '}';
    }
}

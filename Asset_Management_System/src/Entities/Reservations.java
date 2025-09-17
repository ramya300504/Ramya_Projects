package Entities;

import java.util.Date;

public class Reservations {

    private int reservation_id;
    private int asset_id;
    private int employee_id;
    private Date reservation_date;
    private Date start_date;
    private Date end_date;
    private String status;

    // default constructor
    Reservations() {
    }

    //parameterized constructor
    Reservations(int reservation_id, int asset_id, int employee_id, Date reservation_date, Date start_date, Date end_date, String status) {
        this.reservation_id = reservation_id;
        this.asset_id = asset_id;
        this.employee_id = employee_id;
        this.reservation_date = reservation_date;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(int asset_id) {
        this.asset_id = asset_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public Date getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(Date reservation_date) {
        this.reservation_date = reservation_date;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}



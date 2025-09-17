package DAO;

import Entities.*;
import Myexceptions.AssetNotFoundException;
import Myexceptions.AssetNotMaintainException;
import Myexceptions.EmployeeNotFoundException;
import Myexceptions.ReservationNotFoundException;
import Util.*;

import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AssetManagementServiceImpl implements IAssetManagementService {
    //static connection variable
    public static Connection connection;

    public AssetManagementServiceImpl() {
        try {
            connection = DBConnection.getConnection();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("Error establishing database connection: " + e.getMessage());
        }
    }

    //To perform 8 menu actions

    //To add New Asset
    @Override
    public boolean addAsset(Assets assets) throws SQLException, ParseException {

        boolean added = false;
        // Insert obtained datas into database
        PreparedStatement pst = connection.prepareStatement("INSERT INTO assets (asset_id, name, type, serial_number, purchase_date, location, status) VALUES (?, ?, ?, ?, ?, ?, ?)");

        pst.setInt(1, assets.getAsset_id());
        pst.setString(2, assets.getAsset_name());
        pst.setString(3, assets.getAsset_type());
        pst.setString(4, assets.getSerial_number());
        pst.setDate(5, assets.getPurchase_date());
        pst.setString(6, assets.getLocation());
        pst.setString(7, assets.getStatus());
        //pst.setInt(8, newOwnerId);
        //Can't able to insert owner_id because it is foreign key

        int rows = pst.executeUpdate();
        if (rows > 0) {
            added = true;
        }

        return added;
    }

    //To update New Status for Asset
    @Override
    public boolean updateAsset(Assets assets) throws SQLException {

        boolean updated = false;
        PreparedStatement pst = connection.prepareStatement("UPDATE assets SET status = ? WHERE asset_id = ?");
        pst.setString(1, assets.getStatus());
        pst.setInt(2, assets.getAsset_id());
        int row = pst.executeUpdate();
        if (row > 0) {
            updated = true;
        }

        return updated;

    }

    //To delete Asset
    @Override
    public boolean deleteAsset(int assetid) throws SQLException {
        boolean deleted = false;
        PreparedStatement pst = connection.prepareStatement("delete from assets where asset_id=?");
        pst.setInt(1, assetid);
        int rows = pst.executeUpdate();
        if (rows > 0) {
            deleted = true;
        }
        return deleted;

    }

    //To allocate asset
    @Override
    public boolean allocateAsset(int assetId, int employeeId, String allocationDate) throws SQLException {

        // Check if the asset ID exists
        try {
            checkAssetID(assetId);
        } catch (AssetNotFoundException e) {
            System.out.println(e.getMessage()); // Prints custom message
        }
        // Check if the employee ID exists
        try {
            checkEmployeeID(employeeId);
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
        //To get new allocationid from database
        int allocation_id = getNewAllocationId("asset_allocations", "allocation_id");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate allocDate = LocalDate.parse(allocationDate, formatter);

        // Calculate return date
        LocalDate returnDate = allocDate.plusDays(10);
        boolean allocated = false;
        PreparedStatement pst = connection.prepareStatement("Insert into asset_allocations (asset_id,allocation_id,employee_id, allocation_date, return_date)values(?,?,?,?,?)");
        pst.setInt(1, assetId);
        pst.setInt(2, allocation_id);
        pst.setInt(3, employeeId);
        pst.setDate(4, Date.valueOf(allocDate));
        pst.setDate(5, Date.valueOf(returnDate));
        int rows = pst.executeUpdate();
        if (rows > 0) {
            allocated = true;
        }

        return allocated;
    }

    //To deallocate asset
    @Override
    public boolean deallocateAsset(int assetId, int employeeId, String returnDate) throws SQLException {
        boolean deallocate = false;
        LocalDate retDate = LocalDate.parse(returnDate);

        PreparedStatement pst = connection.prepareStatement(
                "UPDATE asset_allocations SET return_date = ? WHERE asset_id = ? AND employee_id = ?"
        );
        pst.setDate(1, Date.valueOf(retDate));
        pst.setInt(2, assetId);
        pst.setInt(3, employeeId);

        int rows = pst.executeUpdate();
        if (rows > 0) {
            deallocate = true;
        }
        return deallocate;
    }

    //To perform maintenance
    public boolean performMaintenance(int asset_id, String maintainence_date, String description, double cost) throws SQLException {

        boolean performed = false;
        int maintainence_id = getNewMaintainenceId("maintenance_records", "maintenance_id");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(maintainence_date, formatter);
        PreparedStatement pst = connection.prepareStatement("INSERT INTO maintenance_records (maintenance_id, asset_id, maintenance_date, description, cost) VALUES (?, ?, ?, ?, ?)");
        pst.setInt(1, maintainence_id);
        pst.setInt(2, asset_id);
        pst.setDate(3, Date.valueOf(date));
        pst.setString(4, description);
        pst.setDouble(5, cost);
        int rows = pst.executeUpdate();
        if (rows > 0) {
            performed = true;
        }
        return performed;
    }

    //To reserve Asset
    @Override
    public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate) throws SQLException {
        boolean reserved = false;
        // Generate new reservation ID
        int reservationId = getNewReservationId("reservations", "reservation_id");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate resDate = LocalDate.parse(reservationDate, formatter);
        LocalDate sDate = LocalDate.parse(startDate, formatter);
        LocalDate eDate = LocalDate.parse(endDate, formatter);

        PreparedStatement pst = connection.prepareStatement("INSERT INTO reservations (reservation_id, asset_id, employee_id, reservation_date, start_date, end_date, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)");
        pst.setInt(1, reservationId);
        pst.setInt(2, assetId);
        pst.setInt(3, employeeId);
        pst.setDate(4, Date.valueOf(resDate));
        pst.setDate(5, Date.valueOf(sDate));
        pst.setDate(6, Date.valueOf(eDate));
        pst.setString(7, "Reserved");
        int rows = pst.executeUpdate();
        if (rows > 0) {
            reserved = true;
        }

        return reserved;
    }

    //To withdraw reserved asset
    @Override
    public boolean withdrawReservation(int reservationId) throws SQLException {

        boolean withdrawn = false;
        PreparedStatement pst = connection.prepareStatement("UPDATE reservations SET status = 'Available' WHERE reservation_id = ?");
        pst.setInt(1, reservationId);

        int rows = pst.executeUpdate();
        if (rows > 0) {
            withdrawn = true;
        }
        return withdrawn;
    }
//---------------------------------------------------------------------------------------------------------------------------------------------
    //to generate next datas

    //To generate new Asset if from existing datas
    public int getNextID(String assets, String asset_id) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("SELECT MAX(asset_id) FROM assets");
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        }
        return 1;
    }

    //To generate new Serial Number if from existing datas
    public String generateNextSerialNumber() throws SQLException {
        PreparedStatement pst = connection.prepareStatement("SELECT MAX(serial_number) FROM assets");
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String last = rs.getString(1);
            if (last != null) {
                int number = Integer.parseInt(last.substring(1));
                number++;
                return "S" + String.format("%05d", number);
            }
        }
        return "S00001";
    }

    //To get new MaintainenceId from database
    public int getNewMaintainenceId(String maintenance_records, String maintainence_id) throws SQLException {

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(maintenance_id) FROM maintenance_records");
        if (rs.next()) {
            return rs.getInt(1) + 1;
        }
        return 1;
    }

    //To get new Reseervationid from database
    @Override
    public int getNewReservationId(String reservations, String reservation_id) throws SQLException {

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(reservation_id) FROM reservations");
        if (rs.next()) {
            return rs.getInt(1) + 1;
        }
        return 1;
    }


    //Method To get new allocationid from database
    @Override
    public int getNewAllocationId(String asset_allocations, String allocation_id) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("SELECT MAX(allocation_id) FROM asset_allocations");
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        }
        return 1;

    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    //to check existence

    //To check existence of employeeid to proceed further
    @Override
    public void checkEmployeeID(int empid) throws SQLException, EmployeeNotFoundException {

        PreparedStatement pst = connection.prepareStatement("select 1 from employees where employee_id=?");
        pst.setInt(1, empid);
        ResultSet rs = pst.executeQuery();
        if (rs.next() && rs.getInt(1) == 0) {
            throw new EmployeeNotFoundException("Entered Employee ID is Not Available.Check Again");
        }

    }

    //To check existence of assetid to proceed further
    public void checkAssetID(int assetId) throws SQLException, AssetNotFoundException {


        PreparedStatement pst = connection.prepareStatement("SELECT COUNT(*) FROM assets WHERE asset_id = ?");
        pst.setInt(1, assetId);
        ResultSet rs = pst.executeQuery();
        if (rs.next() && rs.getInt(1) == 0) {
            throw new AssetNotFoundException("Asset with ID " + assetId + " does not exist.Kindly Check Again and Proceed");
        }

    }

    //To check existence of reservationid to proceed further
    @Override
    public void checkReservationID(int reservation_id) throws SQLException {

        PreparedStatement pst = connection.prepareStatement("select count(*) from reservations where reservation_id=?");
        pst.setInt(1, reservation_id);
        ResultSet rs = pst.executeQuery();
        if (rs.next() && rs.getInt(1) == 0) {
            throw new ReservationNotFoundException("Entered Reservation ID does not exist.Kindly Check Again and Proceed");
        }
    }

    //To check maintainence date more than 2 years(Method for Exception)
    @Override
    public void checkmaintainenceDate(int assetid) throws SQLException, AssetNotMaintainException {
        PreparedStatement pst = connection.prepareStatement("select max(maintenance_date) from maintenance_records where asset_id=?");
        pst.setInt(1, assetid);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            Date maxdate = rs.getDate(1);
            long millisec = 2 * 365L * 24 * 60 * 60 * 1000;
            long diff = System.currentTimeMillis() - maxdate.getTime();

            if (diff >= millisec) {
                throw new AssetNotMaintainException("Asset not Maintained in the last 2 years.So Performing Maintainence is Not Possible");
            }
        }

    }

}



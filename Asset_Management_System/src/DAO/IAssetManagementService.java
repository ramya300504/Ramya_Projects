package DAO;

import Entities.Assets;
import Myexceptions.AssetNotFoundException;
import Myexceptions.AssetNotMaintainException;

import java.sql.SQLException;
import java.text.ParseException;


public interface IAssetManagementService {

    //To perform 8 menu actions
    boolean addAsset(Assets assets) throws SQLException, ParseException;

    boolean updateAsset(Assets assets) throws SQLException;

    boolean deleteAsset(int assetId) throws SQLException;

    boolean allocateAsset(int assetId, int employeeId, String allocationDate) throws SQLException;

    boolean deallocateAsset(int assetId, int employeeId, String returnDate) throws SQLException;

    boolean performMaintenance(int asset_id, String maintainence_date, String description, double cost) throws SQLException;

    boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate) throws SQLException;

    boolean withdrawReservation(int reservationId) throws SQLException;

    //to generate next datas

    int getNextID(String assets, String asset_id) throws SQLException;

    String generateNextSerialNumber() throws SQLException;

    int getNewAllocationId(String asset_allocations, String allocation_id) throws SQLException;

    int getNewReservationId(String reservations, String reservation_id) throws SQLException;

    //to check existence
    //These method throws exception

    void checkEmployeeID(int empid) throws SQLException;

    void checkAssetID(int assetId) throws SQLException, AssetNotFoundException;

    void checkReservationID(int reservation_id) throws SQLException;

    void checkmaintainenceDate(int assetid) throws SQLException, AssetNotMaintainException;

}



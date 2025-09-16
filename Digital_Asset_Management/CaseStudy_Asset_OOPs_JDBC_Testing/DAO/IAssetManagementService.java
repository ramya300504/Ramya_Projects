package DAO;
import Entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IAssetManagementService {

    boolean checkEmployeeID(int empid) throws SQLException;

    boolean addAsset(Assets asset);

    boolean  updateAsset(Assets asset);

    boolean deleteAsset(int assetId);

    boolean allocateAsset(int assetId, int employeeId, String allocationDate);

    boolean deallocateAsset(int assetId, int employeeId, String returnDate);

    List<Assets> displayAsset();
}

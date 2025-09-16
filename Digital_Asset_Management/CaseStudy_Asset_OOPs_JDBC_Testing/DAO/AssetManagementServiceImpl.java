package DAO;

import Entity.*;
import Util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class AssetManagementServiceImpl implements IAssetManagementService{
    public static Connection connection;
     public AssetManagementServiceImpl() {
         try {
             connection = DBConnection.getConnection();
             System.out.println("Driver Loaded Successfully");
         } catch (IOException | ClassNotFoundException | SQLException e) {
             System.out.println("Error establishing database connection: " + e.getMessage());
         }
     }
     @Override
             public boolean checkEmployeeID(int empid) throws SQLException {
            boolean exist=false;
         PreparedStatement pst=connection.prepareStatement("select 1 from employees where empid=?");
         pst.setInt(1,empid);
         ResultSet rs = pst.executeQuery();
         if (rs.next()) {
             exist = true;
         }
         return exist;
     }
    List<Assets> l=new ArrayList<>();
    @Override
    public boolean addAsset(Assets asset)
    {

        l.add(asset);
        if(l.contains(asset))
        return true;
        else
        return false;
    }

    @Override
    public boolean updateAsset(Assets asset)
    {
        return true;
    }

    @Override
    public boolean deleteAsset(int assetid)
    {
        for(Assets a:l) {
            if (a.getAsset_id() == assetid) {
                l.remove(a);
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean allocateAsset(int assetId, int employeeId, String allocationDate)
    {
        List<Asset_allocations> list=new ArrayList<>();
        Random ran=new Random();
        int allocationId= ran.nextInt(36)+100;
        LocalDate date = LocalDate.parse(allocationDate);
        LocalDate returnDate = date.plusDays(6);
        String return_Date= returnDate.toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date allocation_Date = null;
        Date returndate=null;
        try {
            allocation_Date = sdf.parse(allocationDate);
            returndate=sdf.parse(return_Date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Asset_allocations al= new Asset_allocations(allocationId,assetId,employeeId,allocation_Date,returndate);
        list.add(al);
        if(!list.isEmpty())
            return true;
        else
            return false;
    }

    @Override
    public boolean deallocateAsset(int assetId, int employeeId, String returnDate)
    {
             return true;
    }
    @Override
    public List<Assets> displayAsset()
    {
        return l;
    }


}

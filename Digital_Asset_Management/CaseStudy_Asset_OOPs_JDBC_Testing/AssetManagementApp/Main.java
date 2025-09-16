package AssetManagementApp;

import DAO.AssetManagementServiceImpl;
import DAO.IAssetManagementService;

import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to Digital Asset Management Application");
        System.out.println("Enter your Employee ID to Check Whether you are Employee of Our System");
        int empid=sc.nextInt();
        IAssetManagementService service=new AssetManagementServiceImpl();
        if(service.checkEmployeeID(empid));
        {
            System.out.println("Great!!, We confirmed that you are an Employee, GO Further");
            System.out.println("The Available Operations are Listed.");
            System.out.println("To ADD Asset Enter '1'\nTo UPDATE Asset Enter '2'\nTo DELETE Asset Enter '3'\n" +
                    "To ALLOCATE Asset Enter '4'\nTo DEALLOCATE Asset Enter '5'\nTo PERFORM MAINTAINENCE Enter '6'\nTo RESERVE Asset Enter '7'");

            int option = sc.nextInt();
//            switch (option) {
//                case 1:
//                    addAsset();
//                    break;
//
//            }
        }
    }
}

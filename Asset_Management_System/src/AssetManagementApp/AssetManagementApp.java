
package AssetManagementApp;

import DAO.AssetManagementServiceImpl;
import DAO.IAssetManagementService;
import Entities.Assets;
import Myexceptions.AssetNotFoundException;
import Myexceptions.EmployeeNotFoundException;
import Myexceptions.ReservationNotFoundException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AssetManagementApp {

    public static void main(String[] args) throws SQLException, ParseException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Digital Asset Management Application");
        System.out.println("Enter your Employee ID to Check Whether you are Employee of Our System");
        int empid = sc.nextInt();
        IAssetManagementService service = new AssetManagementServiceImpl();
        //handles exception for not found employee id
        try {
            service.checkEmployeeID(empid);

            System.out.println("Great!!, We confirmed that you are an Employee, GO Further");
            System.out.println("The Available Operations are Listed.");
            System.out.println("To ADD Asset Enter           : 1\n" +
                    "To UPDATE Asset Enter        : 2\n" +
                    "To DELETE Asset Enter        : 3\n" +
                    "To ALLOCATE Asset Enter      : 4\n" +
                    "To DEALLOCATE Asset Enter    : 5\n" +
                    "To PERFORM MAINTAINENCE Enter: 6\n" +
                    "To RESERVE Asset Enter       : 7\n" +
                    "To WithDraw Reservation Enter: 8");

            int option = sc.nextInt();
            if (option == 1) {

                // Get next asset ID
                int newAssetId = service.getNextID("assets", "asset_id");

                // Read input from user
                System.out.print("Enter Asset Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Asset Type: ");
                String type = sc.nextLine();

                // Generate new serial number
                String newSerial = service.generateNextSerialNumber();

                System.out.print("Enter Purchase Date (YYYY-MM-DD): ");
                String purchaseDate = sc.nextLine();

                //TO convert String to Date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date utilDate = sdf.parse(purchaseDate);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                System.out.print("Enter Location: ");
                String location = sc.nextLine();

                System.out.print("Enter Status: ");
                String status = sc.nextLine();
                Assets assets = new Assets(newAssetId, name, type, newSerial, sqlDate, location, status, 0);
                boolean check = service.addAsset(assets);
                if (check) {
                    System.out.println("Asset added successfully with ID: " + assets.getAsset_id());
                } else {
                    System.out.println("Asset Cannot be Added Due to Server Issues");
                }

            } else if (option == 2) {
                System.out.println("Enter Asset ID:");
                int aid = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter New Status to be Updated:");
                String newstatus = sc.nextLine();
                Assets assets = new Assets(aid, null, null, null, null, null, newstatus, 0);
                boolean check = service.updateAsset(assets);
                if (check) {
                    System.out.println("Asset Status updated successfully.");
                } else {
                    System.out.println("Asset not found.Enter Correct ID");
                }

            } else if (option == 3) {
                System.out.println("Enter Asset ID to Delete the Asset");
                int aid = sc.nextInt();
                //Handles AssetNotFoundException
                try {
                    service.checkAssetID(aid);
                } catch (AssetNotFoundException e) {
                    System.out.println(e.getMessage()); // Prints custom message
                }

                boolean check = service.deleteAsset(aid);
                if (check) {
                    System.out.println("Asset Deleted Successfully");
                } else {
                    System.out.println("Asset Not Found.Please Check the Asset ID");
                }

            } else if (option == 4) {
                System.out.println("Enter Asset ID");
                int assetid = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Employee ID");
                int employeeid = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Allocation Date (yyyy-MM-dd)");
                String allocationDate = sc.nextLine();

                //generating allocation id from Database
                boolean allocated = service.allocateAsset(assetid, employeeid, allocationDate);
                if (allocated) {
                    System.out.println("Asset Allocated Successfully,New Allocation Id is " + service.getNewAllocationId("asset_allocations", "allocation_id"));
                }

            } else if (option == 5) {
                System.out.print("Enter Asset ID: ");
                int assetId = sc.nextInt();
                System.out.print("Enter Employee ID: ");
                int empId = sc.nextInt();
                System.out.print("Enter Return Date (yyyy-MM-dd): ");
                String retDate = sc.next();
                boolean deallocated = service.deallocateAsset(assetId, empId, retDate);
                if (deallocated) {
                    System.out.println("Asset Deallocated Successfully");
                } else {
                    System.out.println("Deallocation Failed Due to Invalid Entry");
                }

            } else if (option == 6) {
                System.out.print("Enter Asset ID: ");
                int assetId = sc.nextInt();
                sc.nextLine();
                // Check if the asset ID exists
                try {
                    service.checkAssetID(assetId);
                } catch (AssetNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                //handles exception for maintainence date
                try {
                    service.checkmaintainenceDate(assetId);
                } catch (AssetNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                System.out.print("Enter Maintenance Date (yyyy-MM-dd): ");
                String maintenanceDate = sc.nextLine();
                System.out.print("Enter Description: ");
                String description = sc.nextLine();
                System.out.print("Enter Cost: ");
                double cost = sc.nextDouble();
                boolean maintained = service.performMaintenance(assetId, maintenanceDate, description, cost);
                if (maintained) {
                    System.out.println("Maintenance recorded successfully.");
                } else {
                    System.out.println("Failed to record maintenance.");
                }
            } else if (option == 7) {
                System.out.println("Enter Employee ID");
                int eid = sc.nextInt();
                try {
                    service.checkEmployeeID(eid);
                } catch (EmployeeNotFoundException e) {
                    System.out.println(e.getMessage());
                }

                System.out.print("Enter Asset ID: ");
                int assetId = sc.nextInt();
                System.out.print("Enter Reservation Date (yyyy-MM-dd): ");
                String reserDate = sc.next();
                System.out.print("Enter Start Date (yyyy-MM-dd): ");
                String startDate = sc.next();
                System.out.print("Enter End Date (yyyy-MM-dd): ");
                String endDate = sc.next();
                boolean reserved = service.reserveAsset(assetId, eid, reserDate, startDate, endDate);
                if (reserved) {
                    System.out.println("Asset Reserved Successfully");
                } else {
                    System.out.println("Problem in Asset Reservation.Try Later");
                }


            } else if (option == 8) {
                System.out.println("To Withdraw Reserved Asset Enter correct Reservation ID:");
                int rid = sc.nextInt();
                try {
                    service.checkReservationID(rid);
                    boolean withdrawn = service.withdrawReservation(rid);
                    if (withdrawn) {
                        System.out.println("Asset Withdrawn Successfully");
                    } else {
                        System.out.println("Server Issue,Try Later");
                    }
                } catch (ReservationNotFoundException e) {
                    System.out.println(e.getMessage());
                }

            } else {
                System.out.println("Invalid Option:(\nChoose between 1 - 7");
            }
        }
        //if entered empid doesn't exist throws exception
        catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}




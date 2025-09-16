package Entity;

import DAO.AssetManagementServiceImpl;
import DAO.IAssetManagementService;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        IAssetManagementService service=new AssetManagementServiceImpl();
        //Addition of Asset
        Calendar calendar1=Calendar.getInstance();
        calendar1.set(2025,Calendar.MARCH,20);
        Date purchasedate=calendar1.getTime();
        Assets asset1=new Assets(3, "HP LaserJet Pro", "Printer", "S01003", purchasedate, "Madurai Office", "Under Maintenance", 103);
        Assets asset2=new Assets(5, "Apple MacBook Air", "Laptop", "S01005", purchasedate, "Trichy Office", "In Use",105);
        Assets asset3=new Assets(7, "Samsung 55-inch TV", "Display", "S01007", purchasedate, "Chennai Conference Room", "In Use", 107);
        boolean Added1 = service.addAsset(asset1);
        boolean Added2 = service.addAsset(asset2);
        boolean Added3 = service.addAsset(asset3);
        if(Added1)
            System.out.println("Asset added Successfully:");

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        //Updation of Asset

        //Deletion of Asset
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Asset ID that need to be Deleted");
        int assetid=sc.nextInt();
        boolean delete= service.deleteAsset(assetid);
        List<Assets> afterdeletelist=new ArrayList<>();
        if(delete)
        {
            afterdeletelist=service.displayAsset();
            for(Assets i:afterdeletelist)
            {
                System.out.println(i);
            }
        }
        //Allocation of Asset to Employee
        System.out.println("Enter Asset ID:");
        int asset_id=sc.nextInt();
        System.out.println("Enter Employee ID");
        int empid=sc.nextInt();
        System.out.println("Enter Allocation Date(YYYY-MM-DD");
        String adate=sc.next();
        System.out.println(service.allocateAsset(asset_id,empid,adate));

    }
}

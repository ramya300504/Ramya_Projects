package AssetManagementApp;

import Entities.Assets;
import DAO.AssetManagementServiceImpl;
import Myexceptions.AssetNotFoundException;
import Myexceptions.EmployeeNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;

public class TestCaseDevelopment {

    static AssetManagementServiceImpl ams;

    //create object before all tests
    @BeforeAll
    static void setup() {
        ams = new AssetManagementServiceImpl();
    }

    //empty object after all tests
    @AfterAll
    static void destroy() {
        ams = null;
    }

    // test case to test asset created successfully or not.
    @Test
    void TestAssetAdded() throws SQLException, ParseException {

        Assets assets = new Assets(29, "Mac Book", "Electronics", "S02985", null, "Velur", "Ordered", 195);

        boolean test = ams.addAsset(assets);
        Assertions.assertEquals(true, test);
        System.out.println("Test 1 Passed Successfully");
    }

    // test case to test asset is added to maintenance successfully or not.
    @Test
    void TestAssetMaintained() throws SQLException {
        boolean test = ams.performMaintenance(3, "2025-02-14", "Unexpected Power Off", 2460);
        Assertions.assertEquals(true, test);
        System.out.println("Test 2 Passed Successfully");

    }

    // test case to test asset is reserved successfully or not.
    @Test
    void TestAssetReserved() throws SQLException {
        boolean test = ams.reserveAsset(5, 108, "2025-01-17", "2025-01-29", "2025-02-15");
        Assertions.assertEquals(true, test);
        System.out.println("Test 3 Passed Successfully");
    }

    //test case to test exception is thrown correctly or not asset id not found in database.
    @Test
    void testAssetNotFoundException() {
        int AssetId = 94;

        Assertions.assertThrows(AssetNotFoundException.class, () -> {
            ams.checkAssetID(AssetId);
        });
        System.out.println("Test 4 Passed Successfully");
    }

    //test case to test exception is thrown correctly or not asset id not found in database.
    @Test
    void testEmployeeNotFoundException() {
        int invalidEmpId = 88; // assuming this ID does not exist in DB

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            ams.checkEmployeeID(invalidEmpId);
        });
        System.out.println("Test 5 Passed Successfully");
    }
}





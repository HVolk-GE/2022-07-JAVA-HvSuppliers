package de.hvolk.hvsupplier.logic.db;

import de.hvolk.hvsupplier.model.Delivery;
import de.hvolk.hvsupplier.settings.AppTexts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.List;

public class DbManagerDelivery {

    //region 0. Constants
    //endregion

    //region 1. Decl. and Init Attribute
    private static DbManagerDelivery instance;
    private DaoDeliveries daoDeliveries;
    //endregion

    //region 2. constructors

    /**
     * Standard constructor
     */
    private DbManagerDelivery() {
        this.daoDeliveries = new DaoDeliveries();
    }

    //endregion

    //region 3. Get Instance

    /**
     * returns only one Instanz
     */
    public static synchronized DbManagerDelivery getInstance() {
        if (instance == null) {
            instance = new DbManagerDelivery();
        }
        return instance;
    }
    //endregion

    //region 4. Database Connection

    /**
     * <h1>Returns read-/write Database connection</h1>
     * <p>Array reading {@link DbConnectionSetting} from JSON configuration-file,
     * for flexible configuration outside of source code!</p>
     * <br>
     * <h3>Description of JDBC-Connection-String complete:</h3>
     * <ul><li>jdbc:mariadb://localhost:3306/project", "root", "";</li></ul>
     * <br>
     * <h3>Array contains:</h3>
     * <ul>
     * <li>dbConnectArray[0] -> JDBC URL Starts with:  "jdbc:mariadb://"</li>
     * <li>dbConnectArray[1] -> Host Name / IP:  "localhost"</li>
     * <li>dbConnectArray[2] -> TCP Port:  "3306"</li>
     * <li>dbConnectArray[3] -> Database name: "suppliers"</li>
     * <li>dbConnectArray[4] -> Database driver name: "org.mariadb.jdbc.Driver"</li>
     * <li>dbConnectArray[5] -> UserName</li>
     * <li>dbConnectArray[6] -> UserPassword</li>
     * </ul>
     */
    private Connection getRwDbConnection() throws Exception {
        Connection rwDbConnection = null;

        String[] dbConnectArray = DbConnectionSetting.DbConnection();
        String dbConnectDriver = dbConnectArray[4];

        String dbConnectUrl = dbConnectArray[0] + dbConnectArray[1] + ":" + dbConnectArray[2] + "/" + dbConnectArray[3];

        String dbConnectUserName = dbConnectArray[5];
        String dbConnectPassWord = dbConnectArray[6];

        try {
            //register the JDBC driver
            Class.forName(dbConnectDriver);

            //2. open connection
            rwDbConnection = DriverManager.getConnection(dbConnectUrl, dbConnectUserName, dbConnectPassWord);

        } catch (SQLNonTransientConnectionException sqlNoConnectionEx) {
            throw new Exception(AppTexts.NO_DATABASE_CONNECTION);
        } catch (ClassNotFoundException classNotFoundEx) {
            throw new Exception(AppTexts.JDBC_DRIVER_LOADING_NOT_POSSIBLE);
        }

        return rwDbConnection;
    }

    /**
     * check Database is online!
     */
    public boolean isDatabaseOnline() {
        boolean isOnline = true;
        try {
            this.getRwDbConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            isOnline = false;
        }
        return isOnline;
    }
    //endregion


    //region 5. CRUD - operation User
    /**
     * Insert one supplier in the Database
     */
    public void insertDeliveryIntoDbTbl(Delivery deliveryToInsert) {

        try {
            if (this.isDatabaseOnline()) {
                //Create new connection
                this.daoDeliveries.insertDataRecordIntoDbTbl(this.getRwDbConnection(), deliveryToInsert);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Insert multiple supplier to the Database
     */

    public void insertSuppliersIntoDbTbl(List<Delivery> suppliersToInsert) {

        try {
            if (this.isDatabaseOnline()) {
                this.daoDeliveries.insertDataRecordsIntoDbTbl(this.getRwDbConnection(), suppliersToInsert);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Read all data from the table
     */
    public List<Delivery> getAllDelieveriesFromDb() {
        //Create new connection
        List<Delivery> allDeliveriesFromDb = new ArrayList<>();

        try {
            if (this.isDatabaseOnline()) {
                allDeliveriesFromDb = this.daoDeliveries.getAllDataRecordsFromDbTbl(this.getRwDbConnection());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return allDeliveriesFromDb;
    }

    public List<Delivery> getAllDelieveriesForSuppliersFromDb(int iId) {
        //Create new connection
        List<Delivery> allDeliveriesForSuppliersFromDb = new ArrayList<>();

        try {
            if (this.isDatabaseOnline()) {
                allDeliveriesForSuppliersFromDb = this.daoDeliveries.getAllDataRecordsForSpecificalRelIdFromDbTbl(this.getRwDbConnection(), "deliveries", iId);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return allDeliveriesForSuppliersFromDb;
    }

    /**
     * Read one spec. Delivery from Database
     */
    public Delivery getDeliveryById(int iId) {
        Delivery specificDeliveriesFromDbById = new Delivery();

        try {
            if (this.isDatabaseOnline()) {
                //Create new connection
                specificDeliveriesFromDbById =
                        this.daoDeliveries.getSpecificDataRecordFromDbTblById(this.getRwDbConnection(), iId);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return specificDeliveriesFromDbById;
    }

    /**
     * Read all Deliveries by spec. supplierID from Database
     */
    public Delivery getDeliverySupplierById(int iId) {
        Delivery specificDeliverySupplierFromDbById = new Delivery();

        try {
            if (this.isDatabaseOnline()) {
                //Create new connection
                specificDeliverySupplierFromDbById =
                        (Delivery) this.daoDeliveries.getSpecificSupplierDataRecordFromDbTblById(this.getRwDbConnection(), iId);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return specificDeliverySupplierFromDbById;
    }

    /**
     * Create new Database Connection for update.
     */
    public void updateDeliveryInDbTbl(Delivery deliveryToUpdate) {
        try {
            if (this.isDatabaseOnline()) {
                //Create new connection
                this.daoDeliveries.updateDataRecordIntoDbTbl(this.getRwDbConnection(), deliveryToUpdate);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Update one Deliveries data in the Database
     */
    public void updateDeliveriesInDbTbl(List<Delivery> deliveriesToUpdate) {
        try {
            if (this.isDatabaseOnline()) {
                //Create new connection
                this.daoDeliveries.updateDataRecordsIntoDbTbl(this.getRwDbConnection(), deliveriesToUpdate);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Delete one spec. Delivery by id from Database
     */
    public void deleteDeliveryInDbTblById(int iId) {
        //Create new connection
        try {
            if (this.isDatabaseOnline()) {
                this.daoDeliveries.deleteDataRecordInDbTblById(this.getRwDbConnection(), iId);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //endregion
}
/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/10/2022 10:30AM).
 */


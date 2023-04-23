package de.hvolk.hvsupplier.logic.db;

import de.hvolk.hvsupplier.model.Supplier;
import de.hvolk.hvsupplier.settings.AppTexts;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.List;

/**
 * Threat safety Database access
 */

public class DbManager {

    //region 0. Constants
    //endregion

    //region 1. Decl. and Init Attribute
    private static DbManager instance;
    private DaoSuppliers daoSuppliers;
    //endregion

    //region 2. constructors
    /**
     * Standard constructor
     */
    private DbManager() {this.daoSuppliers = new DaoSuppliers();}

    //endregion

    //region 3. Get Instance
    /**
     * returns only one Instanz
     */
    public static synchronized DbManager getInstance(){
        if(instance == null){
            instance = new DbManager();
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
            // Warning Database not started !
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(AppTexts.SQL_NOT_CONNECTED);
            alert.setHeaderText(AppTexts.SQL_NOT_CONNECTED);
            alert.setContentText(AppTexts.FENSTER_SCHLIESSEN_UND_PRUEFEN_SIE_OB_DIE_DATENBANK_GESTARTET_WURDE);
            alert.getOnCloseRequest();
            alert.showAndWait();
            Platform.exit();
            throw new Exception(AppTexts.NO_DATABASE_CONNECTION);

        } catch (ClassNotFoundException classNotFoundEx) {
            //Waring messages Db Driver incorrect or wrong !
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(AppTexts.DATENBANKTREIBER_NICHT_GEFUNDEN_ODER_NICHT_KORREKT);
            alert.setHeaderText(AppTexts.DATENBANKTREIBER_NICHT_GEFUNDEN_ODER_NICHT_KORREKT);
            alert.setContentText(AppTexts.PRUEFEN_SIE_OB_DER_RICHTIGE_DATENBANKTREIBER_AUS_GEWAEHLT_WURDE);
            alert.getOnCloseRequest();
            alert.showAndWait();
            Platform.exit();
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
    public void insertSupplierIntoDbTbl(Supplier supplierToInsert) {

        try {
            if (this.isDatabaseOnline()) {
                //Create new connection
                this.daoSuppliers.insertDataRecordIntoDbTbl(this.getRwDbConnection(), supplierToInsert);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Insert multiple supplier to the Database
     */

    public void insertSuppliersIntoDbTbl(List<Supplier> suppliersToInsert) {

        try {
            if (this.isDatabaseOnline()) {
                this.daoSuppliers.insertDataRecordsIntoDbTbl(this.getRwDbConnection(), suppliersToInsert);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Read all data from the table
     */
    public List<Supplier> getAllSuppliersFromDb() {
        //Create new connection
        List<Supplier> allSuppliersFromDb = new ArrayList<>();

        try {
            if (this.isDatabaseOnline()) {
                allSuppliersFromDb = this.daoSuppliers.getAllDataRecordsFromDbTbl(this.getRwDbConnection());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return allSuppliersFromDb;
    }
    /**
     * Read one spec. Supplier from Database
     */
    public Supplier getSupplierById(int iId) {
        Supplier specificSupplierFromDbById = new Supplier();

        try {
            if (this.isDatabaseOnline()) {
                //Create new connection
                specificSupplierFromDbById =
                        this.daoSuppliers.getSpecificDataRecordFromDbTblById(this.getRwDbConnection(), iId);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return specificSupplierFromDbById;
    }

    /**
     * Create new Database Connection for update.
     */
    public void updateSupplierInDbTbl(Supplier supplierToUpdate) {
        try {
            if (this.isDatabaseOnline()) {
                //Create new connection
                this.daoSuppliers.updateDataRecordIntoDbTbl(this.getRwDbConnection(), supplierToUpdate);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Update one Supplier data in the Database
     */
    public void updateSuppliersInDbTbl(List<Supplier> deliveriesToUpdate) {
        try {
            if (this.isDatabaseOnline()) {
                //Create new connection
                this.daoSuppliers.updateDataRecordsIntoDbTbl(this.getRwDbConnection(), deliveriesToUpdate);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    /**
     * Delete one spec. Supplier by id from Database
     */
    public void deleteSupplierInDbTblById(int iId) {
        //Create new connection
        try {
            if (this.isDatabaseOnline()) {
                this.daoSuppliers.deleteDataRecordInDbTblById(this.getRwDbConnection(), iId);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //endregion
}
/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/10/2022 10:38AM).
 */

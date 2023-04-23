package de.hvolk.hvsupplier.logic.db;

import de.hvolk.hvsupplier.model.Delivery;
import de.hvolk.hvsupplier.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Specific Dao Class for suppliers (table) in the suppliers Database !
 */
public class DaoSuppliers extends ADao {

    //region 0. constants
    protected static final String COL_NAME_SUPPLIER_NAME = "supplierName";
    protected static final String COL_NAME_SUPPLIER_NAME_INC_COL_BACK_TICKS =
            CHAR_COL_BACK_TICK + COL_NAME_SUPPLIER_NAME + CHAR_COL_BACK_TICK;

    protected static final String COL_NAME_SUPPLIER_CITY = "supplierCity";
    protected static final String COL_NAME_SUPPLIER_CITY_INC_COL_BACK_TICKS =
            CHAR_COL_BACK_TICK + COL_NAME_SUPPLIER_CITY + CHAR_COL_BACK_TICK;

    protected static final String COL_NAME_SUPPLIER_STREET = "supplierStreet";
    protected static final String COL_NAME_SUPPLIER_STREET_INC_COL_BACK_TICKS =
            CHAR_COL_BACK_TICK + COL_NAME_SUPPLIER_STREET + CHAR_COL_BACK_TICK;

    protected static final String COL_NAME_SUPPLIER_POST_CODE = "supplierPostalCode";
    protected static final String COL_NAME_SUPPLIER_POST_CODE_INC_COL_BACK_TICKS =
            CHAR_COL_BACK_TICK + COL_NAME_SUPPLIER_POST_CODE + CHAR_COL_BACK_TICK;

    protected static final String COL_NAME_SUPPLIER_PREFERT_DELIVERY_MODE = "prefertDeliveryMode";
    protected static final String COL_NAME_SUPPLIER_PREFERT_DELIVERY_MODE_INC_COL_BACK_TICKS =
            CHAR_COL_BACK_TICK + COL_NAME_SUPPLIER_PREFERT_DELIVERY_MODE + CHAR_COL_BACK_TICK;

    protected static final String COL_NAME_SUPPLIER_DELIVERY_MATERIAL = "deliveryMaterial";
    protected static final String COL_NAME_SUPPLIER_DELIVERY_MATERIAL_INC_COL_BACK_TICKS =
            CHAR_COL_BACK_TICK + COL_NAME_SUPPLIER_DELIVERY_MATERIAL + CHAR_COL_BACK_TICK;


    protected static final String COL_NAME_SUPPLIER_AVERAGE_DELIVERY_DAYS = "averageaDeliveryDays";
    protected static final String COL_NAME_SUPPLIER_AVERAGE_DELIVERY_DAYS_INC_COL_BACK_TICKS =
            CHAR_COL_BACK_TICK + COL_NAME_SUPPLIER_AVERAGE_DELIVERY_DAYS + CHAR_COL_BACK_TICK;

    private static final String TABLE_NAME_TRIPS = "suppliers";
    //endregion

    //region 2. constructor
    public DaoSuppliers() {
        super(TABLE_NAME_TRIPS);
    }
    //endregion

    //region 3. insert

    /**
     * Insert once dataset into the database
     */

    @Override
    public void insertDataRecordIntoDbTbl(Connection dbRwConnection, Object objectToInsertIntoDbTable) {

        //Decl and Init
        Statement dbStatementToExecute = null;

        //Check which instance collect which class
        if (objectToInsertIntoDbTable instanceof Supplier) {

            //EXPLICIT CAST
            Supplier supplierToInsertIntoDbTable = (Supplier) objectToInsertIntoDbTable;

            try {
                //1. Database Connection are opened by DbManager

                //2. Statement object generate
                dbStatementToExecute = dbRwConnection.createStatement();

                String sqlTextInsertStatement = INSERT_TBL + this.tableName + CHAR_OPEN_PARENTHESIS
                        + COL_NAME_SUPPLIER_NAME_INC_COL_BACK_TICKS + CHAR_COMMA
                        + COL_NAME_SUPPLIER_STREET_INC_COL_BACK_TICKS + CHAR_COMMA
                        + COL_NAME_SUPPLIER_CITY_INC_COL_BACK_TICKS + CHAR_COMMA
                        + COL_NAME_SUPPLIER_POST_CODE_INC_COL_BACK_TICKS + CHAR_COMMA
                        + COL_NAME_SUPPLIER_PREFERT_DELIVERY_MODE_INC_COL_BACK_TICKS + CHAR_COMMA
                        + COL_NAME_SUPPLIER_DELIVERY_MATERIAL_INC_COL_BACK_TICKS + CHAR_COMMA
                        + COL_NAME_SUPPLIER_AVERAGE_DELIVERY_DAYS_INC_COL_BACK_TICKS
                        + CHAR_CLOSE_PARENTHESIS
                        + VALUES_OPERATOR + CHAR_OPEN_PARENTHESIS
                        + CHAR_VALUE_BACK_TICK + supplierToInsertIntoDbTable.getSuppilerName() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + CHAR_VALUE_BACK_TICK + supplierToInsertIntoDbTable.getSupplierStreet() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + CHAR_VALUE_BACK_TICK + supplierToInsertIntoDbTable.getSupplierCity() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + CHAR_VALUE_BACK_TICK + supplierToInsertIntoDbTable.getSupplierPostCode() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + CHAR_VALUE_BACK_TICK + supplierToInsertIntoDbTable.getPrefertDeliveryMode() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + CHAR_VALUE_BACK_TICK + supplierToInsertIntoDbTable.getDeliveryMaterial() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + CHAR_VALUE_BACK_TICK + supplierToInsertIntoDbTable.getAverageDeliveryDays() + CHAR_VALUE_BACK_TICK
                        + CHAR_CLOSE_PARENTHESIS_SEMICOLON;


                //DEBUG
                //System.out.println(">>>>>>>> " + sqlTextInsertStatement);

                //3. SQL - String to statement object
                dbStatementToExecute.execute(sqlTextInsertStatement);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (dbStatementToExecute != null) {
                    //4. Close statement
                    try {
                        dbStatementToExecute.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }

                if (dbRwConnection != null) {
                    //5. Close connection
                    try {
                        dbRwConnection.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }
            }
        }

    }

    /**
     * Insert multi datasets into the database
     */
    @Override
    public void insertDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends Object> objectsToInsertIntoDbTable) {
        //Decl and Init
        Statement dbStatementToExecute = null;
        final int indexFirstElement = 0;

        //Check which instance collect which class
        if (objectsToInsertIntoDbTable.get(indexFirstElement) instanceof Supplier) {


            try {
                //1. Database Connection are opened by DbManager

                for (Object objectToInsert : objectsToInsertIntoDbTable) {

                    //EXPLICIT CAST
                    Supplier supplierToInsertIntoDbTable = (Supplier) objectToInsert;

                    //2. Statement object generate
                    dbStatementToExecute = dbRwConnection.createStatement();

                    String sqlTextInsertStatement = INSERT_TBL + this.tableName + CHAR_OPEN_PARENTHESIS
                            + COL_NAME_SUPPLIER_NAME_INC_COL_BACK_TICKS + CHAR_COMMA
                            + COL_NAME_SUPPLIER_STREET_INC_COL_BACK_TICKS + CHAR_COMMA
                            + COL_NAME_SUPPLIER_CITY_INC_COL_BACK_TICKS + CHAR_COMMA
                            + COL_NAME_SUPPLIER_POST_CODE_INC_COL_BACK_TICKS + CHAR_COMMA
                            + COL_NAME_SUPPLIER_PREFERT_DELIVERY_MODE_INC_COL_BACK_TICKS + CHAR_COMMA
                            + COL_NAME_SUPPLIER_DELIVERY_MATERIAL_INC_COL_BACK_TICKS + CHAR_COMMA
                            + COL_NAME_SUPPLIER_AVERAGE_DELIVERY_DAYS_INC_COL_BACK_TICKS
                            + CHAR_CLOSE_PARENTHESIS
                            + VALUES_OPERATOR + CHAR_OPEN_PARENTHESIS
                            + CHAR_VALUE_BACK_TICK + supplierToInsertIntoDbTable.getSuppilerName() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + CHAR_VALUE_BACK_TICK + supplierToInsertIntoDbTable.getSupplierStreet() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + CHAR_VALUE_BACK_TICK + supplierToInsertIntoDbTable.getSupplierCity() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + CHAR_VALUE_BACK_TICK + supplierToInsertIntoDbTable.getSupplierPostCode() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + CHAR_VALUE_BACK_TICK + supplierToInsertIntoDbTable.getPrefertDeliveryMode() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + CHAR_VALUE_BACK_TICK + supplierToInsertIntoDbTable.getDeliveryMaterial() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + CHAR_VALUE_BACK_TICK + supplierToInsertIntoDbTable.getAverageDeliveryDays() + CHAR_VALUE_BACK_TICK
                            + CHAR_CLOSE_PARENTHESIS_SEMICOLON;

                    //DEBUG
                    // System.out.println(">>>>>>>> " + sqlTextInsertStatement);

                    //3. SQL - String to statement object
                    dbStatementToExecute.execute(sqlTextInsertStatement);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (dbStatementToExecute != null) {
                    //4. Close statement
                    try {
                        dbStatementToExecute.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }

                if (dbRwConnection != null) {
                    //5. Close connection
                    try {
                        dbRwConnection.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }
            }
        }
    }
    //endregion

    //region Update

    /**
     * Change single Datenset in the datatable.
     */
    @Override
    public void updateDataRecordIntoDbTbl(Connection dbRwConnection, Object objectToUpdateInDbTable) {

        Statement dbStatementToExecute = null;

        //Check which instance collect which class
        if (objectToUpdateInDbTable instanceof Supplier) {

            //EXPLICIT CAST
            Supplier supplierToUpdateIntoDbTable = (Supplier) objectToUpdateInDbTable;

            try {

                //1. Database Connection are opened by DbManager

                //2. Statement object generate
                dbStatementToExecute = dbRwConnection.createStatement();

                String sqlTextUpdateStatement = UPDATE_TBL + this.tableName
                        + SET_OPERATOR
                        + COL_NAME_SUPPLIER_NAME_INC_COL_BACK_TICKS
                        + EQUALS_OPERATOR
                        + CHAR_VALUE_BACK_TICK + supplierToUpdateIntoDbTable.getSuppilerName() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + COL_NAME_SUPPLIER_CITY_INC_COL_BACK_TICKS
                        + EQUALS_OPERATOR + CHAR_VALUE_BACK_TICK + supplierToUpdateIntoDbTable.getSupplierCity() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + COL_NAME_SUPPLIER_STREET_INC_COL_BACK_TICKS
                        + EQUALS_OPERATOR + CHAR_VALUE_BACK_TICK + supplierToUpdateIntoDbTable.getSupplierStreet() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + COL_NAME_SUPPLIER_POST_CODE_INC_COL_BACK_TICKS
                        + EQUALS_OPERATOR + CHAR_VALUE_BACK_TICK + supplierToUpdateIntoDbTable.getSupplierPostCode() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + COL_NAME_SUPPLIER_PREFERT_DELIVERY_MODE_INC_COL_BACK_TICKS
                        + EQUALS_OPERATOR
                        + CHAR_VALUE_BACK_TICK + supplierToUpdateIntoDbTable.getPrefertDeliveryMode() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + COL_NAME_SUPPLIER_DELIVERY_MATERIAL_INC_COL_BACK_TICKS
                        + EQUALS_OPERATOR + CHAR_VALUE_BACK_TICK + supplierToUpdateIntoDbTable.getDeliveryMaterial() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + COL_NAME_SUPPLIER_AVERAGE_DELIVERY_DAYS_INC_COL_BACK_TICKS
                        + EQUALS_OPERATOR + CHAR_VALUE_BACK_TICK + supplierToUpdateIntoDbTable.getAverageDeliveryDays() + CHAR_VALUE_BACK_TICK
                        + WHERE_CONDITION + COL_NAME_ID_INC_COL_BACK_TICKS + EQUALS_OPERATOR + supplierToUpdateIntoDbTable.getId() + CHAR_SEMICOLON;

                //DEBUG
                //System.out.println(">>>>>>>> " + sqlTextUpdateStatement);

                //3. SQL - String to statement object
                dbStatementToExecute.executeUpdate(sqlTextUpdateStatement);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (dbStatementToExecute != null) {
                    //4. close statement
                    try {
                        dbStatementToExecute.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }

                if (dbRwConnection != null) {
                    //5. Close connection
                    try {
                        dbRwConnection.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Change multi Datensets in the datatable.
     */
    @Override
    public void updateDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends Object> objectsToUpdateInDbTable) {
        Statement dbStatementToExecute = null;
        final int indexFirstElement = 0;

        //Check which instance collect which class
        if (objectsToUpdateInDbTable.get(indexFirstElement) instanceof Supplier) {

            try {

                for (Object objectToUpdateInDbTable : objectsToUpdateInDbTable) {

                    //EXPLICIT CAST
                    Supplier supplierToUpdateIntoDbTable = (Supplier) objectToUpdateInDbTable;

                    //1. Database Connection are opened by DbManager

                    //2. Statement object generate
                    dbStatementToExecute = dbRwConnection.createStatement();

                    String sqlTextUpdateStatement = UPDATE_TBL + this.tableName
                            + SET_OPERATOR
                            + COL_NAME_SUPPLIER_NAME_INC_COL_BACK_TICKS
                            + EQUALS_OPERATOR
                            + CHAR_VALUE_BACK_TICK + supplierToUpdateIntoDbTable.getSuppilerName() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + COL_NAME_SUPPLIER_CITY_INC_COL_BACK_TICKS
                            + EQUALS_OPERATOR + supplierToUpdateIntoDbTable.getSupplierCity() + CHAR_COMMA
                            + COL_NAME_SUPPLIER_POST_CODE_INC_COL_BACK_TICKS
                            + EQUALS_OPERATOR + supplierToUpdateIntoDbTable.getSupplierPostCode() + CHAR_COMMA
                            + COL_NAME_SUPPLIER_PREFERT_DELIVERY_MODE_INC_COL_BACK_TICKS
                            + EQUALS_OPERATOR
                            + CHAR_VALUE_BACK_TICK + supplierToUpdateIntoDbTable.getPrefertDeliveryMode() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + COL_NAME_SUPPLIER_DELIVERY_MATERIAL_INC_COL_BACK_TICKS
                            + EQUALS_OPERATOR + supplierToUpdateIntoDbTable.getDeliveryMaterial() + CHAR_COMMA
                            + COL_NAME_SUPPLIER_AVERAGE_DELIVERY_DAYS_INC_COL_BACK_TICKS
                            + EQUALS_OPERATOR + supplierToUpdateIntoDbTable.getAverageDeliveryDays()
                            + WHERE_CONDITION + COL_NAME_ID_INC_COL_BACK_TICKS + EQUALS_OPERATOR + supplierToUpdateIntoDbTable.getId() + CHAR_SEMICOLON;

                    //DEBUG
                    // System.out.println(">>>>>>>> " + sqlTextUpdateStatement);

                    //3. SQL - String to statement object
                    dbStatementToExecute.executeUpdate(sqlTextUpdateStatement);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (dbStatementToExecute != null) {
                    //4. Close statement
                    try {
                        dbStatementToExecute.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }

                if (dbRwConnection != null) {
                    //5. Close connection
                    try {
                        dbRwConnection.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }
            }
        }
    }
    //endregion

    /**
     * Read all Data records from the datatable
     */
    //region DB Read
    @Override
    public List<Supplier> getAllDataRecordsFromDbTbl(Connection dbRwConnection) {

        //Decl. and Init
        List<Supplier> allSuppliersFromDbTable = new ArrayList<>();

        Statement dbStatementToExecute = null;

        try {
            //1. Create statenement
            dbStatementToExecute = dbRwConnection.createStatement();

            //2. Create Query and save result
            String sqlTextGetAllDataRecordsStatement = SELECT_ALL_DATA_FROM + TABLE_NAME_TRIPS;


            ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(sqlTextGetAllDataRecordsStatement);

            //3. ResultSet == get through all results
            while (resultSetFromExecutedQuery.next()) {

                Supplier supplierFromDbTable = this.getModelFromResultSet(resultSetFromExecutedQuery);

                //5. Model Object adjust to the list
                allSuppliersFromDbTable.add(supplierFromDbTable);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (dbStatementToExecute != null) {
                //6. Close Statement
                try {
                    dbStatementToExecute.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }

            if (dbRwConnection != null) {
                //7. Close connection
                try {
                    dbRwConnection.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
        }

        return allSuppliersFromDbTable;
    }

    /**
     * Dummy Methode for correct runs !
     */
    @Override
    public List<Delivery> getAllDataRecordsForSpecificalRelIdFromDbTbl(Connection dbRwConnection, String tableName, int iId) {
        return null;
    }

    /**
     * Read one and only spec. Supplier Dataset from the Database
     */
    @Override
    public Supplier getSpecificDataRecordFromDbTblById(Connection dbRwConnection, int iId) {
        Supplier specificSupplier = null;

        //Decl. and Init
        Statement dbStatementToExecute = null;

        try {
            //0. Create statenement
            dbStatementToExecute = dbRwConnection.createStatement();

            // 1. create Query and save results
            String sqlTextGetRecordByIdStatement =
                    SELECT_ALL_DATA_FROM + TABLE_NAME_TRIPS + WHERE_CONDITION + COL_NAME_ID + EQUALS_OPERATOR + iId;

            ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(sqlTextGetRecordByIdStatement);

            //2. ResultSet == get through all results
            if (resultSetFromExecutedQuery.first()) {

                specificSupplier = this.getModelFromResultSet(resultSetFromExecutedQuery);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (dbStatementToExecute != null) {
                //3. close statement
                try {
                    dbStatementToExecute.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }

            if (dbRwConnection != null) {
                //4. close connection
                try {
                    dbRwConnection.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
        }

        return specificSupplier;
    }

    /**
     * Read one and only spec. Supplier Dataset from the Database
     */
    @Override
    public Supplier getSpecificSupplierDataRecordFromDbTblById(Connection dbRwConnection, int iId) {
        Supplier specificSupplier = null;

        //Decl. and Init
        Statement dbStatementToExecute = null;

        try {
            //0. Create statenement
            dbStatementToExecute = dbRwConnection.createStatement();

            // 1. create Query and save results
            String sqlTextGetRecordByIdStatement =
                    SELECT_ALL_DATA_FROM + TABLE_NAME_TRIPS + WHERE_CONDITION + COL_NAME_ID + EQUALS_OPERATOR + iId;

            ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(sqlTextGetRecordByIdStatement);

            //2. ResultSet == get through all results
            if (resultSetFromExecutedQuery.first()) {

                specificSupplier = this.getModelFromResultSet(resultSetFromExecutedQuery);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (dbStatementToExecute != null) {
                //3. close statement
                try {
                    dbStatementToExecute.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }

            if (dbRwConnection != null) {
                //4. close connection
                try {
                    dbRwConnection.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
        }
        return specificSupplier;
    }
    //endregion

    /**
     * Delete once Dataset from the supplier datatable
     */
    //region Delete Dataset
    @Override
    public void deleteDataRecordInDbTblById(Connection dbRwConnection, int iId) {
        Statement dbStatementToExecute = null;

        try {
            //1. generate statenement
            dbStatementToExecute = dbRwConnection.createStatement();

            // 2. genrate test statement
            // String strSqlDeleteUserById = "DELETE FROM `deliveries` WHERE `_id` = " + iId;

            String strSqlDeleteUserById = ASqlKeyWords.DELETE_FROM_TBL + TABLE_NAME_TRIPS + WHERE_CONDITION
                    + COL_NAME_ID_INC_COL_BACK_TICKS
                    + ASqlKeyWords.EQUALS_OPERATOR + iId;

            //DEBUG
            //System.out.println(">>>>>>>> " + strSqlDeleteUserById);

            dbStatementToExecute.executeUpdate(strSqlDeleteUserById);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (dbStatementToExecute != null) {
                //3. close Statement
                try {
                    dbStatementToExecute.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }

            if (dbRwConnection != null) {
                //4. close connection
                try {
                    dbRwConnection.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
        }
    }
    //endregion

    /**
     * Model of supplier resultset from Gui
     */
    //region Model ResultSet from Gui Form
    @Override
    protected Supplier getModelFromResultSet(ResultSet currentResultSet) throws Exception {
        // 0. reading Table indizes
        final int columnIndexId = currentResultSet.findColumn(COL_NAME_ID);

        final int columnIndexSupplierNameId = currentResultSet.findColumn(COL_NAME_SUPPLIER_NAME);

        final int columnIndexSupplierStreetId = currentResultSet.findColumn(COL_NAME_SUPPLIER_STREET);
        final int columnIndexSupplierCityId = currentResultSet.findColumn(COL_NAME_SUPPLIER_CITY);
        final int columnIndexSupplierPostCodeId = currentResultSet.findColumn(COL_NAME_SUPPLIER_POST_CODE);

        final int columnIndexSupplierPrefertDeliveryMode = currentResultSet.findColumn(COL_NAME_SUPPLIER_PREFERT_DELIVERY_MODE);
        final int columnIndexSupplierDeliveryMaterial = currentResultSet.findColumn(COL_NAME_SUPPLIER_DELIVERY_MATERIAL);
        final int columnIndexSupplierAverageDeliveryDays = currentResultSet.findColumn(COL_NAME_SUPPLIER_AVERAGE_DELIVERY_DAYS);


        int id = currentResultSet.getInt(columnIndexId);

        String SupplierNameId = currentResultSet.getString(columnIndexSupplierNameId);

        String SupplierStreet = currentResultSet.getString(columnIndexSupplierStreetId);
        String SupplierCityId = currentResultSet.getString(columnIndexSupplierCityId);
        String SupplierPostCodeId = currentResultSet.getString(columnIndexSupplierPostCodeId);

        String SupplierPrefertDeliveryMode = currentResultSet.getString(columnIndexSupplierPrefertDeliveryMode);
        String SupplierDeliveryMaterial = currentResultSet.getString(columnIndexSupplierDeliveryMaterial);
        String SupplierAverageDeliveryDays = currentResultSet.getString(columnIndexSupplierAverageDeliveryDays);

        //1. genrate new Model object
        Supplier supplierFromDb = new Supplier(SupplierNameId, SupplierCityId, SupplierPostCodeId);

        supplierFromDb.setId(id);
        supplierFromDb.setSupplierStreet(SupplierStreet);

        supplierFromDb.setPrefertDeliveryMode(SupplierPrefertDeliveryMode);
        supplierFromDb.setDeliveryMaterial(SupplierDeliveryMaterial);
        supplierFromDb.setAverageDeliveryDays(SupplierAverageDeliveryDays);

        return supplierFromDb;
    }
    //endregion
}
/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/10/2022 10:50AM).
 */
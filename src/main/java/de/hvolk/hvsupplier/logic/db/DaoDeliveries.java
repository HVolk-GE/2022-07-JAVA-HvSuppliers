package de.hvolk.hvsupplier.logic.db;

import de.hvolk.hvsupplier.model.Delivery;
import de.hvolk.hvsupplier.settings.AppTexts;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Specific Dao Class for deliveries (table) in the suppliers Database !
 */
public class DaoDeliveries extends ADao {

    //region 0. constants
    protected static final String COL_NAME_DELIVERY_ID = "_id";
    protected static final String COL_NAME_DELIVERY_ID_INC_COL_BACK_TICKS =
            CHAR_COL_BACK_TICK + COL_NAME_DELIVERY_ID + CHAR_COL_BACK_TICK;
    protected static final String COL_NAME_DELIVERY_SUPPLIER_ID = "_idsup";
    protected static final String COL_NAME_DELIVERY_SUPPLIER_ID_INC_COL_BACK_TICKS =
            CHAR_COL_BACK_TICK + COL_NAME_DELIVERY_SUPPLIER_ID + CHAR_COL_BACK_TICK;
    protected static final String COL_NAME_DELIVERY_SUPPLIER_NAME = "deliverysupplier";
    protected static final String COL_NAME_DELIVERY_SUPPLIER_NAME_INC_COL_BACK_TICKS =
            CHAR_COL_BACK_TICK + COL_NAME_DELIVERY_SUPPLIER_NAME + CHAR_COL_BACK_TICK;

    protected static final String COL_NAME_DELIVERY_MODE = "delieverymode";
    protected static final String COL_NAME_DELIVERY_MODE_INC_COL_BACK_TICKS =
            CHAR_COL_BACK_TICK + COL_NAME_DELIVERY_MODE + CHAR_COL_BACK_TICK;

    protected static final String COL_NAME_DELIVERY_CONTENT = "deliverycontent";
    protected static final String COL_NAME_DELIVERY_CONTENT_INC_COL_BACK_TICKS =
            CHAR_COL_BACK_TICK + COL_NAME_DELIVERY_CONTENT + CHAR_COL_BACK_TICK;

    protected static final String COL_NAME_DELIVERY_FROM = "deliveryfrom";
    protected static final String COL_NAME_DELIVERY_FROM_INC_COL_BACK_TICKS =
            CHAR_COL_BACK_TICK + COL_NAME_DELIVERY_FROM + CHAR_COL_BACK_TICK;

    protected static final String COL_NAME_DELIVERY_TO = "deliveryto";
    protected static final String COL_NAME_DELIVERY_TO_INC_COL_BACK_TICKS =
            CHAR_COL_BACK_TICK + COL_NAME_DELIVERY_TO + CHAR_COL_BACK_TICK;

    protected static final String COL_NAME_DELIVERY_DAYS = "deliverydays";
    protected static final String COL_NAME_DELIVERY_DAYS_INC_COL_BACK_TICKS =
            CHAR_COL_BACK_TICK + COL_NAME_DELIVERY_DAYS + CHAR_COL_BACK_TICK;

    private static final String TABLE_NAME_DELIVERIES_TRIPS = "deliveries";
    //endregion


    //region 2. constructor
    public DaoDeliveries() {
        super(TABLE_NAME_DELIVERIES_TRIPS);
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
        if (objectToInsertIntoDbTable instanceof Delivery) {

            //EXPLICIT CAST
            Delivery deliveryToInsertIntoDbTable = (Delivery) objectToInsertIntoDbTable;

            try {
                //1. Database Connection are opened by DbManager

                //2. Statement object generate
                dbStatementToExecute = dbRwConnection.createStatement();

                String sqlTextInsertStatement = INSERT_TBL + this.tableName + CHAR_OPEN_PARENTHESIS
                        + COL_NAME_DELIVERY_SUPPLIER_ID_INC_COL_BACK_TICKS + CHAR_COMMA
                        + COL_NAME_DELIVERY_SUPPLIER_NAME_INC_COL_BACK_TICKS + CHAR_COMMA
                        + COL_NAME_DELIVERY_CONTENT_INC_COL_BACK_TICKS + CHAR_COMMA
                        + COL_NAME_DELIVERY_MODE_INC_COL_BACK_TICKS + CHAR_COMMA
                        + COL_NAME_DELIVERY_FROM_INC_COL_BACK_TICKS + CHAR_COMMA
                        + COL_NAME_DELIVERY_TO_INC_COL_BACK_TICKS + CHAR_COMMA
                        + COL_NAME_DELIVERY_DAYS_INC_COL_BACK_TICKS
                        + CHAR_CLOSE_PARENTHESIS
                        + VALUES_OPERATOR + CHAR_OPEN_PARENTHESIS
                        + CHAR_VALUE_BACK_TICK + deliveryToInsertIntoDbTable.getIdsup() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + CHAR_VALUE_BACK_TICK + deliveryToInsertIntoDbTable.getDeliverySupplier() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + CHAR_VALUE_BACK_TICK + deliveryToInsertIntoDbTable.getDeliveryContent() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + CHAR_VALUE_BACK_TICK + deliveryToInsertIntoDbTable.getDeliveryMode() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + CHAR_VALUE_BACK_TICK + deliveryToInsertIntoDbTable.getDeliveryFrom() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + CHAR_VALUE_BACK_TICK + deliveryToInsertIntoDbTable.getDeliveryTo() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + CHAR_VALUE_BACK_TICK + deliveryToInsertIntoDbTable.getDeliveryDays() + CHAR_VALUE_BACK_TICK
                        + CHAR_CLOSE_PARENTHESIS_SEMICOLON;


                //3. SQL - String to statement object
                //DEBUG: System.out.println("SQL Statement: " + sqlTextInsertStatement);
                dbStatementToExecute.execute(sqlTextInsertStatement);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(AppTexts.DATEN_IMPORT);
                alert.setHeaderText(AppTexts.DATEN_WURDEN_ERFOLGREICH_IMPORTIERT);
                alert.setContentText(AppTexts.IHRE_DATEN_WURDEN_ERFOLGREICH_IMPORTIERT);
                alert.show();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(AppTexts.DATEN_IMPORT);
                alert.setHeaderText(AppTexts.DATEN_KONNTEN_NICHT_IMPORTIERT_WERDEN);
                alert.setContentText(AppTexts.IHRE_DATEN_KONNTEN_NICHT_IMPORTIERT_WERDEN);
                alert.show();
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
        if (objectsToInsertIntoDbTable.get(indexFirstElement) instanceof Delivery) {


            try {
                //1. Database Connection are opened by DbManager

                for (Object objectToInsert : objectsToInsertIntoDbTable) {

                    //EXPLICIT CAST
                    Delivery deliveryToInsertIntoDbTable = (Delivery) objectToInsert;

                    //2. Statement object generate
                    dbStatementToExecute = dbRwConnection.createStatement();

                    String sqlTextInsertStatement = INSERT_TBL + this.tableName + CHAR_OPEN_PARENTHESIS
                            + COL_NAME_DELIVERY_SUPPLIER_ID_INC_COL_BACK_TICKS + CHAR_COMMA
                            + COL_NAME_DELIVERY_SUPPLIER_NAME_INC_COL_BACK_TICKS + CHAR_COMMA
                            + COL_NAME_DELIVERY_CONTENT_INC_COL_BACK_TICKS + CHAR_COMMA
                            + COL_NAME_DELIVERY_MODE_INC_COL_BACK_TICKS + CHAR_COMMA
                            + COL_NAME_DELIVERY_FROM_INC_COL_BACK_TICKS + CHAR_COMMA
                            + COL_NAME_DELIVERY_TO_INC_COL_BACK_TICKS + CHAR_COMMA
                            + COL_NAME_DELIVERY_DAYS_INC_COL_BACK_TICKS
                            + CHAR_CLOSE_PARENTHESIS
                            + VALUES_OPERATOR + CHAR_OPEN_PARENTHESIS
                            + CHAR_VALUE_BACK_TICK + deliveryToInsertIntoDbTable.getIdsup() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + CHAR_VALUE_BACK_TICK + deliveryToInsertIntoDbTable.getDeliverySupplier() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + CHAR_VALUE_BACK_TICK + deliveryToInsertIntoDbTable.getDeliveryMode() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + CHAR_VALUE_BACK_TICK + deliveryToInsertIntoDbTable.getDeliveryContent() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + CHAR_VALUE_BACK_TICK + deliveryToInsertIntoDbTable.getDeliveryFrom() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + CHAR_VALUE_BACK_TICK + deliveryToInsertIntoDbTable.getDeliveryTo() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + CHAR_VALUE_BACK_TICK + deliveryToInsertIntoDbTable.getDeliveryDays() + CHAR_VALUE_BACK_TICK
                            + CHAR_CLOSE_PARENTHESIS_SEMICOLON;

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
        if (objectToUpdateInDbTable instanceof Delivery) {

            //EXPLICIT CAST
            Delivery deliveryToUpdateIntoDbTable = (Delivery) objectToUpdateInDbTable;

            try {

                //1. Database Connection are opened by DbManager

                //2. Statement object generate
                dbStatementToExecute = dbRwConnection.createStatement();

                String sqlTextUpdateStatement = UPDATE_TBL + this.tableName
                        + SET_OPERATOR
                        + COL_NAME_DELIVERY_SUPPLIER_NAME_INC_COL_BACK_TICKS
                        + EQUALS_OPERATOR
                        + CHAR_VALUE_BACK_TICK + deliveryToUpdateIntoDbTable.getIdsup() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + COL_NAME_DELIVERY_MODE_INC_COL_BACK_TICKS
                        + EQUALS_OPERATOR + CHAR_VALUE_BACK_TICK + deliveryToUpdateIntoDbTable.getDeliverySupplier() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + COL_NAME_DELIVERY_CONTENT_INC_COL_BACK_TICKS
                        + EQUALS_OPERATOR + CHAR_VALUE_BACK_TICK + deliveryToUpdateIntoDbTable.getDeliveryMode() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + COL_NAME_DELIVERY_FROM_INC_COL_BACK_TICKS
                        + EQUALS_OPERATOR + CHAR_VALUE_BACK_TICK + deliveryToUpdateIntoDbTable.getDeliveryContent() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + COL_NAME_DELIVERY_TO_INC_COL_BACK_TICKS
                        + EQUALS_OPERATOR
                        + CHAR_VALUE_BACK_TICK + deliveryToUpdateIntoDbTable.getDeliveryFrom() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + COL_NAME_DELIVERY_DAYS_INC_COL_BACK_TICKS
                        + EQUALS_OPERATOR + CHAR_VALUE_BACK_TICK + deliveryToUpdateIntoDbTable.getDeliveryTo() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                        + WHERE_CONDITION + COL_NAME_ID_INC_COL_BACK_TICKS + EQUALS_OPERATOR + deliveryToUpdateIntoDbTable.getId() + CHAR_SEMICOLON;

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
        if (objectsToUpdateInDbTable.get(indexFirstElement) instanceof Delivery) {

            try {

                for (Object objectToUpdateInDbTable : objectsToUpdateInDbTable) {

                    //EXPLICIT CAST
                    Delivery deliveryToUpdateIntoDbTable = (Delivery) objectToUpdateInDbTable;

                    //1. Database Connection are opened by DbManager

                    //2. Statement object generate
                    dbStatementToExecute = dbRwConnection.createStatement();

                    String sqlTextUpdateStatement = UPDATE_TBL + this.tableName
                            + SET_OPERATOR
                            + COL_NAME_DELIVERY_SUPPLIER_NAME_INC_COL_BACK_TICKS
                            + EQUALS_OPERATOR
                            + CHAR_VALUE_BACK_TICK + deliveryToUpdateIntoDbTable.getDeliverySupplier() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + COL_NAME_DELIVERY_MODE_INC_COL_BACK_TICKS
                            + EQUALS_OPERATOR + deliveryToUpdateIntoDbTable.getDeliveryMode() + CHAR_COMMA
                            + COL_NAME_DELIVERY_FROM_INC_COL_BACK_TICKS
                            + EQUALS_OPERATOR + deliveryToUpdateIntoDbTable.getDeliveryFrom() + CHAR_COMMA
                            + COL_NAME_DELIVERY_TO_INC_COL_BACK_TICKS
                            + EQUALS_OPERATOR + CHAR_VALUE_BACK_TICK + deliveryToUpdateIntoDbTable.getDeliveryTo() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + COL_NAME_DELIVERY_DAYS_INC_COL_BACK_TICKS
                            + EQUALS_OPERATOR + deliveryToUpdateIntoDbTable.getDeliveryDays() + CHAR_COMMA
                            + WHERE_CONDITION + COL_NAME_ID_INC_COL_BACK_TICKS + EQUALS_OPERATOR + deliveryToUpdateIntoDbTable.getId() + CHAR_SEMICOLON;

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
    public List<Delivery> getAllDataRecordsFromDbTbl(Connection dbRwConnection) {

        //Decl. and Init
        List<Delivery> allSuppliersFromDbTable = new ArrayList<>();

        Statement dbStatementToExecute = null;

        try {
            //1. Create statenement
            dbStatementToExecute = dbRwConnection.createStatement();

            //2. Create Query and save result
            String sqlTextGetAllDataRecordsStatement = SELECT_ALL_DATA_FROM + TABLE_NAME_DELIVERIES_TRIPS;
//            TABLE_NAME_DELIVERIES_TRIPS;

            ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(sqlTextGetAllDataRecordsStatement);

            //3. ResultSet == get through all results
            while (resultSetFromExecutedQuery.next()) {

                Delivery deliveryFromDbTable = this.getModelFromResultSet(resultSetFromExecutedQuery);

                //5. Model Object adjust to the list
                allSuppliersFromDbTable.add(deliveryFromDbTable);
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
     * Read all Data records with one specify SupplierID
     */

    @Override
    public List<Delivery> getAllDataRecordsForSpecificalRelIdFromDbTbl(Connection dbRwConnection, String tableName, int iId) {

        //Decl. and Init
        List<Delivery> allSuppliersFromDbTable = new ArrayList<>();

        Statement dbStatementToExecute = null;

        try {
            //1. Create statenement
            dbStatementToExecute = dbRwConnection.createStatement();

            //2. Create Query and save result
            String sqlTextGetAllDataRecordsStatement = SELECT_ALL_DATA_FROM + TABLE_NAME_DELIVERIES_TRIPS + WHERE_CONDITION + COL_NAME_DELIVERY_SUPPLIER_ID + EQUALS_OPERATOR + iId;

            ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(sqlTextGetAllDataRecordsStatement);

            //3. ResultSet == get through all results
            while (resultSetFromExecutedQuery.next()) {

                Delivery deliveryFromDbTable = this.getModelFromResultSet(resultSetFromExecutedQuery);

                //5. Model Object adjust to the list
                allSuppliersFromDbTable.add(deliveryFromDbTable);
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
     * Read one and only spec. Delivery Dataset from the Database
     */
    @Override
    public Delivery getSpecificDataRecordFromDbTblById(Connection dbRwConnection, int iId) {
        Delivery specificDelivery = null;

        //Decl. and Init
        Statement dbStatementToExecute = null;

        try {
            //0. Create statenement
            dbStatementToExecute = dbRwConnection.createStatement();

            // 1. create Query and save results
            String sqlTextGetRecordByIdStatement =
                    SELECT_ALL_DATA_FROM + TABLE_NAME_DELIVERIES_TRIPS + WHERE_CONDITION + COL_NAME_DELIVERY_ID + EQUALS_OPERATOR + iId;

            ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(sqlTextGetRecordByIdStatement);

            //2. ResultSet == get through all results
            if (resultSetFromExecutedQuery.first()) {

                specificDelivery = this.getModelFromResultSet(resultSetFromExecutedQuery);

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

        return specificDelivery;
    }
    //endregion

    /**
     * Read one and only spec. Delivery Dataset from the Database
     */
    //region recordset by Supplier:
//    @Override
    public List<Delivery> getSpecificSupplierDataRecordFromDbTblById(Connection dbRwConnection, int iId) {
        Delivery specificDelivery = null;

        //Decl. and Init
        Statement dbStatementToExecute = null;

        try {
            //0. Create statenement
            dbStatementToExecute = dbRwConnection.createStatement();

            // 1. create Query and save results
            String sqlTextGetRecordByIdStatement =
                    SELECT_ALL_DATA_FROM + TABLE_NAME_DELIVERIES_TRIPS + WHERE_CONDITION + COL_NAME_DELIVERY_SUPPLIER_ID + EQUALS_OPERATOR + iId;

            ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(sqlTextGetRecordByIdStatement);

            //2. ResultSet == get once result
            while (resultSetFromExecutedQuery.next()) {

                specificDelivery = this.getModelFromResultSet(resultSetFromExecutedQuery);
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

        return (List<Delivery>) specificDelivery;
    }
    //endregion

    /**
     * Delete once Dataset from the deliveries datatable
     */

    //region Delete once Dataset
    @Override
    public void deleteDataRecordInDbTblById(Connection dbRwConnection, int iId) {
        Statement dbStatementToExecute = null;

        try {
            //1. generate statenement
            dbStatementToExecute = dbRwConnection.createStatement();

            // 2. genrate test statement
            // String strSqlDeleteUserById = "DELETE FROM `deliveries` WHERE `_id` = " + iId;

            String strSqlDeleteUserById = ASqlKeyWords.DELETE_FROM_TBL + TABLE_NAME_DELIVERIES_TRIPS + WHERE_CONDITION
                    + COL_NAME_ID_INC_COL_BACK_TICKS
                    + ASqlKeyWords.EQUALS_OPERATOR + iId;

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
     * Model of delivery resultset from Gui
     */
    //region Model ResultSet from Gui Form
    @Override
    protected Delivery getModelFromResultSet(ResultSet currentResultSet) throws Exception {
        // 0. reading Table indizes
        final int columnIndexId = currentResultSet.findColumn(COL_NAME_DELIVERY_ID);
        final int columnIndexSupplierId = currentResultSet.findColumn(COL_NAME_DELIVERY_SUPPLIER_ID);

        final int columnIndexDeliverySupplierNameId = currentResultSet.findColumn(COL_NAME_DELIVERY_SUPPLIER_NAME);

        final int columnIndexDeliveryContentId = currentResultSet.findColumn(COL_NAME_DELIVERY_CONTENT);
        final int columnIndexDeliveryModeId = currentResultSet.findColumn(COL_NAME_DELIVERY_MODE);
        final int columnIndexDeliveryFromId = currentResultSet.findColumn(COL_NAME_DELIVERY_FROM);

        final int columnIndexDeliveryToId = currentResultSet.findColumn(COL_NAME_DELIVERY_TO);
        final int columnIndexDeliveryDaysId = currentResultSet.findColumn(COL_NAME_DELIVERY_DAYS);


        int id = currentResultSet.getInt(columnIndexId);
        int idSup = currentResultSet.getInt(columnIndexSupplierId);

        String DeliverySupplierNameId = currentResultSet.getString(columnIndexDeliverySupplierNameId);
        String DeliveryModeId = currentResultSet.getString(columnIndexDeliveryModeId);
        String DeliveryContentId = currentResultSet.getString(columnIndexDeliveryContentId);

        String DeliveryFromId = currentResultSet.getString(columnIndexDeliveryFromId);
        String DeliveryToId = currentResultSet.getString(columnIndexDeliveryToId);

        int DeliveryDaysId = currentResultSet.getInt(columnIndexDeliveryDaysId);

        //1. genrate new Model object
        Delivery deliveryFromDb = new Delivery();
        deliveryFromDb.setId(id);
        deliveryFromDb.setIdsup(idSup);

        deliveryFromDb.setDeliverySupplier(DeliverySupplierNameId);
        deliveryFromDb.setDeliveryMode(DeliveryModeId);
        deliveryFromDb.setDeliveryContent(DeliveryContentId);

        deliveryFromDb.setDeliveryFrom(DeliveryFromId);
        deliveryFromDb.setDeliveryTo(DeliveryToId);

        deliveryFromDb.setDeliveryDays(DeliveryDaysId);

        return deliveryFromDb;
    }
    //endregion
}
/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/09/2022 16:35PM).
 */
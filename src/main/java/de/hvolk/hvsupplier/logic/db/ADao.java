package de.hvolk.hvsupplier.logic.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 * Diese Klasse deiniert was alle Datenbanktabellen
 * und Ihre Dao-Klassen gemeinsam haben. Halbe Wahrheit, wenn man mehr als eine Tabelle hat und eine Teilmenge daraus holen möchte,
 * braucht man eine eigene get funktion, da diese hier nicht beruecksichtigt (Teilmengen) wurden.
 * <ul>
 *     <li>Spaltennamen</li>
 *     <li>CRUD-Operationen</li>
 *     <li>Hilfsmethoden und Funktionen</li>
 * </ul>
 */

public abstract class ADao extends ASqlKeyWords {

    //region 0. Konstanten
    /**
     * Primaerschluessel aller Tabellen dieses Projekts
     * ansonsten koennnte man auch hier PRIMARY KEY als Namen
     * verwenden.
     */
    protected static final String COL_NAME_ID                    = "_id";
    protected static final String COL_NAME_ID_INC_COL_BACK_TICKS =
            CHAR_COL_BACK_TICK + COL_NAME_ID + CHAR_COL_BACK_TICK;
    //endregion

    //region 1. DEcl. and Init Attribute
    protected String tableName;
    //endregion

    //region 2. Konstruktor
    protected ADao(String tableName) {
        this.tableName = tableName;
    }
    //endregion


    //region Insert
    /**
     * Fuegt einen einzelen Datensatz in die Datebanktabelle ein
     *
     * @param dbRwConnection          : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param objectToInsertIntoDbTable : {@link Object : Model welches eingefuegt werden soll
     */
    public abstract void insertDataRecordIntoDbTbl(Connection dbRwConnection, Object objectToInsertIntoDbTable);

    /**
     * Fuegt eine Liste von Datensaetzen in die Datebanktabelle ein
     *
     * @param dbRwConnection           : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param objectsToInsertIntoDbTable : {@link Object} : Models welches eingefuegt werden soll
     */
    public abstract void insertDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends Object> objectsToInsertIntoDbTable);
    //endregion

    //region Update

    /**
     * Aendert einen einzelen Datensatz in der Datebanktabelle
     *
     * @param dbRwConnection        : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param objectToUpdateInDbTable : {@link Object} : Model welches geaendert werden soll
     */
    public abstract void updateDataRecordIntoDbTbl(Connection dbRwConnection, Object objectToUpdateInDbTable);

    /**
     * Aendert eine Liste von Datensaetzen in der Datebanktabelle
     *
     * @param dbRwConnection         : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param objectsToUpdateInDbTable : {@link Object} : Models welches geaendert werden soll
     */
    public abstract void updateDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends Object> objectsToUpdateInDbTable);
    //endregion

    //region Read Recordsets
    /**
     * Gibt alle Datensaetze eine Datenbanktabelle als {@link List} zurueck
     *
     * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     *
     * @return allDataRecordsFromDbTbl : {@link List} Objects extended from {@link Object} : Liste aller Datensaetze
     */
    public abstract List<? extends Object> getAllDataRecordsFromDbTbl(Connection dbRwConnection);

    /**
     * Gibt alle Datensaetze eine Datenbanktabelle die einer Relation-ID entsprechen als {@link List} zurueck
     * Tabellennamen und Relation-ID werden als Uebergabewerte der Funktion mit gegeben.
     * Somit ist eine flexiblere Abfrage ueber mehrere Tabellen/Relationen mit einer bestimmten Relation-ID moeglich.
     *
     * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     *
     * @return Read allDataRecordsForSpecificalRelIdFromDbTbl : {@link List} Objects extended from {@link Object} : Liste aller Datensaetze einer Entsprechenden Relation Tabellen ID zurück
     */
    public abstract List<? extends Object> getAllDataRecordsForSpecificalRelIdFromDbTbl(Connection dbRwConnection, String tableName, int iId);

    /**
     * Gibt einen bestimmten Datensatz einer Datenbanktabelle zurueck
     *
     * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param iId
     *
     * @return specificDataRecordFoundById : {@link Object}  oder abgeleitet davon: Gesuchtes Objekt oder null,
     * sollte es dieses nicht geben
     */
    public abstract Object getSpecificDataRecordFromDbTblById(Connection dbRwConnection, int iId);

    /**
     * @param dbRwConnection
     * @param iId
     * @return specificSupplierDataRecordFoundBySupId : {@link Object}  oder abgeleitet davon: Gesuchtes Objekt oder null,
     * sollte es dieses nicht geben
     *
     */
     public abstract Object getSpecificSupplierDataRecordFromDbTblById(Connection dbRwConnection, int iId);
    //endregion

    //region Delete
    /**
     * Loescht einen bestimmten Datensatz aus der Datenbanktabelle
     *
     * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param iId            : int : Id des Objektes welches in der DbTabelle geloscht werden soll
     */
    public abstract void deleteDataRecordInDbTblById(Connection dbRwConnection, int iId);
    //endregion

    //region Model aus ResultSet Formen

    /**
     * Nimmt die Ergebnismenge und formt ein konkretes Model daraus
     *
     * @param currentResultSet : {@link ResultSet} : Ergebnismenge der aktuellen Abfrage
     *
     * @return trip : {@link Object} : Model abgeleitet von der Basisklasse
     *
     * @throws Exception
     */
    protected abstract Object getModelFromResultSet(ResultSet currentResultSet) throws Exception;
    //endregion

}

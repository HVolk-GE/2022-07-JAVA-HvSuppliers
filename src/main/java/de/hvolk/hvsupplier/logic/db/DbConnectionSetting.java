package de.hvolk.hvsupplier.logic.db;

import java.io.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class DbConnectionSetting {

    /**
     * Thread safety connection to the JSON Config file:
     */
    public static synchronized String[] DbConnection() {
        JSONParser parser = new JSONParser();
        String[] dbConnection = new String[7];

        try {
            Object obj = parser.parse(new FileReader("src/main/java/de/hvolk/hvsupplier/sources/DbConnectionSettings.json"));
            JSONObject jsonObject = (JSONObject) obj;
            String databaseConnectUri = (String) jsonObject.get("DatabaseConnectUri");
            String databaseServerNameIP = (String) jsonObject.get("DatabaseServerNameIP");
            String databaseTcpPortNumber = (String) jsonObject.get("DatabaseTcpPortNumber");
            String databaseName = (String) jsonObject.get("DatabaseName");
            String databaseDriver = (String) jsonObject.get("DatabaseDriver");
            String databaseUserName = (String) jsonObject.get("DatabaseUserName");
            String databaseUserPasswort = (String) jsonObject.get("DatabaseUserPasswort");


            dbConnection[0] = databaseConnectUri;
            dbConnection[1] = databaseServerNameIP;
            dbConnection[2] = databaseTcpPortNumber;
            dbConnection[3] = databaseName;
            dbConnection[4] = databaseDriver;
            dbConnection[5] = databaseUserName;
            dbConnection[6] = databaseUserPasswort;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConnection;
    }
}
/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/10/2022 10:45AM).
 */

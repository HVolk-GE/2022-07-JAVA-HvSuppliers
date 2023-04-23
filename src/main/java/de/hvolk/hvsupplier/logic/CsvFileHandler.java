package de.hvolk.hvsupplier.logic;

import de.hvolk.hvsupplier.model.Contact;
import de.hvolk.hvsupplier.settings.AppTexts;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>CSVFileHanlder</h1>
 */

public class CsvFileHandler {

    //region 0. constants
    private static final String CSV_FILE_PATH = AppTexts.SRC_MAIN_JAVA_DE_HVOLK_HVSUPPLIER_SOURCES_CONTACTS_CSV;
    //endregion

    //region 1. Declare and Init Attribute
    private static CsvFileHandler onlyInstanceOfThisClassEver;
    //endregion

    //region 2. Konstruktoren
    /**
     * constructor runs once, after call getOnlyInstanceOfThisClassEver:
     */
    private CsvFileHandler() {
    }
    //endregion

    //region 3. GetOnlyInstanceOfThisClassEver
    public static synchronized CsvFileHandler getOnlyInstanceOfThisClassEver() {
        //Check isn't = "null":
        if (onlyInstanceOfThisClassEver == null) {

            //Generiert diese Instanz ein einziges mal:
            onlyInstanceOfThisClassEver = new CsvFileHandler();
        }
        return onlyInstanceOfThisClassEver;
    }
    //endregion

    //region 4. Save Csv-File
    public void saveToCsvFile(List<Contact> contactListToSave) {

        //Created Data object
        File csvFile = new File(CSV_FILE_PATH);

        FileOutputStream fos = null;

        OutputStreamWriter osw = null;

        BufferedWriter out = null;

        try {
            //Check file exists ?
            if (!csvFile.exists()) {
                //Create a new file, if not exists
                csvFile.createNewFile();
            }
            //Fos generate Dateiobjekt
            fos = new FileOutputStream(csvFile);

            //osw Char Set with fos
            osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

            //out with osw generate
            out = new BufferedWriter(osw);

            //read full List line by line
            for (Contact n : contactListToSave) {

                out.write(n.getAllAttributesAsCsvLine());
            }
        } catch (Exception e) {
            System.err.println(CSV_FILE_PATH);
            e.printStackTrace();
        } finally {

            if (out != null) {
                try {
                    out.flush();

                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
    //endregion

    //region 5. Lesen
    public List<Contact> readFromCsvFile(int supId) {
        //Create new ArrayList
        List<Contact> contactListFromFile = new ArrayList<>();

        File csvFile = new File(CSV_FILE_PATH);

        FileInputStream fis = null;

        InputStreamReader isr = null;

        BufferedReader in = null;

        try {
            if (csvFile.exists()) {

                fis = new FileInputStream(csvFile);

                isr = new InputStreamReader(fis, StandardCharsets.UTF_8);

                in = new BufferedReader(isr);

                boolean eof = false;

                while (!eof) {

                    String readCsvLine = in.readLine();

                    if (readCsvLine == null) {
                        eof = true;
                    } else {
                        Contact currentContact = new Contact();

                        currentContact.setAllAttributesFromCsvLine(readCsvLine);

                        contactListFromFile.add(currentContact);
                    }
                }
            }

        } catch (Exception e) {
            System.err.println(CSV_FILE_PATH);
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return contactListFromFile;
    }
}
/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/09/2022 16:48PM).
 */

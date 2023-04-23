package de.hvolk.hvsupplier;

import de.hvolk.hvsupplier.settings.Settings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main Class the central starting point.
 * <h1>Supplier and Delivery Application</h1>
 * <br>
 * <p>Customer request - made to customer's specifications</p>
 * <ul>
 *     <li>Use customers Csv-File, export of suppliers [contact -] address data, Csv file includes supplierId. Provided Csv-File.</li>
 *     <li>Setup a Database with suppliers and deliveries data-tables. Import from provided Excel-Sheet.</li>
 *     <li>Customer want to see; 1. All suppliers with all deliveries, include address date for the supplier.</li>
 *     <li>2. Extra dialog for supplier contact address (Email/Phone contact).</li>
 *     <li>3. Extra dialog for order a new Delivery, for exists suppliers.</li>
 *     <li>-> For new supplier, the customer has a inhouse application for create new supplier.</li>
 * </ul>
 */

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Settings.PRODUCTION_PROCESS_LAYOUT_PATH));
        Scene scene = new Scene(fxmlLoader.load(), Settings.MAIN_SCENE_WIDTH, Settings.MAIN_SCENE_HEIGHT);
        stage.setTitle(Settings.APPLICATION_NAME);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/10/2022 08:45AM).
 */

package de.hvolk.hvsupplier;

import de.hvolk.hvsupplier.settings.AppTexts;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Gui manager for controlling all gui's in this application
 */
public class GuiManager {
    //region 0. constants
    //endregion

    //region 1. Decl and Init Attribute
    private static GuiManager instance;
    //endregion

    //frist stage
    private Stage primaryStage;
    //endregion

    //second stage
    private Stage secondaryStage;
    //endregion

    //third stage
    private Stage thirdStage;
    //endregion

    /**
     * standard constructor
     */
    //region 2. constructors
    private GuiManager() {
        //Nothing to do
    }
    //endregion

    /**
     * one instance of this class synchronised and thread safety
     */
    //region 3. Get Instance
    public static synchronized GuiManager getInstance() {

        if (instance == null) {
            instance = new GuiManager();
        }

        return instance;

    }
    //endregion

    //region 4. Getter and Setter for all stages
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getSecondaryStage() {
        return secondaryStage;
    }

    public void setSecondaryStage(Stage secondaryStage) {
        this.secondaryStage = secondaryStage;
    }

    public Stage getThirdStage() {
        return thirdStage;
    }

    public void setThirdStage() {
        this.thirdStage = thirdStage;
    }
    //endregion

    //region 5. Methods and Function
    //  This stage is loading by default start stage.
    public void openPrimaryGui() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("supplier_layout.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 800);
            primaryStage.setTitle(AppTexts.LIEFERANTEN);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    // open/load secondary stage (contact details for supplier, open with button on the main stage):
    public void openSecondaryGui() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("contact_layout.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load(), 600, 600);

            //New Window
            secondaryStage = new Stage();
            secondaryStage.setTitle(AppTexts.LIEFERANT_KONTAKT_DATEN);
            secondaryStage.setScene(scene);
            secondaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // open/load third stage for create a new delivery order, by supplier:
    public void openThirdGui() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newDelivery_layout.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load(), 815, 480);

            //New Window
            thirdStage = new Stage();
            thirdStage.setTitle(AppTexts.NEUE_LIEFERUNG_ANLEGEN);
            thirdStage.setScene(scene);
            thirdStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion
}
/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/10/2022 09:20AM).
 */
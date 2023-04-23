package de.hvolk.hvsupplier;

import de.hvolk.hvsupplier.logic.CsvFileHandler;
import de.hvolk.hvsupplier.logic.MainController;
import de.hvolk.hvsupplier.model.Contact;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

/**
 * <h1>GuiContactController</h1>
 * <p>
 * this represents the contact - details Gui.
 * contact detalis data (include supplier phone number and email address) provided from costumer as Csv-file, the Csv - file is only read.
 */

public class GuiContactController implements Initializable {

    //region 0. constants
    //endregion

    //region 1. Declare and Init Attribute
    @FXML
    private TextField txtContactFacility;
    @FXML
    private TextField txtContactName;
    @FXML
    private TextField txtContactStreet;
    @FXML
    private TextField txtContactPCodeCity;
    @FXML
    private TextField txtContactPhoneNr;
    @FXML
    private TextField txtContactMobilNr;
    @FXML
    private TextField txtContactEmail;
    private List<Contact> allContacts;
    //endregion

    /**
     * called to initialize a controller after it's root element has been completely processed.
     * load Gui after Gui-objects exists and all objects are established, but before view.
     */
    //region 2. Open contact (second-) Stage:
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.startContactGui();
    }

    //region 3.
    public void startContactGui() {

        int supId = MainController.getInstance().getCurrentId();

        this.allContacts = CsvFileHandler.getOnlyInstanceOfThisClassEver().readFromCsvFile(supId);

        if (supId > 0) {

            for (int i = 0; i < allContacts.size(); i++) {


                if (allContacts.get(i).getId() == supId) {
                    this.txtContactFacility.setText(allContacts.get(i).getContactfacilityName());

                    this.txtContactName.setText(allContacts.get(i).getContactName());

                    this.txtContactStreet.setText(allContacts.get(i).getContactstreetName());
                    this.txtContactPCodeCity.setText(allContacts.get(i).getContactAddress());

                    this.txtContactPhoneNr.setText(allContacts.get(i).getContactPhoneNumber());
                    this.txtContactMobilNr.setText(allContacts.get(i).getContactMobileNumber());

                    this.txtContactEmail.setText(allContacts.get(i).getContactEmail());
                }
            }
        } else {

            //Set all textfields to default/empty value:
            this.txtContactFacility.setText("");
            this.txtContactName.setText("");

            this.txtContactStreet.setText("");
            this.txtContactPCodeCity.setText("");

            this.txtContactPhoneNr.setText("");
            this.txtContactMobilNr.setText("");

            this.txtContactEmail.setText("");
        }
    }
    //endregion
}
/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/10/2022 09:35AM).
 */

package de.hvolk.hvsupplier;

import de.hvolk.hvsupplier.listview.ListVewCellDelivery;
import de.hvolk.hvsupplier.listview.ListViewCellSupplier;
import de.hvolk.hvsupplier.logic.db.DbManager;
import de.hvolk.hvsupplier.logic.db.DbManagerDelivery;

import de.hvolk.hvsupplier.logic.MainController;
import de.hvolk.hvsupplier.model.Supplier;
import de.hvolk.hvsupplier.model.Delivery;
import de.hvolk.hvsupplier.settings.AppTexts;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

/**
 * <h1>GuiSupplierController</h1>
 * <p>
 * this represents the supplier Gui, it controls all Gui fields and inserts.
 * it includes also all tables and views for the suppliers table - !
 */
public class GuiSupplierController implements Initializable {

    //region 0. Constants
    //endregion

    //region 1. Decl. and Init Attribute
    @FXML
    private TextField txtSupplierName;
    @FXML
    private TextField txtSupplierCity;

    @FXML
    private TextField txtSupplierPostCode;

    @FXML
    private TextField txtSupplierPrefertDeliveryMode;

    @FXML
    private TextField txtSupplierDeliveryMaterial;

    @FXML
    private TextField txtSupplierAverageDeliveryDays;

    @FXML
    private ListView<Supplier> listViewSuppliers;

    @FXML
    private ListView<Delivery> listViewDeliveries;

    @FXML
    private TextField txtSupplierStreet;

    @FXML
    private Button bntPersonalContactViewOpen;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnStartSupplierNameSearch;

    @FXML
    private Button btnDeleteSupplier;
    private ListViewCellSupplier listViewCellSupplier;

    private ListVewCellDelivery listVewCellDelivery;

    private List<Supplier> allSuppliers;

    private List<Delivery> allDeliveries;

    private Supplier currentSelectedSupplier;

    private Delivery currentSelectedDelivery;

    private Stage secondaryStage;
    //endregion


    //region 2. Constructor replacement initialize

    /**
     * called to initialize a controller after it's root element has been completely processed.
     * <p>
     * Load Gui after Gui-objects exists and all objects are established, but before view.
     * </p>
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.updateListView();
    }
    //endregion

    //region 3. Supplier-ListView-Update / TextArea Update
    @FXML
    private void showAllSuppliers() {

        if (this.currentSelectedSupplier != null) {

            this.currentSelectedSupplier = null;

            this.resetInputControls();

            this.updateListView();

        }
    }
    //endregion

    //region 4. Button to edit the Supplier base Information / not the Address !!

    /**
     * Button to edit the Supplier base Information / not the Address !!
     * Because the Address is exported csv-file from the local Email-Server.
     */
    @FXML
    private void btnEdit() {

        if (this.txtSupplierStreet.isDisabled() == true) {
            this.txtSupplierStreet.setDisable(false);
            this.txtSupplierPostCode.setDisable(false);
            this.txtSupplierCity.setDisable(false);
            this.txtSupplierDeliveryMaterial.setDisable(false);
            this.txtSupplierPrefertDeliveryMode.setDisable(false);
            this.txtSupplierAverageDeliveryDays.setDisable(false);
        } else {
            this.txtSupplierStreet.setDisable(true);
            this.txtSupplierPostCode.setDisable(true);
            this.txtSupplierCity.setDisable(true);
            this.txtSupplierDeliveryMaterial.setDisable(true);
            this.txtSupplierPrefertDeliveryMode.setDisable(true);
            this.txtSupplierAverageDeliveryDays.setDisable(true);
        }
    }
    //endregion

    //region 5. Start to begin, for fill up the Form, with the minimal data.

    /**
     * Startup, fill the suppliers list and set default active and de-active textfields and buttons for input/select control
     */
    @FXML
    private void updateListView() {

        //Define listview orientation vertical
        this.listViewSuppliers.setOrientation(Orientation.VERTICAL);
        this.listViewDeliveries.setOrientation(Orientation.VERTICAL);

        //Generiere callback which once Items
        this.listViewCellSupplier = new ListViewCellSupplier();
        this.listVewCellDelivery = new ListVewCellDelivery();

        //Observe variable liste declare
        ObservableList observableSuppliers = null;

        if (this.currentSelectedSupplier == null) {

            if (this.allSuppliers == null) {
                List<Supplier> suppliers = DbManager.getInstance().getAllSuppliersFromDb();
                observableSuppliers = FXCollections.observableList(suppliers);
            }
        } else {
            List<Supplier> filteredSuppliers = new ArrayList<>();
            filteredSuppliers.add(this.currentSelectedSupplier);
            observableSuppliers = FXCollections.observableList(filteredSuppliers);
        }

        //Clean all Fields in the Gui:
        this.listViewSuppliers.getItems().clear();

        //Button for view Contact Data on start like disable. The User have to select first one Supplier from the Listview.
        this.bntPersonalContactViewOpen.setDisable(true);
        this.btnDeleteSupplier.setDisable(true);

        //Set current values in the Gui
        this.listViewSuppliers.setItems(observableSuppliers);
        this.listViewSuppliers.setCellFactory(this.listViewCellSupplier);


        //Add OnItemCellClick
        this.listViewSuppliers.getSelectionModel().selectedItemProperty().addListener(this::onItemCellClick);

    }
    //endregion

    /**
     * React. mouseclick on once value in the Supplier-Listview, the other fields in the Gui, would be updated with the current data
     * and the deliveries-Listview updated with the deliveries list of this special supplier.
     */
    //region 6. on Supplier-item click
    private void onItemCellClick(ObservableValue<? extends Supplier> observableList,
                                 Supplier previousSupplier, Supplier selectedSupplier) {

        int supId = -1;

        //Observer variable liste declare
        ObservableList observableDeliveries = null;

        //select one Supplier in the Listview
        if ((selectedSupplier != null) && (!selectedSupplier.equals(previousSupplier))) {
            //selected SupplierId for searching the deliveries !
            MainController.getInstance().setCurrentId(selectedSupplier.getId());

            //read record from the deliveries Datatable, related to the selected supplier:
             supId = MainController.getInstance().getCurrentId();

            if (this.allDeliveries == null) {
                List<Delivery> deliveriesList = DbManagerDelivery.getInstance().getAllDelieveriesForSuppliersFromDb(supId);
                observableDeliveries = FXCollections.observableList(deliveriesList);
            }

            //Listview of deliveries are clean up before new data comes into.
            this.listViewDeliveries.getItems().clear();

            //Found deliveries come from the dataset and put the items in the deliveries-Listview
            if (observableDeliveries != null) {
                this.listViewDeliveries.setItems(observableDeliveries);
                this.listViewDeliveries.setCellFactory(this.listVewCellDelivery);
                this.listViewDeliveries.setDisable(false);
            }

            //Math. count the average delivery days and send to Gui:
            int deliveryDays = 0;
            int deliveriesCounter = 0;

            for (int i = 0; i < observableDeliveries.size(); i++) {
                deliveryDays = deliveryDays + listViewDeliveries.getItems().get(i).getDeliveryDays();
                deliveriesCounter++;
            }
            //Counter adjustment
            deliveriesCounter = deliveriesCounter - 1;

            if (deliveriesCounter > 1) {
                deliveriesCounter = deliveryDays / deliveriesCounter;
            } else {
                deliveriesCounter = deliveryDays;
            }

            this.txtSupplierAverageDeliveryDays.setText(String.valueOf(deliveriesCounter));

            this.bntPersonalContactViewOpen.setDisable(false);

            this.btnEdit.setDisable(false);
            this.btnDeleteSupplier.setDisable(false);

            this.currentSelectedSupplier = selectedSupplier;

            this.showSupplierDetails(this.currentSelectedSupplier);
        }
    }
    //endregion

    //region 7. React of button click for open other/different Gui.

    /**
     * Second window with selected address the contact address open (address-list are imported by Csv-File, represent a list of email-addresses - as exchange export -),
     * Third window for create a new delivery/request/order, this will open new window without some selection of supplier / delivery.
     * by click on the Button !
     */
    @FXML
    private void bntPersonalContactViewOpen() {
        GuiManager.getInstance().openSecondaryGui();
    }

    /**
     * Third window for request a new delivery !
     */
    @FXML
    private void bntNewDeliveryRequest() {
        GuiManager.getInstance().openThirdGui();
    }
    //endregion

    /**
     * Submit / Save / Update selected supplier data, from the supplier form into the Database.
     */
    //region 8. Submit supplier data to the database
    @FXML
    private void submitSupplier() {

        //read all supplier data from Gui
        Supplier supplierFromGui = this.getSupplierFromGui();

        if (supplierFromGui == null) {
            this.showAlertDialog(AppTexts.CANT_SUBMIT_DIALOG, AppTexts.USER_MSG_INPUT_A_VALID_SUPPLIERNAME_FILL_EVERYTHING_IN);
        } else {

            if (this.currentSelectedSupplier == null) {
                DbManager.getInstance().insertSupplierIntoDbTbl(supplierFromGui);
            } else {
                supplierFromGui.setId(this.currentSelectedSupplier.getId());

                DbManager.getInstance().updateSupplierInDbTbl(supplierFromGui);

                this.currentSelectedSupplier = null;
                this.resetInputControls();
            }

            this.txtSupplierStreet.setDisable(true);
            this.txtSupplierPostCode.setDisable(true);
            this.txtSupplierCity.setDisable(true);
            this.txtSupplierDeliveryMaterial.setDisable(true);
            this.txtSupplierPrefertDeliveryMode.setDisable(true);
            this.txtSupplierAverageDeliveryDays.setDisable(true);

            this.updateListView();
        }
    }
    //endregion

    /**
     * Delete selected supplier from the list / database.
     */
    //region 9. Delete reading supplier data from the database
    @FXML
    private void deleteSupplier() {

        if (this.currentSelectedSupplier != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(AppTexts.DELETE);
            alert.setHeaderText(AppTexts.USER_MSG_DELETE_CONFIRMATION);
            alert.setContentText(String.format(AppTexts.USER_MSG_DELETE_THIS_DELIVERY_CONFIRMATION_FORMAT,
                    this.currentSelectedSupplier.getSuppilerName()));

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                DbManager.getInstance().deleteSupplierInDbTblById(this.currentSelectedSupplier.getId());
                this.currentSelectedSupplier = null;

                this.resetInputControls();

                this.updateListView();
            }
        }
    }
    //endregion

    //region 10. Search supplier

    /**
     * Search supplier by supplier-name and show all detail of this one supplier only in the Gui.
     */
    @FXML
    private void startSupplierNameSearch() {
        String deliverySlipIdToSearchFor = this.txtSupplierName.getText();

        if (!this.isSuppierNamePatternValid(deliverySlipIdToSearchFor)) {
            this.resetInputControls();
        } else {

            //1. Decl. and Init Arbeitsvariablen
            boolean foundSupplierName = false;
            int index = 0;

            //2. Search in all Records for result.
            List<Supplier> allSuppliersFromDb = DbManager.getInstance().getAllSuppliersFromDb();
            while (!foundSupplierName && index < allSuppliersFromDb.size()) {
                Supplier currentSupplierNameToCheck = allSuppliersFromDb.get(index);

                if (deliverySlipIdToSearchFor.equals(currentSupplierNameToCheck.getSuppilerName())) {

                    this.currentSelectedSupplier = currentSupplierNameToCheck;
                    this.showSupplierDetails(this.currentSelectedSupplier);
                    this.updateListView();

                    //3. Loop ends with the Searchflag = true
                    foundSupplierName = true;
                }
                //4 increments the index value
                ++index;
            }
        }
    }
    //endregion

    /**
     * Read data from {@link Supplier} and put all data from selected 'one supplier' results in the Gui textfields
     */

    //region 11. Supplier-data inset into the Gui
    private void showSupplierDetails(Supplier supplierToShow) {

        this.txtSupplierName.setText(supplierToShow.getSuppilerName());

        this.txtSupplierStreet.setText(supplierToShow.getSupplierStreet());
        this.txtSupplierCity.setText(supplierToShow.getSupplierCity());
        this.txtSupplierPostCode.setText(supplierToShow.getSupplierPostCode());

        this.txtSupplierDeliveryMaterial.setText(supplierToShow.getDeliveryMaterial());
        this.txtSupplierPrefertDeliveryMode.setText(supplierToShow.getPrefertDeliveryMode());
    }
    //endregion

    //region 12. Help methods and function for Supplier(s)

    /**
     * Valid checks, the user is not allowed to search with an empty / blank search field !
     */
    private boolean isSuppierNamePatternValid(String supplierNameToSearchFor) {

        String userMsgSupplierNamePattern = "";
        boolean isSupplierNamePatternValid = true;

        boolean isEmpty = supplierNameToSearchFor.isEmpty();
        boolean isBlank = supplierNameToSearchFor.isBlank();

        //User messages
        if (isEmpty) {
            userMsgSupplierNamePattern = AppTexts.USER_MSG_SUPPLIERNAME_NOT_BE_EMPTY;
            isSupplierNamePatternValid = false;
        }

        if (isSupplierNamePatternValid && isBlank) {
            userMsgSupplierNamePattern = AppTexts.USER_MSG_SUPPLIERNAME_NOT_BE_BLANK;
            isSupplierNamePatternValid = false;
        }

        if (isSupplierNamePatternValid) {

        }

        if (!userMsgSupplierNamePattern.isEmpty()) {
            this.showAlertDialog(AppTexts.SUPPLIER_NOT_VALID_DIALOG, userMsgSupplierNamePattern);
        }

        return isSupplierNamePatternValid;
    }
    //endregion

    //region 13. reset all Input fields in the Gui control by Button on the Gui!

    /**
     * reset and clean all fields.
     */
    private void resetInputControls() {

        this.txtSupplierName.clear();
        this.txtSupplierName.requestFocus();

        this.txtSupplierName.clear();

        this.txtSupplierStreet.clear();
        this.txtSupplierCity.clear();
        this.txtSupplierPostCode.clear();

        this.txtSupplierPrefertDeliveryMode.clear();
        this.txtSupplierDeliveryMaterial.clear();
        this.txtSupplierAverageDeliveryDays.clear();

        this.listViewDeliveries.getItems().clear();
        this.btnEdit.setDisable(true);
        this.listViewDeliveries.setDisable(true);
        this.btnDeleteSupplier.setDisable(true);
    }
    //endregion

    //region 14. supplier data read from Gui

    /**
     * Create an arraylist (gui-data) returns supplier from Gui.
     */
    private Supplier getSupplierFromGui() {
        Supplier supplierFromGui = null;
        boolean isGuiDataValid = true;

        String supplierName = this.txtSupplierName.getText();

        String supplierStreet = this.txtSupplierStreet.getText();
        String supplierCity = this.txtSupplierCity.getText();
        String supplierPostCode = this.txtSupplierPostCode.getText();

        String supplierPreferDeliveryMode = this.txtSupplierPrefertDeliveryMode.getText();
        String supplierDeliveryMaterial = this.txtSupplierDeliveryMaterial.getText();
        String supplierAverageDeliveryDays = this.txtSupplierAverageDeliveryDays.getText();

        String[] guiData = {
                supplierName,
                supplierStreet,
                supplierCity,
                supplierPostCode,
                supplierPreferDeliveryMode,
                supplierDeliveryMaterial,
                supplierAverageDeliveryDays

        };

        for (String currentInput : guiData) {
            if (currentInput.isEmpty() || currentInput.isBlank()) {
                isGuiDataValid = false;
            }

        }

        isGuiDataValid = this.isSuppierNamePatternValid(supplierName);

        if (isGuiDataValid) {
            supplierFromGui = new Supplier();

            supplierFromGui.setSuppilerName(supplierName);

            supplierFromGui.setSupplierStreet(supplierStreet);
            supplierFromGui.setSupplierCity(supplierCity);
            supplierFromGui.setSupplierPostCode(supplierPostCode);

            supplierFromGui.setPrefertDeliveryMode(supplierPreferDeliveryMode);

            supplierFromGui.setDeliveryMaterial(supplierDeliveryMaterial);
            supplierFromGui.setAverageDeliveryDays(supplierAverageDeliveryDays);
        }

        return supplierFromGui;
    }
    //endregion

    //region 15. Alert dialogs

    /**
     * Show alerts from Gui if value not valid.
     */
    private void showAlertDialog(int alertDialogToShow, String userMessage) {

        switch (alertDialogToShow) {
            case AppTexts.CANT_SUBMIT_DIALOG -> {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(AppTexts.WRONG_INPUT);
                alert.setHeaderText(AppTexts.USER_MSG_CANT_SUBMIT_DELIVERY);
                alert.setContentText(userMessage);
                alert.show();
            }

            case AppTexts.SUPPLIER_NOT_VALID_DIALOG -> {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(AppTexts.WRONG_INPUT);
                alert.setHeaderText(AppTexts.USER_MSG_SUPPLIERNAME_IS_NOT_VALID);
                alert.setContentText(userMessage);
                alert.showAndWait();
            }
        }
    }
    //endregion
}
/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/10/2022 08:55AM).
 */

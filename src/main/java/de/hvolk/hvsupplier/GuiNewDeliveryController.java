package de.hvolk.hvsupplier;

import de.hvolk.hvsupplier.logic.db.DbManager;
import de.hvolk.hvsupplier.logic.db.DbManagerDelivery;
import de.hvolk.hvsupplier.model.Delivery;
import de.hvolk.hvsupplier.model.Supplier;
import de.hvolk.hvsupplier.settings.AppTexts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

/**
 * <h1>GuiNewDeliveryController</h1>
 * <p>This represents the new order of delivery Gui, it controls all Gui fields and inserts!</p>
 */
public class GuiNewDeliveryController implements Initializable {

    //region 0. Constants
    //endregion

    //region 1. Decl. and Init Attribute
    @FXML
    private ComboBox combSupplierName;

    @FXML
    private TextField txtSupplierPostCode;

    @FXML
    private TextField txtSupplierCity;

    @FXML
    private TextField txtSupplierStreet;

    @FXML
    private TextField txtsupId;

    @FXML
    private ComboBox combFrachtMode;

    @FXML
    private TextField txtMaterial;

    @FXML
    private TextField txtMaterialFrom;

    @FXML
    private TextField txtMaterialTo;

    @FXML
    private TextField txtFrachtArt;

    @FXML
    private Button bntNewDelivery;

    @FXML
    private TextField txtSupplierName;
    //endregion

    //region 2. Constructor replacement initialize

    /**
     * Called to initialize a controller after it's root element has been completely processed.
     * Load Gui after Gui-objects exists and all objects are established, but before view.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.getCollectData();
    }
    //endregion

    //region 3. Get data from the DbTable for first ComboBox this lists all Suppliers by Name
    //          and after select one Supplier, read data & update the Gui address fields.
    private void getCollectData() {

        List<Supplier> supplierList = DbManager.getInstance().getAllSuppliersFromDb();

        ObservableList supplierNames = FXCollections.observableArrayList();

        String[] supplierNameList = new String[supplierList.size()];

        for (int i = 0; i < supplierList.size(); i++) {
            supplierNameList[i] = supplierList.get(i).getSuppilerName();
            supplierNames.add(supplierNameList[i]);
        }
        combSupplierName.setItems(supplierNames);

        setTxtFields();
        setNewDeliveryModes();
    }
    //endregion

    //region 3. set fields to values, when the combobox supplierName are change the selection.
    public void setTxtFields() {

        this.combSupplierName.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {

            String selectSupplierName = (String) newValue;

            List<Supplier> supplierList = DbManager.getInstance().getAllSuppliersFromDb();

            String[] supplierNameList = new String[supplierList.size()];

            for (int i = 0; i < supplierList.size(); i++) {
                supplierNameList[i] = supplierList.get(i).getSuppilerName();
                if (selectSupplierName.equals(supplierNameList[i])) {
                    this.txtsupId.setText(String.valueOf(supplierList.get(i).getId()));
                    this.txtSupplierStreet.setText(supplierList.get(i).getSupplierStreet());
                    this.txtSupplierCity.setText(supplierList.get(i).getSupplierCity());
                    this.txtSupplierPostCode.setText(supplierList.get(i).getSupplierPostCode());
                    this.txtSupplierName.setText(supplierList.get((i)).getSuppilerName());
                }
            }
        });
    }
    //endregion

    //region 4. Set delivery mode, a static array of shipment methods.
    //          Set text field to selected value, for better read the selected data from Gui (IMHO).
    private void setNewDeliveryModes() {

        String[] deliveriesMode = {"Bahn", "LKW", "Schiff", "Transporter", "PKW"};

        ObservableList deliveryMode = FXCollections.observableArrayList(deliveriesMode);

        combFrachtMode.setItems(deliveryMode);
        this.combFrachtMode.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            String selectDeliveryMode = (String) newValue;
            this.txtFrachtArt.setText(selectDeliveryMode);
        });
    }
    //endregion

    //region 5. Input valid check:
    private boolean isNewDeliverPatternValid(String supplierIdToSearchFor) {

        String userMsgSupplierIdPattern = "";
        boolean isSupplierIdPatternValid = true;

        boolean isEmpty = supplierIdToSearchFor.isEmpty();
        boolean isBlank = supplierIdToSearchFor.isBlank();

        //User messages
        if (isEmpty) {
            userMsgSupplierIdPattern = AppTexts.USER_MSG_SUPPLIERNAME_NOT_BE_EMPTY;
            isSupplierIdPatternValid = false;
        }

        if (isSupplierIdPatternValid && isBlank) {
            userMsgSupplierIdPattern = AppTexts.USER_MSG_SUPPLIERNAME_NOT_BE_BLANK;
            isSupplierIdPatternValid = false;
        }

        if (isSupplierIdPatternValid) {

        }

        if (!userMsgSupplierIdPattern.isEmpty()) {
            this.showAlertDialog(AppTexts.SUPPLIER_NOT_VALID_DIALOG, userMsgSupplierIdPattern);
        }

        return isSupplierIdPatternValid;
    }
    //endregion


    //region 6. Get new delivery data from the Gui.
    private Delivery getNewDeliveryFromGui() {
        Delivery deliveryFromGui = null;
        boolean isGuiDataValid = true;

        //     String id = null;

        int supId = Integer.parseInt(this.txtsupId.getText());

        String deliversupplier = this.txtSupplierName.getText();
        String deliverPreferDeliveryMode = this.txtFrachtArt.getText();
        String deliveryMaterial = this.txtMaterial.getText();
        ;
        String deliverFrom = this.txtMaterialFrom.getText();
        String deliveryTo = this.txtMaterialTo.getText();

        //deliveryDays by default = 0 -> should be to reveal the delivery order is new and not started yet.
        String deliveryDays = "0";

        String[] guiData = {

                String.valueOf(supId),
                deliversupplier,
                deliverPreferDeliveryMode,
                deliveryMaterial,
                deliverFrom,
                deliveryTo,
                deliveryDays
        };

        isGuiDataValid = this.isNewDeliverPatternValid(String.valueOf(supId));

        if (isGuiDataValid) {
            deliveryFromGui = new Delivery();

            deliveryFromGui.setIdsup(supId);
            deliveryFromGui.setDeliverySupplier(deliversupplier);
            deliveryFromGui.setDeliveryMode(deliverPreferDeliveryMode);
            deliveryFromGui.setDeliveryContent(deliveryMaterial);
            deliveryFromGui.setDeliveryFrom(deliverFrom);
            deliveryFromGui.setDeliveryTo(deliveryTo);
            deliveryFromGui.setDeliveryDays(Integer.parseInt(deliveryDays));
        }

        return deliveryFromGui;
    }
    //endregion

    //region 7. Send new delivery data from Gui to the database table.
    @FXML
    private void submitNewDelivery() {

        //read new Delivery data from Gui
        Delivery deliveryFromGui = this.getNewDeliveryFromGui();

        if (deliveryFromGui == null) {
            this.showAlertDialog(AppTexts.CANT_SUBMIT_DIALOG, AppTexts.USER_MSG_INPUT_A_VALID_SUPPLIERNAME_FILL_EVERYTHING_IN);
        } else {
            DbManagerDelivery.getInstance().insertDeliveryIntoDbTbl(deliveryFromGui);
        }

        this.combFrachtMode.setValue("");
        this.txtSupplierStreet.setText("");
        this.txtSupplierPostCode.setText("");
        this.txtSupplierCity.setText("");

        this.combSupplierName.setValue("");
        this.txtMaterial.setText("");
        this.txtMaterialFrom.setText("");
        this.txtMaterialTo.setText("");
    }
    //endregion

    //region 8. Alert dialogs
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
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/10/2022 09:10AM).
 */


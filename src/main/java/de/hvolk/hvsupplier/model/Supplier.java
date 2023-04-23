package de.hvolk.hvsupplier.model;

import de.hvolk.hvsupplier.settings.AppTexts;

/**
 * Model supplier data, define the data construction of supplier data
 */

public class Supplier {

    //region 0. Default values
    //endregion

    //region 1.  Decl and Init Attribute
    private int id;
    private String suppilerName;
    private String supplierStreet;
    private String supplierPostCode;
    private String supplierCity;
    private String prefertDeliveryMode;
    private String deliveryMaterial;
    private String averageDeliveryDays;
    //endregion

    //region 2. Constructor

    /**
     * Standard constructor direct initialize all attributs with own standard values
     */
    public Supplier() {
        this.id = AppTexts.DEFAULT_VALUE_INTEGER;
        this.suppilerName = AppTexts.DEFAULT_VALUE_STRINGS;
        this.supplierStreet = AppTexts.DEFAULT_VALUE_STRINGS;
        this.supplierPostCode = AppTexts.DEFAULT_VALUE_STRINGS;
        this.supplierCity = AppTexts.DEFAULT_VALUE_STRINGS;
        this.prefertDeliveryMode = AppTexts.DEFAULT_VALUE_STRINGS;
        this.deliveryMaterial = AppTexts.DEFAULT_VALUE_STRINGS;
        this.averageDeliveryDays = AppTexts.DEFAULT_VALUE_STRINGS;
    }

    /**
     * Overload constructor direct set the Main attribute this class
     */
    public Supplier(String suppilerName, String supplierCity, String supplierPostCode) {

        //Call standard constructor to set all attribute with standard values
        this();

        this.suppilerName = suppilerName;
        this.supplierCity = supplierCity;
        this.supplierPostCode = supplierPostCode;
    }
    //endregion

    //region 3. Getter and Setter:
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuppilerName() {
        return suppilerName;
    }

    public void setSuppilerName(String suppilerName) {
        this.suppilerName = suppilerName;
    }

    public String getSupplierStreet() {
        return supplierStreet;
    }

    public void setSupplierStreet(String supplierStreet) {
        this.supplierStreet = supplierStreet;
    }

    public String getSupplierPostCode() {
        return supplierPostCode;
    }

    public void setSupplierPostCode(String supplierPostCode) {
        this.supplierPostCode = supplierPostCode;
    }

    public String getSupplierCity() {
        return supplierCity;
    }

    public void setSupplierCity(String supplierCity) {
        this.supplierCity = supplierCity;
    }

    public String getPrefertDeliveryMode() {
        return prefertDeliveryMode;
    }

    public void setPrefertDeliveryMode(String prefertDeliveryMode) {
        this.prefertDeliveryMode = prefertDeliveryMode;
    }

    public String getDeliveryMaterial() {
        return deliveryMaterial;
    }

    public void setDeliveryMaterial(String deliveryMaterial) {
        this.deliveryMaterial = deliveryMaterial;
    }

    public String getAverageDeliveryDays() {
        return averageDeliveryDays;
    }

    public void setAverageDeliveryDays(String averageDeliveryDays) {
        this.averageDeliveryDays = averageDeliveryDays;
    }
    //endregion

    //region 4. get minimal information for supplier - Listview.
    public String getMinimalInformation() {
        return this.suppilerName + " - " + this.supplierPostCode + " - " + this.supplierCity;
    }
    //endregion

    //region 5. toString
    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", suppilerName='" + suppilerName + '\'' +
                ", supplierStreet='" + supplierStreet + '\'' +
                ", supplierPostCode='" + supplierPostCode + '\'' +
                ", supplierCity='" + supplierCity + '\'' +
                ", prefertDeliveryMode='" + prefertDeliveryMode + '\'' +
                ", deliveryMaterial='" + deliveryMaterial + '\'' +
                ", averageDeliveryDays='" + averageDeliveryDays + '\'' +
                '}';
    }
    //endregion
}
/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/10/2022 10:20AM).
 */

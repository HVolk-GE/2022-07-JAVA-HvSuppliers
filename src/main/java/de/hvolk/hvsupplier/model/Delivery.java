package de.hvolk.hvsupplier.model;

import de.hvolk.hvsupplier.settings.AppTexts;

/**
 * Model delivery data, define the data construction of delivery data
 */
public class Delivery {

    //region 0. Default values
    //endregion

    //region 1. Declare and Init Attribute
    private int id;

    private int idsup;

    private String deliverySupplier;

    private String deliveryMode;

    private String deliveryContent;

    private String deliveryFrom;

    private String deliveryTo;

    private int deliveryDays;
    //endregion

    //region 2. Constructor

    /**
     * Standard constructor direct initialize all attributs with own standard values
     */
    public Delivery() {
        this.id = AppTexts.DEFAULT_VALUE_INTEGER;
        this.idsup = AppTexts.DEFAULT_VALUE_INTEGER;
        this.deliverySupplier = AppTexts.DEFAULT_VALUE_STRINGS;
        this.deliveryMode = AppTexts.DEFAULT_VALUE_STRINGS;
        this.deliveryContent = AppTexts.DEFAULT_VALUE_STRINGS;
        this.deliveryFrom = AppTexts.DEFAULT_VALUE_STRINGS;
        this.deliveryTo = AppTexts.DEFAULT_VALUE_STRINGS;
        this.deliveryDays = AppTexts.DEFAULT_VALUE_INTEGER;
    }

    /**
     * Overload constructor direct set the Main attribute this class
     */
    public Delivery(String deliverySupplier, String deliveryFrom, String deliveryTo) {
        this();

        this.deliverySupplier = deliverySupplier;
        this.deliveryFrom = deliveryFrom;
        this.deliveryTo = deliveryTo;
    }
    //endregion

    //region 3. Getter and Setter:
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdsup() {
        return idsup;
    }

    public void setIdsup(int idsup) {
        this.idsup = idsup;
    }

    public String getDeliverySupplier() {
        return deliverySupplier;
    }

    public void setDeliverySupplier(String deliverySupplier) {
        this.deliverySupplier = deliverySupplier;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public String getDeliveryContent() {
        return deliveryContent;
    }

    public void setDeliveryContent(String deliveryContent) {
        this.deliveryContent = deliveryContent;
    }

    public String getDeliveryFrom() {
        return deliveryFrom;
    }

    public void setDeliveryFrom(String deliveryFrom) {
        this.deliveryFrom = deliveryFrom;
    }

    public String getDeliveryTo() {
        return deliveryTo;
    }

    public void setDeliveryTo(String deliveryTo) {
        this.deliveryTo = deliveryTo;
    }

    public int getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(int deliveryDays) {
        this.deliveryDays = deliveryDays;
    }
    //endregion

    //region 4. get minimal information for Deliveries - Listview.
    public String getMinimalDeliveriesInformation() {
        return this.deliverySupplier + " - " + this.deliveryMode + " - " + this.deliveryFrom + " - " + this.deliveryTo + " - " + this.deliveryContent + " - " + this.deliveryDays;
    }
    //endregion

    //region 5. toString
    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", idsup=" + idsup +
                ", deliverySupplier='" + deliverySupplier + '\'' +
                ", delieveryMode='" + deliveryMode + '\'' +
                ", deliveryContent='" + deliveryContent + '\'' +
                ", deliveryFrom='" + deliveryFrom + '\'' +
                ", deliveryTo='" + deliveryTo + '\'' +
                ", deliveryDays=" + deliveryDays +
                '}';
    }
    //endregion
}
/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/10/2022 09:55AM).
 */
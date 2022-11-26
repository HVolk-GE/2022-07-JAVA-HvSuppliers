package de.hvolk.hvsupplier.model;

import de.hvolk.hvsupplier.settings.CsvFileConfig;

/**
 * Model for contact data, define the data construction of contacts data, data reading by Csv-File, this file is profited by an MS-Exchange-Server (provided by customer).
 */
public class Contact {

    //region 0. constants
    private static final String DEFAULT_VALUE_STRINGS = ">noValueSetYet<";
    private static final int    DEFAULT_VALUE_INTEGER = -1;
    private static final String SPLIT_CHAR = ";";
    //endregion


    //region 1. Decl and Init Attribute
    private int id;
    private String contactfacilityName;
    private String contactName;
    private String contactstreetName;
    private String contactAddress;
    private String contactPhoneNumber;
    private String contactMobileNumber;
    private String contactEmail;
    //endregion

    //region 2. constructors
    /**
     * Standard constructor
     */
    public Contact(){
        this.id = DEFAULT_VALUE_INTEGER;
        this.contactfacilityName = DEFAULT_VALUE_STRINGS;
        this.contactName = DEFAULT_VALUE_STRINGS;
        this.contactstreetName = DEFAULT_VALUE_STRINGS;
        this.contactAddress = DEFAULT_VALUE_STRINGS;
        this.contactPhoneNumber = DEFAULT_VALUE_STRINGS;
        this.contactMobileNumber = DEFAULT_VALUE_STRINGS;
        this.contactEmail = DEFAULT_VALUE_STRINGS;
    }

    public String getAllAttributesAsCsvLine(){
        return this.id + SPLIT_CHAR
                +this.contactfacilityName + SPLIT_CHAR
                + this.contactName + SPLIT_CHAR
                + this.contactstreetName + SPLIT_CHAR
                + this.contactAddress + SPLIT_CHAR
                + this.contactPhoneNumber + SPLIT_CHAR
                + this.contactMobileNumber
                + this.contactEmail+ "\n";
    }

    public void setAllAttributesFromCsvLine(String csvLine){
        String[] allAttributes = csvLine.split(SPLIT_CHAR);

        this.id = Integer.parseInt(allAttributes[CsvFileConfig.INDEX_CONTACT_ID]);
        this.contactfacilityName = allAttributes[CsvFileConfig.INDEX_CONTACT_FACILITY_NAME];
        this.contactName = allAttributes[CsvFileConfig.INDEX_CONTACT_NAME];
        this.contactstreetName = allAttributes[CsvFileConfig.INDEX_CONTACT_STREET];
        this.contactAddress = allAttributes[CsvFileConfig.INDEX_CONTACT_ADDRESS];
        this.contactPhoneNumber = allAttributes[CsvFileConfig.INDEX_CONTACT_PHONE_NUMBER];
        this.contactMobileNumber = allAttributes[CsvFileConfig.INDEX_CONTACT_MOBILE_NUMBER];
        this.contactEmail = allAttributes[CsvFileConfig.INDEX_CONTACT_EMAIL];
    }
    //endregion

    //region 3. overload constructor
    public Contact(String contactfacilityName, String contactstreetName, String contactAddress){
        this();

        this.contactfacilityName = contactfacilityName;
        this.contactstreetName = contactstreetName;
        this.contactAddress = contactAddress;
    }
    //endregion

    //region 4. Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactfacilityName() {
        return contactfacilityName;
    }

    public void setContactfacilityName(String contactfacilityName) {
        this.contactfacilityName = contactfacilityName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactstreetName() {
        return contactstreetName;
    }

    public void setContactstreetName(String contactstreetName) {
        this.contactstreetName = contactstreetName;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactMobileNumber() {
        return contactMobileNumber;
    }

    public void setContactMobileNumber(String contactMobileNumber) {
        this.contactMobileNumber = contactMobileNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    //endregion

    //region 4. toString
    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", contactfacilityName='" + contactfacilityName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactstreetName='" + contactstreetName + '\'' +
                ", contactAddress='" + contactAddress + '\'' +
                ", contactPhoneNumber='" + contactPhoneNumber + '\'' +
                ", contactMobileNumber='" + contactMobileNumber + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                '}';
    }
    //endregion
}
/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/10/2022 09:50AM).
 */

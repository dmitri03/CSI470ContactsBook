//Object Model file, defining the Contact class and its fields, and defininf the class getters and setters for earch field.

package com.example.csi460_midterm;

public class ContactModal {

    // variables for our contactname,
    // description, tracks and duration, id.
    private String contactName;
    private String contactNumber;
    private String contactCompany;
    private String contactAddress;
    private String contactEmail;
    private int id;

    // creating getter and setter methods
    public String getContactName() { return contactName; }

    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    public String getContactNumber()
    {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    public String getContactEmail() { return contactEmail; }

    public void setContactEmail(String contactEmail)
    {
        this.contactEmail = contactEmail;
    }

    public String getContactAddress()
    {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress)
    {
        this.contactAddress = contactAddress;
    }
    public String getContactCompany()
    {
        return contactCompany;
    }

    public void setContactCompany(String contactCompany)
    {
        this.contactCompany = contactCompany;
    }


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    // constructor
    public ContactModal(String contactName,
                       String contactNumber,
                       String contactAddress,
                       String contactEmail,
                        String ContactCompany)
    {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.contactEmail= contactEmail;
        this.contactAddress = contactAddress;
        this.contactCompany = contactCompany;
    }
}

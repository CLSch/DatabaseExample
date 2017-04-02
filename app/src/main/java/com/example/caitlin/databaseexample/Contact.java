package com.example.caitlin.databaseexample;

import java.io.Serializable;

/**
 * Created by Caitlin on 02-04-17.
 */

public class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private int _id;


    /**
     * constructor for Contacts from database
     */
    public Contact(String contactName, String contactNr) {
        name = contactName;
        phoneNumber = contactNr;
    }

    public Contact(String contactName, String contactNr, int contactID) {
        name = contactName;
        phoneNumber = contactNr;
        _id = contactID;
    }

    /** get the Contact Number */
    public String getNumber() {
        return phoneNumber;
    }

    /** get the Contact Name */
    public String getName() {
        return name;
    }

    /** set the Contact Name */
    public void setName(String newName) {
        name = newName;
    }

    /** set the Contact Number */
    public void setNumber(String newNumber) {
        phoneNumber = newNumber;
    }


    public int getID() {
        return _id;
    }

    public void setID(int iD) {
        _id = iD;
    }
}

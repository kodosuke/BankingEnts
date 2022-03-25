package org.code.models;

import org.code.enums.Gender;

public class Customer {

    int customerID;
    String customerName;
    String contact;
    String dateOfBirth;
    Gender gender;
    String password;

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer() {
    }

    public Customer(String customerName, String contact, String dateOfBirth, Gender gender, String password) {
        this.customerName = customerName;
        this.contact = contact;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.password = password;
    }

    public Customer(int customerID, String customerName, String contact, String dateOfBirth, Gender gender, String password) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.contact = contact;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerID=" + customerID +
                ", customerName='" + customerName + '\'' +
                ", contact='" + contact + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                '}';
    }
}

package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String address;
  private final String mobilePhoneNumber;
  private final String email;

  public ContactData(String FirstName, String LastName, String Address, String mobilePhoneNumber, String email) {
    this.firstName = FirstName;
    this.lastName = LastName;
    this.address = Address;
    this.mobilePhoneNumber = mobilePhoneNumber;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getMobilePhoneNumber() {
    return mobilePhoneNumber;
  }

  public String getEmail() {
    return email;
  }
}

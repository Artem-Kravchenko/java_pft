package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String address;
  private final String mobilePhoneNumber;
  private final String email;
  private String group;

  public ContactData(String FirstName, String LastName, String Address, String mobilePhoneNumber, String email, String group) {
    this.firstName = FirstName;
    this.lastName = LastName;
    this.address = Address;
    this.mobilePhoneNumber = mobilePhoneNumber;
    this.email = email;
    this.group = group;
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

  public String getGroup() {
    return group;
  }
}

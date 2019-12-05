package com.addressbook;

import java.io.IOException;

public interface IAddressBook {

    public int addPerson(String firstName, String lastName, String phoneNum, String state, String city, String address, int zipcode) throws IOException;
}

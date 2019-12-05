package com.addressbook;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImplAddressBook implements IAddressBook {

    String fileName = "/home/admin1/Desktop/AddressBook/AddressBook1.json";
    static ObjectMapper mapper = new ObjectMapper();
    ArrayList<PersonModel> peoples = new ArrayList<>();
    AddressBookModel addressBookModel = new AddressBookModel();

    public void addPerson(String firstName, String lastName, String phoneNum, String state, String city, String address, int zipcode) throws IOException {
        if(fileName.length()!=0)
            readFromJson(fileName);
        if(!addressBookModel.getPersonModels().isEmpty())
            peoples.addAll(addressBookModel.getPersonModels());

        PersonModel personModel = new PersonModel();
        AddressModel addressModel = new AddressModel();

        personModel.setFirstName(firstName);
        personModel.setLastName(lastName);
        personModel.setPhoneNum(phoneNum);
        addressModel.setState(state);
        addressModel.setCity(city);
        addressModel.setAddress(address);
        addressModel.setZipcode(zipcode);

        personModel.setAddress(addressModel);
        peoples.add(personModel);
        addressBookModel.setPersonModels(peoples);

        writeToJsonFile(addressBookModel);
    }

    public void writeToJsonFile(AddressBookModel addressBookModel) throws IOException {
        mapper.writeValue(new File(fileName), addressBookModel);
    }

    public void readFromJson(String fileName) throws IOException {
        addressBookModel =  mapper.readValue(new File(fileName),AddressBookModel.class);
    }


}

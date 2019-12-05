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
    PersonModel personModel = new PersonModel();
    AddressModel addressModel = new AddressModel();

    public int addPerson(String firstName, String lastName, String phoneNum, String state, String city, String address, int zipcode) throws IOException {
        if (fileName.length() != 0)
            readFromJson(fileName);
        if (!addressBookModel.getPersonModels().isEmpty())
            peoples.addAll(addressBookModel.getPersonModels());
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
        return 1;
    }

    public void writeToJsonFile(AddressBookModel addressBookModel) throws IOException {
        mapper.writeValue(new File(fileName), addressBookModel);
    }

    public void readFromJson(String fileName) throws IOException {
        addressBookModel = mapper.readValue(new File(fileName), AddressBookModel.class);
    }

    public int searchPersonByPhoneNumber(String phoneNum) throws IOException {
        readFromJson(fileName);
        int index = 0;
        String phoneNum1 = phoneNum;
        boolean isFoundPerson = false;
        for (int i = 0; i < addressBookModel.getPersonModels().size(); i++) {
            phoneNum = addressBookModel.getPersonModels().get(i).getPhoneNum();
            if (phoneNum1.equals(phoneNum)) {
                isFoundPerson = true;
                index = i;
                break;
            }
        }
        if (isFoundPerson == true) {
            System.out.print("Person exist");
        } else {
            System.out.print("Person Not Exist");
        }
        return index;
    }

    public int editForPhoneNumber(String oldNumber, String newNumber) throws IOException {
        readFromJson(fileName);
        int index = searchPersonByPhoneNumber(oldNumber);
        addressBookModel.getPersonModels().get(index).setPhoneNum(newNumber);
        writeToJsonFile(addressBookModel);
        return 1;
    }

    public int editForAddress(String phoneNum ,String state, String city, String address, int zipcode) throws IOException {
        readFromJson(fileName);
        int index = searchPersonByPhoneNumber(phoneNum);
        addressBookModel.getPersonModels().get(index).getAddress().setState(state);
        addressBookModel.getPersonModels().get(index).getAddress().setCity(city);
        addressBookModel.getPersonModels().get(index).getAddress().setAddress(address);
        addressBookModel.getPersonModels().get(index).getAddress().setZipcode(zipcode);
        writeToJsonFile(addressBookModel);
        return 1;
    }
}

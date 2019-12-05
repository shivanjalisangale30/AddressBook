package com.addressbook;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public int editForAddress(String phoneNum, String state, String city, String address, int zipcode) throws IOException {
        readFromJson(fileName);
        int index = searchPersonByPhoneNumber(phoneNum);
        addressBookModel.getPersonModels().get(index).getAddress().setState(state);
        addressBookModel.getPersonModels().get(index).getAddress().setCity(city);
        addressBookModel.getPersonModels().get(index).getAddress().setAddress(address);
        addressBookModel.getPersonModels().get(index).getAddress().setZipcode(zipcode);
        writeToJsonFile(addressBookModel);
        return 1;
    }

    public int deletePerson(String phoneNum) throws IOException {
        readFromJson(fileName);
        int index = searchPersonByPhoneNumber(phoneNum);
        addressBookModel.getPersonModels().remove(index);
        writeToJsonFile(addressBookModel);
        return 1;
    }

    public <T extends Comparable<T>> int sortFunctionForPersonModel(String methodName) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        readFromJson(fileName);
        for (int i = 0; i < addressBookModel.getPersonModels().size() - 1; i++) {
            for (int j = 0; j < addressBookModel.getPersonModels().size() - i - 1; j++) {
                Class cls1 = addressBookModel.getPersonModels().get(j).getClass();
                Method methodcall = cls1.getDeclaredMethod(methodName);
                T value1 = (T) methodcall.invoke(addressBookModel.getPersonModels().get(j));
                Class cls2 = addressBookModel.getPersonModels().get(j + 1).getClass();
                Method methodcall1 = cls2.getDeclaredMethod(methodName);
                T value2 = (T) methodcall.invoke(addressBookModel.getPersonModels().get(j + 1));
                if (value1.compareTo(value2) > 0) {
                    PersonModel tempObj = addressBookModel.getPersonModels().get(j);
                    addressBookModel.getPersonModels().set(j, addressBookModel.getPersonModels().get(j + 1));
                    addressBookModel.getPersonModels().set(j + 1, tempObj);
                }
            }
        }
        writeToJsonFile(addressBookModel);
        return 1;
    }

    public <T extends Comparable<T>> int sortFunctionForAddressModel(String methodName) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        readFromJson(fileName);
        for (int i = 0; i < addressBookModel.getPersonModels().size() - 1; i++) {
            for (int j = 0; j < addressBookModel.getPersonModels().size() - i - 1; j++) {
                Class cls1 = addressBookModel.getPersonModels().get(j).getAddress().getClass();
                Method methodcall = cls1.getDeclaredMethod(methodName);
                T value1 = (T) methodcall.invoke(addressBookModel.getPersonModels().get(j).getAddress());
                Class cls2 = addressBookModel.getPersonModels().get(j + 1).getAddress().getClass();
                Method methodcall1 = cls2.getDeclaredMethod(methodName);
                T value2 = (T) methodcall.invoke(addressBookModel.getPersonModels().get(j + 1).getAddress());
                if (value1.compareTo(value2) > 0) {
                    PersonModel tempObj = addressBookModel.getPersonModels().get(j);
                    addressBookModel.getPersonModels().set(j, addressBookModel.getPersonModels().get(j + 1));
                    addressBookModel.getPersonModels().set(j + 1, tempObj);
                }
            }
        }
        writeToJsonFile(addressBookModel);
        return 1;
    }

    public int printInFormat() throws IOException {
        readFromJson(fileName);
        System.out.println("FirstName  LastName  PhoneNumber  State  City  Address  Zipcode   ");
        for (int i = 0; i < addressBookModel.getPersonModels().size() - 1; i++) {
            System.out.println(addressBookModel.getPersonModels().get(i).getFirstName()+
                               "\t\t"+addressBookModel.getPersonModels().get(i).getLastName()+
                               "\t"+addressBookModel.getPersonModels().get(i).getPhoneNum()+
                               "\t"+addressBookModel.getPersonModels().get(i).getAddress().getState()+
                               "\t"+addressBookModel.getPersonModels().get(i).getAddress().getCity()+
                               "\t"+addressBookModel.getPersonModels().get(i).getAddress().getAddress()+
                                "\t\t"+addressBookModel.getPersonModels().get(i).getAddress().getZipcode());
        }
        return 1;

    }
}

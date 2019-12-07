package com.addressbook;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ImplAddressBook {

    static ObjectMapper mapper = new ObjectMapper();
    ArrayList<PersonModel> peoples = new ArrayList<>();

    AddressBookModel addressBookModel = new AddressBookModel();
    PersonModel personModel = new PersonModel();
    AddressModel addressModel = new AddressModel();

    public int addPerson(File fileName, String firstName, String lastName, String phoneNum, String state, String city, String address, int zipcode) throws IOException {
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

        writeToJsonFile(addressBookModel, fileName);
        return 1;
    }

    public void writeToJsonFile(AddressBookModel addressBookModel, File fileName) throws IOException {
        mapper.writeValue(fileName, addressBookModel);
    }

    public void readFromJson(File fileName) throws IOException {
        addressBookModel = mapper.readValue(fileName, AddressBookModel.class);
    }

    public int searchPersonByPhoneNumber(File fileName, String phoneNum) throws IOException {
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
        return index;
    }

    public boolean editForPhoneNumber(File fileName, String oldNumber, String newNumber) throws IOException {
        readFromJson(fileName);
        int index = searchPersonByPhoneNumber(fileName, oldNumber);
        if (index == 0) {
            String number = addressBookModel.getPersonModels().get(0).getPhoneNum();
            if (number.equals(oldNumber)) {
                addressBookModel.getPersonModels().get(index).setPhoneNum(newNumber);
                writeToJsonFile(addressBookModel, fileName);
            } else
                return false;
        }
        addressBookModel.getPersonModels().get(index).setPhoneNum(newNumber);
        writeToJsonFile(addressBookModel, fileName);
        return true;
    }

    public Boolean editForAddress(File fileName, String phoneNum, String state, String city, String address, int zipcode) throws IOException {
        readFromJson(fileName);
        int index = searchPersonByPhoneNumber(fileName, phoneNum);
        if (index == 0) {
            String number = addressBookModel.getPersonModels().get(0).getPhoneNum();
            if (number.equals(phoneNum)) {
                addressBookModel.getPersonModels().get(index).getAddress().setState(state);
                addressBookModel.getPersonModels().get(index).getAddress().setCity(city);
                addressBookModel.getPersonModels().get(index).getAddress().setAddress(address);
                addressBookModel.getPersonModels().get(index).getAddress().setZipcode(zipcode);
                writeToJsonFile(addressBookModel, fileName);
            } else
                return false;
        }
        addressBookModel.getPersonModels().get(index).getAddress().setState(state);
        addressBookModel.getPersonModels().get(index).getAddress().setCity(city);
        addressBookModel.getPersonModels().get(index).getAddress().setAddress(address);
        addressBookModel.getPersonModels().get(index).getAddress().setZipcode(zipcode);
        writeToJsonFile(addressBookModel, fileName);
        return true;
    }

    public boolean deletePerson(File fileName, String phoneNum) throws IOException {
        readFromJson(fileName);
        int index = searchPersonByPhoneNumber(fileName, phoneNum);
        if (index == 0) {
            String number = addressBookModel.getPersonModels().get(0).getPhoneNum();
            if (number.equals(phoneNum)) {
                addressBookModel.getPersonModels().remove(index);
                writeToJsonFile(addressBookModel, fileName);
            } else
                return false;
        }
        addressBookModel.getPersonModels().remove(index);
        writeToJsonFile(addressBookModel, fileName);
        return true;
    }

    public <T extends Comparable<T>> boolean sortFunctionForPersonModel(File fileName, String methodName) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (fileName.length() == 0)
            return false;
        else
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
        writeToJsonFile(addressBookModel, fileName);
        return true;
    }

    public <T extends Comparable<T>> boolean sortFunctionForAddressModel(File fileName, String methodName) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (fileName.length() == 0)
            return false;
        else
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
        writeToJsonFile(addressBookModel, fileName);
        return true;
    }

    public boolean printInFormat(File fileName) throws IOException {
        if (fileName.length() == 0)
            return false;
        else
            readFromJson(fileName);
        System.out.println("FirstName  LastName  PhoneNumber  State  City  Address  Zipcode   ");
        for (int i = 0; i < addressBookModel.getPersonModels().size(); i++) {
            System.out.println(addressBookModel.getPersonModels().get(i).getFirstName() +
                    "\t\t" + addressBookModel.getPersonModels().get(i).getLastName() +
                    "\t" + addressBookModel.getPersonModels().get(i).getPhoneNum() +
                    "\t" + addressBookModel.getPersonModels().get(i).getAddress().getState() +
                    "\t" + addressBookModel.getPersonModels().get(i).getAddress().getCity() +
                    "\t" + addressBookModel.getPersonModels().get(i).getAddress().getAddress() +
                    "\t\t" + addressBookModel.getPersonModels().get(i).getAddress().getZipcode());
        }
        return true;
    }


    public boolean addressBookCreate(String newAddressBook) throws IOException {
        File file = new File("/home/admin1/Desktop/AddressBook/" + newAddressBook + ".json");
        boolean flag = file.createNewFile();
        if (flag) {
            return true;
        } else
            return false;
    }

    public boolean addressBookOpen(String openAddressBook) throws IOException {
        Files.list(Paths.get("/home/admin1/Desktop/AddressBook")).filter(path -> path.toString().endsWith(".json")).forEach(System.out::println);
        File f = new File("/home/admin1/Desktop/AddressBook/" + openAddressBook + ".json");
        if (f.exists()) {
            return true;
        } else
            return false;
    }

    public boolean saveData(File fileName) throws IOException {
        if (fileName.getAbsolutePath().endsWith(".json")) {
            if (fileName.length() != 0) {
                readFromJson(fileName);
                writeToJsonFile(addressBookModel, fileName);
                return true;
            }
        }
        return false;
    }

    public int saveAs(File fileName, String nameOfAddressBook) throws IOException {
        readFromJson(fileName);
        File file = new File("/home/admin1/Desktop/AddressBook/" + nameOfAddressBook + ".json");
        boolean flag = file.createNewFile();
        if (flag) {
            System.out.println("File has been created successfully...");
        } else {
            System.out.println("File is already exist");
        }
        writeToJsonFile(addressBookModel, file);
        return 1;
    }
}

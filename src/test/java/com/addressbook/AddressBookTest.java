package com.addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class AddressBookTest {

    File Addressbook1 = new File("/home/admin1/Desktop/AddressBook/src/test/resources/AddressBook1.json");


    @Test
    public void givenAddressBook_wantToAddNewPersonInformation_shouldGetAdded() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.addPerson(Addressbook1,"Saeesh", "Phatak", "9998885551", "Bihar", "Begusrai", "begusarai", 888222);
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_ifAddedCompleteInformation_shouldRetrunTrue() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.addPerson(Addressbook1,"Jugraj", "Chakane", "022-2456987", "Maharashtra", "Mumbai", "Dadar", 123456);
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_evenifAddedIncompleteInformation_shouldReturntrue() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.addPerson(Addressbook1,"Harsh", "Gurav", null, "Kashmir", null, null, 0);
       Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_wantToSearchExistPersonByPhoneNumber_shouldReturnTrue() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.searchPersonByPhoneNumber(Addressbook1,"022-2456987");
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_wantToSearchNotExistPersonByPhoneNumber_shouldReturnFalse() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.searchPersonByPhoneNumber(Addressbook1,"0123");
        Assert.assertFalse(String.valueOf(result),false);
    }

    @Test
    public void givenAddressBook_wantToEditInformationForPhoneNumber() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.editForPhoneNumber(Addressbook1,"9998885551","9898989898");
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_wantToEditInformationForAddress() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.editForAddress(Addressbook1,"9898989898", "Hydrabad", "Hydrabad", "aaa", 456456);
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_wantToDeletePersonInformation_shouldGetDelete() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.deletePerson(Addressbook1,"9876543212");
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_wantToSortByLastName_shouldGetSort() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.sortFunctionForPersonModel(Addressbook1,"getLastName");
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_wantToSortByZipCode_shouldGetSort() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.sortFunctionForAddressModel(Addressbook1,"getZipcode");
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_wantToPrintFormat_shouldGetPrint() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.printInFormat(Addressbook1,"AddressBook1");
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenOption_forCreatingAddressBook_shouldGetCreate() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.addressBookCreate("AddressBook1");
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenOption_forOpenAddressBook_sholdGetOpen() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.addressBookOpen("AddressBook1");
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_wantToSaveData_sholudGetSaved() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.saveData(Addressbook1);
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_OptionSaveAs_shouldGetChangedFileType() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int reslt = implAddressBook.saveAs(Addressbook1,"AddressBook3");
        Assert.assertEquals(1,reslt);
    }
}

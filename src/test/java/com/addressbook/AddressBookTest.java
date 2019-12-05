package com.addressbook;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class AddressBookTest {

    @Test
    public void givenAddressBook_wantToAddNewPersonInformation_shouldGetAdded() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.addPerson("Sunil", "Sangale", "9876543212", "Rajasthan", "Aajmer", "aajmer", 456789);
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_ifAddedCompleteInformation_shouldRetrunTrue() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.addPerson("Shivanjali", "Sangale", "9876543211", "Maharashtra", "Mumbai", "Dadar", 123456);
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_evenifAddedIncompleteInformation_shouldReturntrue() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.addPerson("Harsh", "Gurav", null, null, null, null, 0);
       Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_wantToSearchExistPersonByPhoneNumber_shouldReturnTrue() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.searchPersonByPhoneNumber("9876543211");
        Assert.assertEquals(0,result);
    }

    @Test
    public void givenAddressBook_wantToSearchNotExistPersonByPhoneNumber_shouldReturnFalse() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.searchPersonByPhoneNumber("123456789");
        Assert.assertFalse(String.valueOf(result),false);
    }

    @Test
    public void givenAddressBook_wantToEditInformationForPhoneNumber() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.editForPhoneNumber("9876543211","9898989898");
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_wantToEditInformationForAddress() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.editForAddress("9876543212", "MH", "Nagpur", "aaa", 55555);
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_wantToDeletePersonInformation_shouldGetDelete() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.deletePerson("9898989898");
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_wantToSortByLastName_shouldGetSort() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.sortFunctionForPersonModel("getLastName");
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_wantToSortByZipCode_shouldGetSort() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.sortFunctionForAddressModel("getZipcode");
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_wantToPrintFormat_shouldGetPrint() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.printInFormat();
        Assert.assertEquals(1,result);
    }
}

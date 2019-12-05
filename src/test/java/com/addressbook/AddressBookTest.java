package com.addressbook;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
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
        int result = implAddressBook.searchPersonByPerson("9876543211");
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenAddressBook_wantToSearchNotExistPersonByPhoneNumber_shouldReturnFalse() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = implAddressBook.searchPersonByPerson("123456789");
        Assert.assertFalse(String.valueOf(result),false);
    }


}

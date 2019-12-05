package com.addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class AddressBookTest {

    @Test
    public void givenAddressBook_wantToAddNewPersonInformation_shouldGetAdded() throws IOException {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        implAddressBook.addPerson("Saeesh", "Apte", "9874563215", "Jammu", "Manali", "AAA", 500123);
    }

}

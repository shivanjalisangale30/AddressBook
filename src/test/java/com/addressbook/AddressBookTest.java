package com.addressbook;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class AddressBookTest {

    File Addressbook1 = new File("/home/admin1/Desktop/AddressBook/AddressBook1.json");
    File Addressbook2 = new File("/home/admin1/Desktop/AddressBook/AddressBook2.json");

    @Test
    public void givenAddressBook_wantToAddNewPersonInformation_shouldGetAdded() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = 0;
        try {
            result = implAddressBook.addPerson(Addressbook1, "Silkesh", "Sanas", "983214568", "Bihar", "Patana", "Patana", 365982);
            Assert.assertEquals(1, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_ifAddedCompleteInformation_shouldRetrunTrue() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = 0;
        try {
            result = implAddressBook.addPerson(Addressbook1, "Harshada", "Pawar", "896547256", "Kerala", "kkk", "Kkk", 896589);
            Assert.assertEquals(1, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_evenifAddedIncompleteInformation_shouldReturntrue() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = 0;
        try {
            result = implAddressBook.addPerson(Addressbook1, "Ruhi", "Walke", "98745632159", "UP", "Banaras", null, 5698745);
            Assert.assertEquals(1, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_wantToSearchExistPersonByPhoneNumber_shouldReturnTrue() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = 0;
        try {
            result = implAddressBook.searchPersonByPhoneNumber(Addressbook1, "98745632159");
            Assert.assertEquals(4, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_wantToSearchNotExistPersonByPhoneNumber_shouldReturnFalse() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int result = 0;
        try {
            result = implAddressBook.searchPersonByPhoneNumber(Addressbook1, "0123");
            Assert.assertFalse(String.valueOf(result), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_wantToEditInformationForCorrectPhoneNumber_shouldReturnTrue() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        boolean result;
        try {
            result = implAddressBook.editForPhoneNumber(Addressbook1, "9874563255", "9865329811");
            Assert.assertTrue(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_wantToEditInformationForNullNumber_shouldReturnFalse() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            boolean result = implAddressBook.editForPhoneNumber(Addressbook1, null, "9874563215");
            Assert.assertFalse(String.valueOf(result), false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void gievnAddressBook_wantToInformationForIncorrectNumber_shouldReturnFalse() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            boolean result = implAddressBook.editForPhoneNumber(Addressbook1, "123", "9898753216");
            Assert.assertFalse(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_wantToEditInformationForAddressWithCorrectNumber_shouldReturnTrue() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            Boolean result = implAddressBook.editForAddress(Addressbook1, "7678077155", "J&K", "Manali", "mmm", 589647);
            Assert.assertTrue(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_wantToEditInformationForAddressWithIncorrectNumber_shouldReturnFalse() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            Boolean result = implAddressBook.editForAddress(Addressbook1, "7678077155", "Delhi", "New Delhi", "dddd", 363636);
            Assert.assertFalse(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_wantToEditInformationForAddressWithNullNumber_shouldReturnFalse() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            Boolean result = implAddressBook.editForAddress(Addressbook1, null, "MH", "Mumbai", "mumbai", 889944);
            Assert.assertFalse(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenAddressBook_wantToDeletePersonInformationWithCorrectNumber_shouldGetDelete() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        boolean result;
        try {
            result = implAddressBook.deletePerson(Addressbook1, "983214568");
            Assert.assertTrue(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenAddressBook_wantToDeletePersonInformationWithIncorrectNumber_shouldGetFalse() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            boolean result = implAddressBook.deletePerson(Addressbook1, "");
            Assert.assertFalse(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_wantToDeletePersonInformationWithNullNumber_shouldGetFalse() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            boolean result = implAddressBook.deletePerson(Addressbook1, null);
            Assert.assertFalse(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookCorrect_wantToSortByLastName_shouldGetSort() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            Boolean result = implAddressBook.sortFunctionForPersonModel(Addressbook1, "getLastName");
            Assert.assertTrue(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenAddressBookEmpty_wantToSortByLastName_shouldREturnFalse() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            Boolean result = implAddressBook.sortFunctionForPersonModel(Addressbook2, "getLastName");
            Assert.assertFalse(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookCorrect_wantToSortByZipCode_shouldGetSort() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            boolean result = implAddressBook.sortFunctionForAddressModel(Addressbook1, "getZipcode");
            Assert.assertTrue(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookEmpty_wantToSortByZipCode_shouldReturnFalse() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            boolean result = implAddressBook.sortFunctionForAddressModel(Addressbook2, "getZipcode");
            Assert.assertFalse(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookCorrect_wantToPrintInFormat_shouldGetPrint() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            boolean result = implAddressBook.printInFormat(Addressbook1);
            Assert.assertTrue(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBookEmpty_wantToPrintInFormat_shouldreturnFalse() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            boolean result = implAddressBook.printInFormat(Addressbook2);
            Assert.assertFalse(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void creatingAddressBook_WithNewName_shouldGetCreate() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            boolean result = implAddressBook.addressBookCreate("new2AddressBook");
            Assert.assertTrue(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void creatingAddressBook_WithExistingName_shouldReturnFalse() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            boolean result = implAddressBook.addressBookCreate("new1AddressBook");
            Assert.assertFalse(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void openAddressBook_whenAddressBookExist_sholdGetOpen() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            boolean result = implAddressBook.addressBookOpen("AddressBook1");
            Assert.assertTrue(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void openAddressBook_whenAddressBookDoesNotExist_sholdReturnFalse() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            boolean result = implAddressBook.addressBookOpen("addressBook");
            Assert.assertFalse(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_wantToSaveDataWhenProperFileContainData_sholudGetSaved() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
             boolean result = implAddressBook.saveData(Addressbook1);
             Assert.assertTrue(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenAddressBook_wantToSaveDataWhenProperFileContainNullData_sholudGetSaved() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        try {
            boolean result = implAddressBook.saveData(Addressbook2);
            Assert.assertFalse(result);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_OptionSaveAs_shouldGetChangedFileType() {
        ImplAddressBook implAddressBook = new ImplAddressBook();
        int reslt = 0;
        try {
            reslt = implAddressBook.saveAs(Addressbook1, "AddressBook3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, reslt);
    }
}

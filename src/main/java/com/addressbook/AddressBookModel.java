package com.addressbook;

import java.io.Serializable;
import java.util.List;

public class AddressBookModel {

    private List<PersonModel> personModels;

    public List<PersonModel> getPersonModels() {
        return personModels;
    }

    public void setPersonModels(List<PersonModel> personModels) {
        this.personModels = personModels;
    }

    @Override
    public String toString() {
        return "AddressBookModel{" +
                "personModels=" + personModels +
                '}';
    }
}

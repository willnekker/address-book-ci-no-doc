package com.example.addressbook.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A mock implementation of the contact data access object (DAO) for testing purposes.
 */
public class MockContactDAO implements IContactDAO {
    /**
     * A static list of contacts to be used as a mock database.
     */
    public final ArrayList<Contact> contacts = new ArrayList<>();
    private int autoIncrementedId = 0;

    /**
     * Adds a contact to the mock database.
     * @param contact The contact to add.
     */
    @Override
    public void addContact(Contact contact) {
        contact.setId(autoIncrementedId);
        autoIncrementedId++;
        contacts.add(contact);
    }

    /**
     * Updates a contact in the mock database.
     * @param contact The contact to update.
     */
    @Override
    public void updateContact(Contact contact) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId() == contact.getId()) {
                contacts.set(i, contact);
                break;
            }
        }
    }

    /**
     * Deletes a contact from the mock database.
     * @param contact The contact to delete.
     */
    @Override
    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    /**
     * Retrieves a contact from the mock database by ID.
     * @param id The ID of the contact to retrieve.
     * @return The contact with the specified ID, or null if not found.
     */
    @Override
    public Contact getContact(int id) {
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }

    /**
     * Retrieves all contacts from the mock database.
     * @return A list containing all contacts in the mock database.
     */
    @Override
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }
}
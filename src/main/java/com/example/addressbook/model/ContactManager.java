package com.example.addressbook.model;

import java.util.List;

/**
 * Manages contacts by interacting with a data access object (DAO).
 */
public class ContactManager {
    private IContactDAO contactDAO;

    /**
     * Constructs a ContactManager with the specified contact data access object.
     * @param contactDAO The contact data access object.
     */
    public ContactManager(IContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    /**
     * Searches for contacts based on a query string.
     * @param query The search query string.
     * @return A list of contacts matching the search query.
     */
    public List<Contact> searchContacts(String query) {
        return contactDAO.getAllContacts()
                .stream()
                .filter(contact -> isContactMatched(contact, query))
                .toList();
    }

    /**
     * Checks if a contact matches a search query.
     * @param contact The contact to check.
     * @param query The search query.
     * @return True if the contact matches the query, false otherwise.
     */
    private boolean isContactMatched(Contact contact, String query) {
        if (query == null || query.isEmpty()) return true;
        query = query.toLowerCase();
        String searchString = contact.getFullName()
                + " " + contact.getEmail()
                + " " + contact.getPhone();
        return searchString.toLowerCase().contains(query);
    }

    /**
     * Adds a new contact.
     * @param contact The contact to add.
     */
    public void addContact(Contact contact) {
        contactDAO.addContact(contact);
    }

    /**
     * Deletes an existing contact.
     * @param contact The contact to delete.
     */
    public void deleteContact(Contact contact) {
        contactDAO.deleteContact(contact);
    }

    /**
     * Updates an existing contact.
     * @param contact The contact to update.
     */
    public void updateContact(Contact contact) {
        contactDAO.updateContact(contact);
    }

    /**
     * Retrieves all contacts.
     * @return A list of all contacts.
     */
    public List<Contact> getAllContacts() {
        return contactDAO.getAllContacts();
    }
}
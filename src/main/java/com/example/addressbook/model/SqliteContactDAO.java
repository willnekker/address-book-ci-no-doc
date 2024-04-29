package com.example.addressbook.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the contact data access object (DAO) for SQLite databases.
 */
public class SqliteContactDAO implements IContactDAO {
    private Connection connection;

    /**
     * Constructor for the SQLite contact DAO.
     * Initializes the database connection and creates the contacts table if it does not exist.
     */
    public SqliteContactDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    /**
     * Creates the contacts table if it does not exist in the database.
     */
    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS contacts ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "phone VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a contact to the database.
     * @param contact The contact to add.
     */
    @Override
    public void addContact(Contact contact) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO contacts (firstName, lastName, phone, email) VALUES (?, ?, ?, ?)");
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setString(3, contact.getPhone());
            statement.setString(4, contact.getEmail());
            statement.executeUpdate();
            // Set the id of the new contact
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                contact.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates a contact in the database.
     * @param contact The contact to update.
     */
    @Override
    public void updateContact(Contact contact) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE contacts SET firstName = ?, lastName = ?, phone = ?, email = ? WHERE id = ?");
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setString(3, contact.getPhone());
            statement.setString(4, contact.getEmail());
            statement.setInt(5, contact.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a contact from the database.
     * @param contact The contact to delete.
     */
    @Override
    public void deleteContact(Contact contact) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM contacts WHERE id = ?");
            statement.setInt(1, contact.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a contact from the database by ID.
     * @param id The ID of the contact to retrieve.
     * @return The contact with the specified ID, or null if not found.
     */
    @Override
    public Contact getContact(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM contacts WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                Contact contact = new Contact(firstName, lastName, phone, email);
                contact.setId(id);
                return contact;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all contacts from the database.
     * @return A list containing all contacts in the database.
     */
    @Override
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM contacts";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                Contact contact = new Contact(firstName, lastName, phone, email);
                contact.setId(id);
                contacts.add(contact);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }
}
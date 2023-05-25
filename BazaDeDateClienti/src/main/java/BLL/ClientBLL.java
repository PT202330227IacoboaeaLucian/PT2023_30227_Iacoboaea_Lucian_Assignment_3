package BLL;
/**
 * Finds a client by their unique identifier.
 *
 * @param id The ID of the client to find.
 * @return The Client object if found.
 * @throws NoSuchElementException if the client with the given ID is not found.
 *  Adds a new client to the system.
 *
 *   @param client The client object to add.
 *   @return The added Client object.
 * Deletes a client from the system.
 *
 * @param client The client object to delete.
 * Updates the information of an existing client.
 *
 * @param client The client object to update.
 * @return The updated Client object.
 * Finds the ID of a client by their name.
 *
 * @param numeClient The name of the client to search for.
 * @return The ID of the client if found.
 * @throws NoSuchElementException if the client with the given name is not found.
 * Retrieves a list of all clients in the system.
 *
 * @return A list of all clients.
*/

import DAO.ClientDAO;
import Model.Client;
import Validare.ClientEmailValidator;
import Validare.ClientVarstaValidator;
import Validare.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new ClientEmailValidator());
        validators.add(new ClientVarstaValidator());

        clientDAO = new ClientDAO();
    }

    public Client findClientById(int id) {
        Client st = clientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return st;
    }
    public Client addClient(Client client) {
        for (Validator<Client> validator : validators) {
            validator.validate(client);
        }
        //showAllClients();
        return clientDAO.insert(client);
    }

    // Metoda pentru ștergerea unui client
    public void deleteClient(Client client) {
        clientDAO.delete(client);
    }

    // Metoda pentru actualizarea datelor unui client
    public Client updateClient(Client client) {
        for (Validator<Client> validator : validators) {
            validator.validate(client);
        }
        return clientDAO.update(client);
    }
    public int findClientIdByNume(String numeClient) {
        List<Client> clients = getAllClients();
        for (Client client : clients) {
            if (client.getName().equals(numeClient)) {
                return client.getId();
            }
        }
        throw new NoSuchElementException("Clientul cu numele " + numeClient + " nu a fost găsit.");
    }

    // Metoda pentru obținerea tuturor clienților
    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }


}


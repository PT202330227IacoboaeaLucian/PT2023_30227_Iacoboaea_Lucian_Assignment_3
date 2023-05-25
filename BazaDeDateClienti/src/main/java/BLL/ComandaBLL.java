package BLL;
/**
 * Adds a new order to the system.
 *
 * @param idComanda      The ID of the order.
 * @param idClient       The ID of the client placing the order.
 * @param idProd         The ID of the product being ordered.
 * @param cantitateComanda The quantity of the product being ordered.
 * Deletes an order from the system.
 *
 * @param idComanda The ID of the order to delete.
 * @throws NoSuchElementException if the order with the given ID is not found.
 * Updates the information of an existing order.
 *
 * @param idComanda      The ID of the order to update.
 * @param idClient       The new ID of the client placing the order.
 * @param idProd         The new ID of the product being ordered.
 * @param cantitateComanda The new quantity of the product being ordered.
 * @throws NoSuchElementException if the order with the given ID is not found.
 * Retrieves a list of all orders in the system.
 *
 * @return A list of all orders.
 * Finds an order by its unique identifier.
 *
 * @param idComanda The ID of the order to find.
 * @return The Comanda object if found.
 * @throws NoSuchElementException if the order with the given ID is not found.
 */
import DAO.ComandaDAO;
import Model.Comanda;

import java.util.List;
import java.util.NoSuchElementException;

public class ComandaBLL {
    private ComandaDAO comandaDAO;
    private static int ultimulIdComanda;

    public ComandaBLL() {
        comandaDAO = new ComandaDAO();
    }

    public void adaugaComanda(int idComanda, String idClient, String idProd, int cantitateComanda) {
        int Id=ultimulIdComanda+1;
        ultimulIdComanda=idComanda;
        Comanda comanda = new Comanda(idComanda, idClient, idProd, cantitateComanda);

        // Verifică dacă comanda există deja în baza de date
        if (comandaDAO.findById(comanda.getIdComanda()) != null) {
            throw new IllegalArgumentException("Comanda cu ID-ul " + comanda.getIdComanda() + " există deja.");
        }

        // Validează comanda folosind regulile de validare specifice
        // ...

        // Inserează comanda în baza de date
        comandaDAO.insert(comanda);
    }

    public void stergeComanda(int idComanda) {
        // Verifică dacă comanda există în baza de date
        Comanda comanda = comandaDAO.findById(idComanda);
        if (comanda == null) {
            throw new NoSuchElementException("Comanda cu ID-ul " + idComanda + " nu a fost găsită.");
        }

        // Șterge comanda din baza de date
        comandaDAO.delete(comanda);
    }

    public void actualizeazaComanda(int idComanda, String idClient, String idProd, int cantitateComanda) {
        // Verifică dacă comanda există în baza de date
        Comanda comanda = comandaDAO.findById(idComanda);
        if (comanda == null) {
            throw new NoSuchElementException("Comanda cu ID-ul " + idComanda + " nu a fost găsită.");
        }

        // Actualizează valorile atributelor comandei
        comanda.setClientNume(idClient);
        comanda.setProdusNume(idProd);
        comanda.getCantitateComanda(cantitateComanda);

        // Validează comanda folosind regulile de validare specifice
        // ...

        // Actualizează comanda în baza de date
        comandaDAO.update(comanda);
    }

    public List<Comanda> getAllComenzi() {
        return comandaDAO.findAll();
    }

    public Comanda findComandaById(int idComanda) {
        Comanda comanda = comandaDAO.findById(idComanda);
        if (comanda == null) {
            throw new NoSuchElementException("Comanda cu ID-ul " + idComanda + " nu a fost găsită.");
        }
        return comanda;
    }

}
package BLL;
/**
 * Adds a new product to the system.
 *
 * @param produs The product to add.
 * @throws IllegalArgumentException if the product with the given ID already exists.
 * Deletes a product from the system.
 *
 * @param produs The product to delete.
 * @throws NoSuchElementException if the product with the given ID is not found.
 * Updates the information of an existing product.
 *
 * @param produs The product to update.
 * @throws NoSuchElementException if the product with the given ID is not found.
 * Finds the ID of a product by its name.
 *
 * @param numeProdus The name of the product to search for.
 * @return The ID of the product if found.
 * @throws NoSuchElementException if the product with the given name is not found.
 * Retrieves a list of all products in the system.
 *
 * @return A list of all products.
 * Finds a product by its unique identifier.
 *
 * @param id The ID of the product to find.
 * @return The Produs object if found.
 * @throws NoSuchElementException if the product with the given ID is not found.
 * Finds a product by its name.
 *
 * @param numeProdus The name of the product to find.
 * @return The Produs object if found.
 * @throws NoSuchElementException if the product with the given name is not found.
 */
import DAO.ProdusDAO;
import Model.Produs;

import java.util.List;
import java.util.NoSuchElementException;

public class ProdusBLL {
    private ProdusDAO produsDAO;

    public ProdusBLL() {
        produsDAO = new ProdusDAO();
    }

    public void adaugaProdus(Produs produs) {
        // Verifică dacă produsul există deja în baza de date
        if (produsDAO.findById(produs.getId()) != null) {
            throw new IllegalArgumentException("Produsul cu ID-ul " + produs.getId() + " există deja.");
        }

        // Validează produsul folosind regulile de validare specifice
        // ...

        // Inserează produsul în baza de date
        produsDAO.insert(produs);
    }

    public void stergeProdus(Produs produs) {
        // Verifică dacă produsul există în baza de date
        if (produsDAO.findById(produs.getId()) == null) {
            throw new NoSuchElementException("Produsul cu ID-ul " + produs.getId() + " nu a fost găsit.");
        }

        // Șterge produsul din baza de date
        produsDAO.delete(produs);
    }

    public void actualizeazaProdus(Produs produs) {
        // Verifică dacă produsul există în baza de date
        if (produsDAO.findById(produs.getId()) == null) {
            throw new NoSuchElementException("Produsul cu ID-ul " + produs.getId() + " nu a fost găsit.");
        }

        // Validează produsul folosind regulile de validare specifice
        // ...

        // Actualizează produsul în baza de date
        produsDAO.update(produs);
    }
    public int findProdusIdByNume(String numeProdus) {
        List<Produs> produse = getAllProduse();
        for (Produs produs : produse) {
            if (produs.getNumeProdus().equals(numeProdus)) {
                return produs.getId();
            }
        }
        throw new NoSuchElementException("Produsul cu numele " + numeProdus + " nu a fost găsit.");
    }

    public List<Produs> getAllProduse() {
        return produsDAO.findAll();
    }

    public Produs findProdusById(int id) {
        Produs produs = produsDAO.findById(id);
        if (produs == null) {
            throw new NoSuchElementException("Produsul cu ID-ul " + id + " nu a fost găsit.");
        }
        return produs;
    }
    public Produs findProdusByNume(String numeProdus) {
        List<Produs> produse = getAllProduse();
        for (Produs produs : produse) {
            if (produs.getNumeProdus().equals(numeProdus)) {
                return produs;
            }
        }
        throw new NoSuchElementException("Produsul cu numele " + numeProdus + " nu a fost găsit.");
    }


}

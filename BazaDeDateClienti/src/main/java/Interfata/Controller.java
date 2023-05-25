package Interfata;
/** Displays a table containing the data from a list of objects.
        *
        * @param objects The list of objects to be displayed in the table.
        * @param <T>     The type of objects in the list.
 * Initializes a new instance of the Controller class.
 *
 * @param interfata         The main interface of the application.
 * @param interfataClient   The client interface of the application.
 * @param interfataProduse  The product interface of the application.
 * @param interfataComanda  The order interface of the application.
 * Handles the action when the "Adăugare client" button is pressed.
 *
 * @param e The action event.
 * Handles the action when the "Adăugare produs" button is pressed.
 *
 * @param e The action event.
 * Handles the action when the "Creare comanda" button is pressed.
 *
 * @param e The action event.
        */
import BLL.ClientBLL;
import BLL.ComandaBLL;
import BLL.ProdusBLL;
import Model.Client;
import Model.Comanda;
import Model.Produs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;


public class Controller {
    private InterfataPrincipala interfata;
    private InterfataClient interfataClient;
    private InterfataProduse interfataProduse;
    private ComandaInterfata interfataComanda;
    private ClientBLL clientBLL;
    private ProdusBLL produsBLL;
    private ComandaBLL comandaBLL;
    private int idComanda=1;

    public <T> void showTable(List<T> objects) {
        if (objects.isEmpty()) {
            System.out.println("Lista este goală.");
            return;
        }

        JFrame frame = new JFrame("Tabel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

        // Creare model tabel
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        // Obține atributele primului obiect din listă
        T firstObject = objects.get(0);
        Field[] fields = firstObject.getClass().getDeclaredFields();

        // Adaugă numele atributelor ca coloane în modelul tabelului
        for (Field field : fields) {
            model.addColumn(field.getName());
        }

        // Adaugă datele în tabel
        for (T object : objects) {
            Object[] rowData = new Object[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                try {
                    rowData[i] = fields[i].get(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(rowData);
        }

        // Adaugă tabelul într-un JScrollPane pentru a permite derularea în cazul în care sunt multe date
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        // Afișează fereastra
        frame.setVisible(true);
    }
    public Controller(InterfataPrincipala interfata,InterfataClient interfataClient,InterfataProduse interfataProduse,ComandaInterfata interfataComanda) {
        this.interfata = interfata;
        this.interfataClient = interfataClient;
        this.interfataProduse = interfataProduse;
        this.interfataComanda = interfataComanda;
        this.clientBLL = new ClientBLL();
        this.produsBLL = new ProdusBLL();
        this.comandaBLL = new ComandaBLL();
        this.interfata.getClientButon().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("asdasfasf");
                InterfataClient interfataClient = new InterfataClient();
                interfataClient.setVisible(true);
                interfataClient.aButton(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Extragem valorile din câmpurile text
                        System.out.println("Butonul Adăugare client a fost apăsat");

                        int id = Integer.parseInt(interfataClient.getId().getText());
                        String name = interfataClient.getClientName().getText();
                        String email = interfataClient.getEmail().getText();
                        String address = interfataClient.getAddress().getText();
                        int age = Integer.parseInt(interfataClient.getAge().getText());

                        // Creăm un obiect de tip Client cu valorile extrase
                        Client client = new Client(id, name, email, address, age);

                        // Adăugăm clientul
                        clientBLL.addClient(client);

                        // Actualizăm interfața
                        // (aici poți adăuga orice cod necesar pentru actualizarea interfeței)
                    }
                });
                interfataClient.dButton(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Extragem id-ul clientului din câmpul text
                        System.out.println("Butonul Ștergere client a fost apăsat");

                        int id = Integer.parseInt(interfataClient.getId().getText());

                        // Găsim clientul după id
                        Client client = clientBLL.findClientById(id);

                        // Ștergem clientul
                        clientBLL.deleteClient(client);

                        // Actualizăm interfața
                        // (aici poți adăuga orice cod necesar pentru actualizarea interfeței)
                    }
                });
                interfataClient.eButton(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Butonul Actualizare client a fost apăsat");
                        // Extragem valorile din câmpurile text
                        int id = Integer.parseInt(interfataClient.getId().getText());
                        String name = interfataClient.getClientName().getText();
                        String email = interfataClient.getEmail().getText();
                        String address = interfataClient.getAddress().getText();
                        int age = Integer.parseInt(interfataClient.getAge().getText());


                        // Creăm un obiect de tip Client cu valorile extrase
                        Client client = new Client(id, name, email, address, age);

                        // Actualizăm clientul
                        clientBLL.updateClient(client);

                        // Actualizăm interfața
                        // (aici poți adăuga orice cod necesar pentru actualizarea interfeței)
                    }
                });

                interfataClient.vButton(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        List<Client> clients = clientBLL.getAllClients();
                        showTable(clients);
                    }
                });


            }
        });

        this.interfata.getProdusButon().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfataProduse interfataProduse = new InterfataProduse();
                interfataProduse.setVisible(true);
                interfataProduse.adaugaButton(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Extragem valorile din câmpurile text
                        int id = Integer.parseInt(interfataProduse.getIdProdus().getText());
                        String nume = interfataProduse.getNumeProdus().getText();
                        double pret = Double.parseDouble(interfataProduse.getPretProdus().getText());
                        int cantitate = Integer.parseInt(interfataProduse.getCantitateProdus().getText());

                        // Creăm un obiect de tip Produs cu valorile extrase
                        Produs produs = new Produs(id, nume, cantitate, pret);

                        // Adăugăm produsul
                        produsBLL.adaugaProdus(produs);

                        // Actualizăm interfața
                        // (aici poți adăuga orice cod necesar pentru actualizarea interfeței)
                    }
                });
                interfataProduse.stergeButton(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Extragem id-ul produsului din câmpul text
                        int id = Integer.parseInt(interfataProduse.getIdProdus().getText());

                        // Găsim produsul după id
                        Produs produs = produsBLL.findProdusById(id);

                        // Ștergem produsul
                        produsBLL.stergeProdus(produs);

                        // Actualizăm interfața
                        // (aici poți adăuga orice cod necesar pentru actualizarea interfeței)
                    }
                });
                interfataProduse.editButton(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Extragem valorile din câmpurile text
                        int id = Integer.parseInt(interfataProduse.getIdProdus().getText());
                        String nume = interfataProduse.getNumeProdus().getText();
                        double pret = Double.parseDouble(interfataProduse.getPretProdus().getText());
                        int cantitate = Integer.parseInt(interfataProduse.getCantitateProdus().getText());

                        // Creăm un obiect de tip Produs cu valorile extrase
                        Produs produs = new Produs(id, nume, cantitate, pret);

                        // Actualizăm produsul
                        produsBLL.actualizeazaProdus(produs);

                        // Actualizăm interfața
                        // (aici poți adăuga orice cod necesar pentru actualizarea interfeței)
                    }
                });
                interfataProduse.vizualizareButton(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Obținem lista de produse
                        List<Produs> produse = produsBLL.getAllProduse();
                        showTable(produse);
                        // Afișăm lista de produse în interfață
                        // (aici poți adăuga orice cod necesar pentru afișarea listei de produse în interfață)
                    }
                });
            }
        });

        this.interfata.getComandaButon().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ComandaInterfata interfataComanda = new ComandaInterfata();
                interfataComanda.setVisible(true);
                interfataComanda.getCreareComandaButton().addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int idComanda = Integer.parseInt(interfataComanda.getIdComandaTextField().getText());
                        // Extrage valorile din câmpurile text și combobox-uri
                        String selectedProdus = (String) interfataComanda.getProdusComboBox().getSelectedItem();
                        String selectedClient = (String) interfataComanda.getClientComboBox().getSelectedItem();
                        int cantitate = Integer.parseInt(interfataComanda.getCantitateTextField().getText());
                        Produs produs = produsBLL.findProdusByNume(selectedProdus);

                        if (produs.getCantitateProdus() >= cantitate) {
                            // Actualizează cantitatea produsului
                            produs.setCantitateProdus(produs.getCantitateProdus() - cantitate);
                            produsBLL.actualizeazaProdus(produs);

                            // Adaugă comanda utilizând metoda din ComandaBLL
                            comandaBLL.adaugaComanda(idComanda, selectedClient, selectedProdus, cantitate);

                            // Afiseaza un mesaj de succes
                            JOptionPane.showMessageDialog(null, "Comanda a fost adaugata cu succes!");

                            // Resetează valorile câmpurilor text și combobox-urilor
                            interfataComanda.getIdComandaTextField().setText("");
                            interfataComanda.getProdusComboBox().setSelectedIndex(0);
                            interfataComanda.getClientComboBox().setSelectedIndex(0);
                            interfataComanda.getCantitateTextField().setText("");
                        } else {
                            // Afisează un mesaj de eroare dacă cantitatea disponibilă este insuficientă
                            JOptionPane.showMessageDialog(null, "Cantitatea disponibila pentru produsul selectat este insuficienta!",
                                    "Eroare", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                });
                List<Produs> produse = produsBLL.getAllProduse();
                for (Produs produs : produse) {
                    interfataComanda.getProdusComboBox().addItem(produs.getNumeProdus());
                }

                List<Client> clienti = clientBLL.getAllClients();
                for (Client client : clienti) {
                    interfataComanda.getClientComboBox().addItem(client.getName());
                }
                interfataComanda.vizualizareComenziButton(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        List<Comanda> comenzi = comandaBLL.getAllComenzi();
                        showTable(comenzi);
                    }
                });
            }
        });
    }
}

package org.example;

import BLL.ClientBLL;
import Interfata.*;
import Model.Client;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    protected static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        InterfataPrincipala interfataPrincipala = new InterfataPrincipala();
        InterfataClient interfataClient = new InterfataClient();
        InterfataProduse interfataProduse = new InterfataProduse();
        ComandaInterfata interfataComanda = new ComandaInterfata();

        Controller controller = new Controller(interfataPrincipala, interfataClient, interfataProduse, interfataComanda);

        interfataPrincipala.setVisible(true);
        System.out.println("Hello world!");
        ClientBLL clientBll = new ClientBLL();
        Client student1 = null;

        try {
            student1 = clientBll.findClientById(1);

        } catch (Exception ex) {
            LOGGER.log(Level.INFO, ex.getMessage());
        }

        // obtain field-value pairs for object through reflection

        Reflexion.retrieveProperties(student1);

    }

}

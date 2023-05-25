package Interfata;

import javax.swing.*;
import java.awt.event.ActionListener;

public class InterfataPrincipala extends JFrame {
    private JButton clientButon;
    private JButton produsButon;
    private JButton comandaButon;

    public InterfataPrincipala() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 150);
        setTitle("Interfata principala");
        setLayout(null);

        clientButon = new JButton("Client");
        clientButon.setBounds(50, 40, 100, 25);
        add(clientButon);

        produsButon = new JButton("Produs");
        produsButon.setBounds(150, 40, 100, 25);
        add(produsButon);

        comandaButon = new JButton("Comanda");
        comandaButon.setBounds(100, 80, 100, 25);
        add(comandaButon);
    }
    public void clientButton (ActionListener actionListener) {this.clientButon.addActionListener(actionListener);}
    public void produsButton (ActionListener actionListener) {this.produsButon.addActionListener(actionListener);}

    public void comandaButton (ActionListener actionListener) {this.comandaButon.addActionListener(actionListener);}

    public JButton getClientButon() {
        return clientButon;
    }

    public JButton getProdusButon() {
        return produsButon;
    }

    public JButton getComandaButon() {
        return comandaButon;
    }
}


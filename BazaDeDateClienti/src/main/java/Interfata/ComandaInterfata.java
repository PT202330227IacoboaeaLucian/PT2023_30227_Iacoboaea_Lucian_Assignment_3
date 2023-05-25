package Interfata;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ComandaInterfata extends JFrame {
    private JComboBox<String> produsComboBox;
    private JComboBox<String> clientComboBox;
    private JTextField cantitateTextField;
    private JTextField idComandaTextField;
    private JButton creareComandaButton;
    private JButton vizualizareComenziButton;
    private JButton stergeComandaButton;

    public ComandaInterfata() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 200);
        setTitle("Creare Comanda Produs");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        setContentPane(panel);

        JLabel produsLabel = new JLabel("Produs:");
        produsLabel.setBounds(20, 20, 80, 25);
        panel.add(produsLabel);

        produsComboBox = new JComboBox<>();
        produsComboBox.setBounds(100, 20, 200, 25);
        panel.add(produsComboBox);

        JLabel clientLabel = new JLabel("Client:");
        clientLabel.setBounds(20, 50, 80, 25);
        panel.add(clientLabel);

        clientComboBox = new JComboBox<>();
        clientComboBox.setBounds(100, 50, 200, 25);
        panel.add(clientComboBox);

        JLabel cantitateLabel = new JLabel("Cantitate:");
        cantitateLabel.setBounds(20, 80, 80, 25);
        panel.add(cantitateLabel);

        cantitateTextField = new JTextField();
        cantitateTextField.setBounds(100, 80, 100, 25);
        panel.add(cantitateTextField);

        JLabel idComandaLabel = new JLabel("ID Comanda:");
        idComandaLabel.setBounds(20, 110, 80, 25);
        panel.add(idComandaLabel);

        idComandaTextField = new JTextField();
        idComandaTextField.setBounds(100, 110, 100, 25);
        panel.add(idComandaTextField);

        creareComandaButton = new JButton("Creare Comanda");
        creareComandaButton.setBounds(20, 150, 150, 25);
        panel.add(creareComandaButton);

        vizualizareComenziButton = new JButton("Vizualizare Comenzi");
        vizualizareComenziButton.setBounds(180, 150, 150, 25);
        panel.add(vizualizareComenziButton);

        stergeComandaButton = new JButton("Sterge Comanda");
        stergeComandaButton.setBounds(20, 190, 150, 25);
        panel.add(stergeComandaButton);

    }
    public JComboBox<String> getProdusComboBox() {
        return produsComboBox;
    }

    public JComboBox<String> getClientComboBox() {
        return clientComboBox;
    }

    public JTextField getCantitateTextField() {
        return cantitateTextField;
    }

    public JButton getCreareComandaButton() {
        return creareComandaButton;
    }
    public JButton getVizualizareComenziButton() {return vizualizareComenziButton;}

    public JTextField getIdComandaTextField() {
        return idComandaTextField;
    }

    public void setIdComandaTextField(JTextField idComandaTextField) {
        this.idComandaTextField = idComandaTextField;
    }

    public void vizualizareComenziButton(ActionListener actionListener) {this.vizualizareComenziButton.addActionListener(actionListener);}
    public void creareButton (ActionListener actionListener) {this.creareComandaButton.addActionListener(actionListener);}
    public void stergeComandaButton(ActionListener actionListener) {this.stergeComandaButton.addActionListener(actionListener);}
}

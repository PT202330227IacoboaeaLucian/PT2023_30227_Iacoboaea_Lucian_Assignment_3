package Interfata;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class InterfataProduse extends JFrame {
    private JTextField idProdus;
    private JTextField numeProdus;
    private JTextField pretProdus;
    private JTextField cantitateProdus;

    private JButton adaugaProdus;
    private JButton stergeProdus;
    private JButton editareProdus;
    private JButton vizualizareProduse;
    public InterfataProduse()
    {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 408, 552);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        JLabel produsLabel = new JLabel("Produse");
        produsLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        produsLabel.setBounds(50, 10, 300, 25);
        panel.add(produsLabel);

        JLabel idLabel = new JLabel("idProdus:");
        idLabel.setBounds(50, 60, 100, 25);
        panel.add(idLabel);

        idProdus = new JTextField();
        idProdus.setBounds(140, 60, 200, 25);
        panel.add(idProdus);

        JLabel numeLabel = new JLabel("numeProdus:");
        numeLabel.setBounds(50, 100, 100, 25);
        panel.add(numeLabel);

        numeProdus = new JTextField();
        numeProdus.setBounds(140, 100, 200, 25);
        panel.add(numeProdus);

        JLabel cantiateLabel = new JLabel("cantitateProdus:");
        cantiateLabel.setBounds(50, 140, 120, 25);
        panel.add(cantiateLabel);

        cantitateProdus = new JTextField();
        cantitateProdus.setBounds(170, 140, 170, 25);
        panel.add(cantitateProdus);

        JLabel pretLabel = new JLabel("pretProdus:");
        pretLabel.setBounds(50, 180, 80, 25);
        panel.add(pretLabel);

        pretProdus = new JTextField();
        pretProdus.setBounds(140, 180, 200, 25);
        panel.add(pretProdus);

        adaugaProdus = new JButton("Adauga Produs");
        adaugaProdus.setBounds(50, 280, 120, 25);
        panel.add(adaugaProdus);

        stergeProdus = new JButton("Sterge Produs");
        stergeProdus.setBounds(170, 280, 120, 25);
        panel.add(stergeProdus);

        editareProdus = new JButton("Editare Produs");
        editareProdus.setBounds(50, 320, 120, 25);
        panel.add(editareProdus);

        vizualizareProduse = new JButton("Vizualizare Produse");
        vizualizareProduse.setBounds(170, 320, 150, 25);
        panel.add(vizualizareProduse);
    }
    public void adaugaButton (ActionListener actionListener) {this.adaugaProdus.addActionListener(actionListener);}
    public void stergeButton (ActionListener actionListener) {this.stergeProdus.addActionListener(actionListener);}
    public void editButton (ActionListener actionListener) {this.editareProdus.addActionListener(actionListener);}
    public void vizualizareButton (ActionListener actionListener) {this.vizualizareProduse.addActionListener(actionListener);}
    public JTextField getIdProdus() {
        return idProdus;
    }

    public JTextField getNumeProdus() {
        return numeProdus;
    }

    public JTextField getPretProdus() {
        return pretProdus;
    }

    public JTextField getCantitateProdus() {
        return cantitateProdus;
    }
}

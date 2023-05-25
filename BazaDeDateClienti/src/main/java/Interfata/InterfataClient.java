package Interfata;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfataClient extends JFrame {
    private JTextField id;
    private JTextField name;
    private JTextField email;
    private JTextField address;
    private JTextField age;

    private JButton adaugaClient;
    private JButton stergeClient;
    private JButton editareClient;
    private JButton vizualizareClient;
    public InterfataClient()
    {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 408, 552);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        JLabel studentLabel = new JLabel("Clienti");
        studentLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        studentLabel.setBounds(50, 10, 300, 25);
        panel.add(studentLabel);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(50, 60, 80, 25);
        panel.add(idLabel);

        id = new JTextField();
        id.setBounds(140, 60, 200, 25);
        panel.add(id);

        JLabel nameLabel = new JLabel("Nume:");
        nameLabel.setBounds(50, 100, 80, 25);
        panel.add(nameLabel);

        name = new JTextField();
        name.setBounds(140, 100, 200, 25);
        panel.add(name);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 140, 80, 25);
        panel.add(emailLabel);

        email = new JTextField();
        email.setBounds(140, 140, 200, 25);
        panel.add(email);

        JLabel addressLabel = new JLabel("Adresa:");
        addressLabel.setBounds(50, 180, 80, 25);
        panel.add(addressLabel);

        address = new JTextField();
        address.setBounds(140, 180, 200, 25);
        panel.add(address);

        JLabel ageLabel = new JLabel("Varsta:");
        ageLabel.setBounds(50, 220, 80, 25);
        panel.add(ageLabel);

        age = new JTextField();
        age.setBounds(140, 220, 200, 25);
        panel.add(age);

        adaugaClient = new JButton("Adauga Client");
        adaugaClient.setBounds(50, 280, 120, 25);
        panel.add(adaugaClient);

        stergeClient = new JButton("Sterge Client");
        stergeClient.setBounds(170, 280, 120, 25);
        panel.add(stergeClient);

        editareClient= new JButton("Editare Client");
        editareClient.setBounds(50, 320, 120, 25);
        panel.add(editareClient);

        vizualizareClient = new JButton("Vizualizare Clienti");
        vizualizareClient.setBounds(170, 320, 150, 25);
        panel.add(vizualizareClient);
    }
    public void aButton(ActionListener actionListener) {
        this.adaugaClient.addActionListener(actionListener);
        this.adaugaClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Butonul Adauga Client a fost apasat");
            }
        });
    }
    public void dButton (ActionListener actionListener) {this.stergeClient.addActionListener(actionListener);}
    public void eButton (ActionListener actionListener) {this.editareClient.addActionListener(actionListener);}
    public void vButton (ActionListener actionListener) {this.vizualizareClient.addActionListener(actionListener);}

    public JTextField getId() {
        return id;
    }

    public JTextField getClientName() {
        return name;
    }

    public JTextField getEmail() {
        return email;
    }

    public JTextField getAddress() {
        return address;
    }

    public JTextField getAge() {
        return age;
    }

    public JButton getAdaugaClient() {
        return adaugaClient;
    }
}
package de.projekt.swing;

import javax.swing.*;

public class Trainingsplan extends JFrame {
    private JTextField textFieldDatum;
    private JComboBox comboBoxCardioEinheit;
    private JPanel heutePanel;
    private JTextField textFieldkg1;
    private JTextField textFieldkg2;
    private JTextField textFieldkg3;
    private JTextField textFieldCardioMin;
    private JButton buttonSpeichern;
    private JButton buttonLetzte;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    public Trainingsplan() {
        setTitle("Trainingsplan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setContentPane(heutePanel);
        setVisible(true);
    }

}

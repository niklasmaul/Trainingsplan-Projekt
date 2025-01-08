package de.projekt.gui;


import de.projekt.objects.Einheit;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class Trainingsplan extends JFrame {
    private JTextField textFieldDatum;
    private JComboBox comboBoxCardioEinheit;
    private JPanel heutePanel;
    private JTextField textFieldUebung1;
    private JTextField textFieldUebung2;
    private JTextField textFieldUebung3;
    private JTextField textFieldCardioMin;
    private JButton buttonSpeichern;
    private JButton buttonLetzte;
    private JTextField textFieldKG1;
    private JTextField textFieldKG2;
    private JTextField textFieldKG3;
    private ArrayList<Einheit> einheit = new ArrayList<Einheit>();

    public Trainingsplan() {
        setTitle("Trainingsplan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setContentPane(heutePanel);
        setVisible(true);

        //setzt Datum Feld automatisch auf heute
        LocalDate today = LocalDate.now();
        textFieldDatum.setText(today.toString());

        //initiiert die Objekte
        initObjects();
        //initiiert die Listener
        initListener();


    }

    //startet das Programm
    public static void main(String[] args) {
        new Trainingsplan();
    }

    //enthält alle Listener
    private void initListener() {
        buttonSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String uebung1 = textFieldUebung1.toString();
                    String uebung2 = textFieldUebung2.toString();
                    String uebung3 = textFieldUebung3.toString();
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Bitte eine gültige Übung eingeben!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        //öffnet neues Übersichts Fenster
        buttonLetzte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                TrainingsplanUebersicht trainingsplanUebersicht = new TrainingsplanUebersicht();
                trainingsplanUebersicht.setVisible(true);
            }
        });
    }

    //setzt 3 Einheiten schon einmal in die Liste
    private void initObjects() {
        Einheit e1 = new Einheit("Brustpresse", 72.5,
                "Butterfly", 15.0, "Schulterpresse", 30.0,
                "Laufband", 30, LocalDate.now().minusDays(15));
        Einheit e2 = new Einheit("High Row", 72.5,
                "Latzug", 70.0, "Curls", 15,
                "Stairmaster", 20, LocalDate.now().minusDays(10));
        Einheit e3 = new Einheit("Beinpresse", 170.0,
                "Beinstrecker", 32.5, "Wadenpresse", 100.0,
                "Fahrrad", 30, LocalDate.now().minusDays(5));
        Einheit e4 = new Einheit("High Row", 67.5,
                "Latzug", 65.0, "Curls", 12.5,
                "Crosstrainer", 20, LocalDate.now().minusDays(3));
        Einheit e5 = new Einheit("Brustpresse", 70.0,
                "Butterfly", 12.5, "Schulterpresse", 27.5,
                "Laufband", 90, LocalDate.now().minusDays(1));

        einheit.add(e1); //fügt die Einheiten in die ArrayList
        einheit.add(e2);
        einheit.add(e3);
        einheit.add(e4);
        einheit.add(e5);
    }



    //gibt die ArrayList auch an andere Klassen
    public ArrayList<Einheit> getEinheit() {
        return einheit;
    }
}

package de.projekt.gui;


import de.projekt.objects.Einheit;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

//die Trainingsplan Klasse erstellt das GUI und wie das funktioniert
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
    private ArrayList<Einheit> einheiten = new ArrayList<Einheit>();

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
        //versucht die Daten zu speichern
        buttonSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //nimmt die Strings
                    if(textFieldUebung1.getText().isEmpty() || textFieldUebung2.getText().isEmpty() || textFieldUebung3.getText().isEmpty()) {
                        throw new Exception("Fülle die Felder bitte aus!");
                    }
                    String uebung1 = textFieldUebung1.getText();
                    String uebung2 = textFieldUebung2.getText();
                    String uebung3 = textFieldUebung3.getText();
                    String cardio = comboBoxCardioEinheit.getSelectedItem().toString();
                    // Versucht, den Text aus textFieldKG in eine Zahl zu konvertieren
                    double kg1 = Double.parseDouble(textFieldKG1.getText());
                    double kg2 = Double.parseDouble(textFieldKG2.getText());
                    double kg3 = Double.parseDouble(textFieldKG3.getText());
                    // Versucht, den Text aus textFieldCardioMin in eine Zahl zu konvertieren
                    int cardioMin = Integer.parseInt(textFieldCardioMin.getText());
                    //Versucht Das datum zu speichern
                    LocalDate datum = LocalDate.parse(textFieldDatum.getText());
                    for(Einheit einheit : einheiten) { //prüft ob bereits eine Einheit an diesem Tag durchgeführt wurde
                        if(datum.equals(einheit.getDate())) {
                            throw new Exception("Du hast bereits eine Einheit an diesem Tag durchgeführt");
                        }
                    }
                    // Versucht, die sachen in einem Objekt zu speichern
                    Einheit varEinheit = new Einheit(uebung1, kg1, uebung2, kg2, uebung3, kg3, cardio, cardioMin, datum);
                    einheiten.add(varEinheit); //speichert die Einheit in die Liste
                    JOptionPane.showMessageDialog(null, "Daten wurden erfolgreich gespeichert!", "gespeichert", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    // Fehlermeldung bei ungültiger Eingabe
                    JOptionPane.showMessageDialog(null, "Bitte gebe in jedem Feld ein gültigen Wert ein!\n" + ex.getMessage(), "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //öffnet neues Übersichts Fenster
        buttonLetzte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrainingsplanUebersicht(Trainingsplan.this, einheiten); //öffnet das neue Fenster mit den
                // Parametern von dieser GUI damit dieses nicht verloren geht/nicht neu ertellt wird. die ArrayList wird angegeben
                // damit diese auch die gleiche in der Übersicht Klasse ist wie diese hier.
                dispose(); //schließt das Fenster
            }
        });
    }

    //erstellt 5 Einheiten/Objekte
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

        einheiten.add(e1); //fügt die Einheiten/Objekte in die ArrayList
        einheiten.add(e2);
        einheiten.add(e3);
        einheiten.add(e4);
        einheiten.add(e5);
    }



    //gibt die ArrayList auch an andere Klassen
    public ArrayList<Einheit> getEinheiten() {
        return einheiten;
    }
}

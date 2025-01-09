package de.projekt.gui;

import de.projekt.objects.Einheit;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class TrainingsplanUebersicht extends JFrame{
    private JComboBox comboBoxAuswahl;
    private JTextArea textAreaUebersicht;
    private JPanel uebersicht;
    private JButton buttonBack;

    private ArrayList<Einheit> einheiten;

    public TrainingsplanUebersicht(JFrame trainingsplan, ArrayList<Einheit> einheiten) {
        setTitle("Trainingsplan Übersicht");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setContentPane(uebersicht);
        setVisible(true);

        this.einheiten = einheiten;

        textAreaUebersicht.setText(insgesamt()); //setzt am beginn direkt die Insgesamt Übersicht ein

        //setzt die Einheiten in die ComboBox zum auswählen
        for (Einheit einheit : einheiten) {
            comboBoxAuswahl.addItem(einheit); //setzt jede Einheit aus der Liste nacheinander ein
        }

        initListener(); //ruft die methode aud damit die Listener funktionieren

        //Listener um zurück zum anderen Fenster zu kommen
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //schließt das Fenster
                trainingsplan.setVisible(true); //öffnet Hauptfenster
            }
        });

    }

    //berechnet die Insgesamte bewegten KG und kcal verbrauch etc.
    private String insgesamt() {
        int anzEinheiten = einheiten.size(); //gibt die größe der Liste als Zahl der insegesamt vollendeten EInheiten wieder
        double kggesamt = 0;
        double kaloriengesamt = 0;

        for (Einheit einheit : einheiten) {
            kggesamt = kggesamt + einheit.getGewicht1() + einheit.getGewicht2() + einheit.getGewicht3(); //alle gewichte von jeder Einheit wird addiert.
            kaloriengesamt = kaloriengesamt + einheit.kcalverbrannt(); //alle verbrannte kcal werden addiert
        }

        return "Insgesamt: \n------------------------------------------------\n" +
                "Insgesamt vollendete Einheiten: " + anzEinheiten + "\nInsgesamt bewegte Kilogramm: " + kggesamt + "\nInsgesamt verbrannt Kalorien: " + kaloriengesamt;
    }

    //berechnet die Werte der letzten 7 Tage
    private String last7Days() {
        LocalDate today = LocalDate.now(); //gibt das aktuelle Datum
        int anzEinheiten = 0;
        double kggesamt = 0;
        double kaloriengesamt = 0;

        for (Einheit einheit : einheiten) {
            if(einheit.getDate().isAfter(today.minusDays(7))) { //prüft ob die einheit in den Letzten 7 Tagen ausgeführt wurde
                anzEinheiten = anzEinheiten + 1; //addiert die Einheiten
                kggesamt = kggesamt + einheit.getGewicht1() + einheit.getGewicht2() + einheit.getGewicht3(); //addiert die gewichte
                kaloriengesamt = kaloriengesamt + einheit.kcalverbrannt(); //addiert die verbrannte kalorien
            }

        }

        return "Letzte 7 Tage: \n------------------------------------------------\n" +
                "Insgesamt vollendete Einheiten: " + anzEinheiten + "\nInsgesamt bewegte Kilogramm: " + kggesamt + "\nInsgesamt verbrannt Kalorien: " + kaloriengesamt;
    }

    //Listener
    private void initListener() {
        //Listener für comboBox auswahl
        comboBoxAuswahl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBoxAuswahl.getSelectedItem() != null) { //überprüft ob überhaupt etwas ausgewählt ist, sonst gehts nicht weiter
                    if (comboBoxAuswahl.getSelectedItem().toString().equals("Insgesamt")) {
                        textAreaUebersicht.setText(insgesamt()); //gibt String insegsamt aus wenn Insegesamt ausgwählt wurde
                    } else if (comboBoxAuswahl.getSelectedItem().toString().equals("Letzte 7 Tage")) {
                        textAreaUebersicht.setText(last7Days()); // gibt String last7Days aus wenn Letzte 7 Tage ausgewählt wurde
                    } else {
                        Einheit selectedEinheit = (Einheit) comboBoxAuswahl.getSelectedItem();
                        textAreaUebersicht.setText(selectedEinheit.ausgeben()); //gibt die ausgewöhlte Einheit aus
                    }
                }
            }
        });

    }
}

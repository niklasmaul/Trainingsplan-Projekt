package de.projekt.objects;

import java.time.LocalDate;

public class Einheit {

    private LocalDate date;
    private String uebung1, uebung2, uebung3, cardioUebung;
    private double gewicht1, gewicht2, gewicht3;
    private int cardioMin;

    public Einheit(String uebung1, double gewicht1, String uebung2, double gewicht2, String uebung3, double gewicht3,
                   String cardioUebung, int cardioMin, LocalDate date) {
        this.cardioMin = cardioMin;
        this.gewicht3 = gewicht3;
        this.gewicht2 = gewicht2;
        this.gewicht1 = gewicht1;
        this.cardioUebung = cardioUebung;
        this.uebung3 = uebung3;
        this.uebung2 = uebung2;
        this.uebung1 = uebung1;
        this.date = date;
    }

    //gibt die werte aus
    public String ausgeben() {
        return "Einheit am " + date + " ausgeführt. \n------------------------------------------------\nÜbung 1: " + uebung1 + ", Gewicht: " + gewicht1 + "kg\nÜbung 2: " + uebung2 + ", Gewicht: " + gewicht2 +
                "kg\nÜbung 3: " + uebung3 + ", Gewicht: " + gewicht3 + "kg\n------------------------------------------------\nCardioübung: " + cardioUebung + ", " +
                cardioMin + " Minuten, kcal verbrannt: " + kcalverbrannt() + "\n------------------------------------------------\n";
    }

    //kcal verbrauch berechnen
    public double kcalverbrannt() {
        return switch (cardioUebung) { //wenn Übung so heißt wird jeweils eine andere rechnung vorgenommen
            case "Laufband" -> (double) Math.round((5.26 * cardioMin) * 100) / 100;
            case "Crosstrainer" -> (double) Math.round((6.13 * cardioMin) * 100) / 100;
            case "Rudergerät" -> (double) Math.round((8.58 * cardioMin) * 100) / 100;
            case "Stairmaster" -> (double) Math.round((9.8 * cardioMin) * 100) / 100;
            case "Fahrrad" -> (double) Math.round((8.33 * cardioMin) * 100) / 100;
            default -> 0;
        };
    }

    //damit wird bei der Combobox Einheit auswahl nur das Datum angezeigt
    @Override
    public String toString() {
        return date.toString();
    }

    //Lässt die Werte auch in anderen Klassen ausgeben
    public LocalDate getDate() {
        return date;
    }

    public String getUebung1() {
        return uebung1;
    }

    public String getUebung2() {
        return uebung2;
    }

    public String getUebung3() {
        return uebung3;
    }

    public String getCardioUebung() {
        return cardioUebung;
    }

    public double getGewicht1() {
        return gewicht1;
    }

    public double getGewicht2() {
        return gewicht2;
    }

    public double getGewicht3() {
        return gewicht3;
    }

    public int getCardioMin() {
        return cardioMin;
    }


}

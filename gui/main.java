package gui;

import javax.swing.SwingUtilities;
import mesin.pemutar;

public class main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            pemutar mesin = new pemutar();

            //contoh data awal
            mesin.gettreedata().tambah(new builder.lagu(
                    "L001",
                    "Bohemian Rhapsody",
                    "Queen",
                    "Rock",
                    355,
                    "A Night at the Opera"));

            mesin.gettreedata().tambah(new builder.lagu(
                    "L002",
                    "Hati-Hati di Jalan",
                    "Tulus",
                    "Pop",
                    240,
                    "Manusia"));

            mesin.gettreedata().tambah(new builder.lagu(
                    "L003",
                    "Diri",
                    "Tulus",
                    "Pop",
                    250,
                    "Manusia"));

            mainframe frame = new mainframe(mesin);
            frame.setVisible(true);

        });
    }
}
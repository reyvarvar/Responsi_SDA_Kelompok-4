package gui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import mesin.pemutar;

public class main {

    public static void main(String[] args) {
        
        // Mengubah Look and Feel agar desain UI menyerupai sistem operasi pengguna (misal: Windows)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // SwingUtilities digunakan untuk memastikan UI berjalan di thread yang aman (Thread Safety)
        SwingUtilities.invokeLater(() -> {
            
            // Instansiasi mesin inti (objek utama yang megang antrean dan histori)
            pemutar mesinPemutar = new pemutar();
            
            // Analisis Kompleksitas: O(1) Amortized per operasi insert pada HashMap bersarang
            mesinPemutar.gettreedata().tambah(new builder.lagu("L001", "Bohemian Rhapsody", "Queen", "Rock", 355, "A Night at the Opera"));
            mesinPemutar.gettreedata().tambah(new builder.lagu("L002", "Hati-Hati di Jalan", "Tulus", "Pop", 240, "Manusia"));
            mesinPemutar.gettreedata().tambah(new builder.lagu("L003", "Diri", "Tulus", "Pop", 250, "Manusia"));
            mesinPemutar.gettreedata().tambah(new builder.lagu("L004", "Kereta Ini Melaju Terlalu Cepat", "Nadin Amizah", "Indie", 280, "Selamat Ulang Tahun")); 
            mesinPemutar.gettreedata().tambah(new builder.lagu("L005", "Rayuan Perempuan Gila", "Nadin Amizah", "Indie", 300, "Untuk Dunia, Cinta, dan Kotornya"));

            // Membangun dan menampilkan antarmuka grafis ke layar
            mainframe frameUtama = new mainframe(mesinPemutar);
            frameUtama.setVisible(true);

        });
    }
}
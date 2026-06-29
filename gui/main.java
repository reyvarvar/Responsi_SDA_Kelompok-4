package gui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import mesin.pemutar;

public class main {

    public static void main(String[] args) {
        
        // Mengubah Look and Feel agar menyerupai sistem operasi pengguna
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            
            // Instansiasi mesin dengan penamaan camelCase
            pemutar mesinPemutar = new pemutar();

            // --- CONTOH MENAMBAH LAGU KE PUSTAKA ---
            // Analisis Kompleksitas: O(1) rata-rata per operasi insert pada HashMap bersarang
            mesinPemutar.gettreedata().tambah(new builder.lagu("L001", "Bohemian Rhapsody", "Queen", "Rock", 355, "A Night at the Opera"));
            mesinPemutar.gettreedata().tambah(new builder.lagu("L002", "Hati-Hati di Jalan", "Tulus", "Pop", 240, "Manusia"));
            mesinPemutar.gettreedata().tambah(new builder.lagu("L003", "Diri", "Tulus", "Pop", 250, "Manusia"));
            
            // CARA NAMBAH LAGU BARU: Cukup copy-paste format di atas.
            // Contoh lagu untuk didengarkan saat naik kereta:
            mesinPemutar.gettreedata().tambah(new builder.lagu("L004", "Kereta Ini Melaju Terlalu Cepat", "Nadin Amizah", "Indie", 280, "Selamat Ulang Tahun")); 
            mesinPemutar.gettreedata().tambah(new builder.lagu("L005", "Rayuan Perempuan Gila", "Nadin Amizah", "Indie", 300, "Untuk Dunia, Cinta, dan Kotornya"));

            // Menjalankan antarmuka grafis
            mainframe frameUtama = new mainframe(mesinPemutar);
            frameUtama.setVisible(true);

        });
    }
}
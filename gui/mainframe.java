package gui;

import builder.lagu;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import mesin.pemutar;

public class mainframe extends JFrame {

    private pemutar mesin;

    private JLabel labeljudul;
    private JLabel labelpenyanyi;
    private JLabel labelalbum;
    private JLabel labelgenre;
    private JList<lagu> listantrean;
    private JList<lagu> listhistori;
    private JTree treelagu;
    private DefaultTreeModel modeltree;

    public mainframe(pemutar mesin) {
        this.mesin = mesin;

        setTitle("pemutar lagu - responsi sda");
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // mengatur warna background utama jadi biru gelap (navy) biar elegan
        JPanel panelutama = new JPanel(new BorderLayout(15, 15));
        panelutama.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelutama.setBackground(new Color(44, 62, 80));
        setContentPane(panelutama);

        // ==========================================
        // PANEL KIRI (Pustaka Musik & Tambah Lagu)
        // ==========================================
        
        // BorderLayout digunakan untuk mengatur posisi area komponen (atas, bawah, tengah, kiri, kanan)
        JPanel panelkiri = new JPanel(new BorderLayout(0, 10));
        panelkiri.setOpaque(false); // fungsi setOpaque(false) biar panel ini transparan ngikutin warna background navy

        treelagu = new JTree();
        refreshtree(); // muat data awal lagu dari HashMap ke tampilan JTree
        treelagu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        treelagu.setBackground(new Color(236, 240, 241));

        // menambahkan event listener klik ganda pada tree untuk memutar lagu
        treelagu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) treelagu.getLastSelectedPathComponent();
                    if (node == null) return;

                    // isLeaf digunakan untuk memastikan yang diklik adalah file lagu, bukan folder (genre/penyanyi)
                    if (node.isLeaf()) {
                        Object info = node.getUserObject();
                        if (info instanceof lagu) {
                            lagu pilih = (lagu) info;
                            mesin.getqueue().addantrean(pilih);
                            if (mesin.getsekarang() == null) mesin.next();
                            syncui();
                        }
                    }
                }
            }
        });

        // JScrollPane berfungsi supaya list/tree bisa di-scroll ke bawah kalau datanya panjang
        JScrollPane scrolltree = new JScrollPane(treelagu);
        
        // membuat border garis warna putih dengan teks judul panel di atasnya
        scrolltree.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                " music ", 0, 0,
                new Font("Segoe UI", Font.BOLD, 12), Color.BLUE));
        
        // memasukkan scrolltree ke bagian tengah (center) dari panelkiri
        panelkiri.add(scrolltree, BorderLayout.CENTER);

        // JButton adalah komponen tombol yang bisa diklik user
        JButton tomboltambah = new JButton("+ tambah lagu baru");
        tomboltambah.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tomboltambah.setBackground(new Color(39, 174, 96)); // mengatur warna hijau
        tomboltambah.setForeground(Color.BLUE); // mengatur warna teks tombol jadi putih
        tomboltambah.setFocusPainted(false);
        tomboltambah.setOpaque(true); // WAJIB TRUE: biar warna tombol ga transparan/ketimpa tema OS Windows
        tomboltambah.setCursor(new Cursor(Cursor.HAND_CURSOR)); // kursor berubah jadi ikon tangan pas ngehover

        // memberikan aksi (action) apa yang terjadi kalau tombol ditambah klik
        tomboltambah.addActionListener(e -> formtambahlagu());
        panelkiri.add(tomboltambah, BorderLayout.SOUTH);

        panelkiri.setPreferredSize(new Dimension(300, 0));
        panelutama.add(panelkiri, BorderLayout.WEST);

        // ==========================================
        // PANEL TENGAH (Label Lagu & Kontrol)
        // ==========================================
        JPanel paneltengah = new JPanel(new BorderLayout());
        paneltengah.setBackground(new Color(236, 240, 241)); // putih keabu-abuan
        paneltengah.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 3, true));

        // JLabel digunakan untuk menampilkan teks statis biasa (bukan form input)
        JPanel info = new JPanel();
info.setOpaque(false);
info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
info.setBorder(new EmptyBorder(20,20,20,20));

labeljudul = new JLabel("Belum ada lagu");
labeljudul.setAlignmentX(Component.CENTER_ALIGNMENT);
labeljudul.setFont(new Font("Segoe UI", Font.BOLD, 26));

labelpenyanyi = new JLabel("-");
labelpenyanyi.setAlignmentX(Component.CENTER_ALIGNMENT);

labelalbum = new JLabel("-");
labelpenyanyi.setFont(new Font("Segoe UI", Font.PLAIN, 15));
labelalbum.setAlignmentX(Component.CENTER_ALIGNMENT);
labelalbum.setFont(new Font("Segoe UI", Font.PLAIN, 15));

labelgenre = new JLabel("-");
labelgenre.setFont(new Font("Segoe UI", Font.PLAIN, 15));
labelgenre.setAlignmentX(Component.CENTER_ALIGNMENT);


info.add(Box.createVerticalStrut(15));
JLabel now = new JLabel("NOW PLAYING");
now.setFont(new Font("Segoe UI", Font.BOLD, 13));
now.setAlignmentX(Component.CENTER_ALIGNMENT);

info.add(now);
JSeparator garis = new JSeparator();
garis.setMaximumSize(new Dimension(220,2));

info.add(Box.createVerticalStrut(8));
info.add(garis);
info.add(Box.createVerticalStrut(15));
info.add(Box.createVerticalGlue());
info.add(labeljudul);
info.add(Box.createVerticalStrut(15));
info.add(labelpenyanyi);
info.add(labelalbum);
info.add(labelgenre);
info.add(Box.createVerticalGlue());

paneltengah.add(info, BorderLayout.CENTER);

        // FlowLayout mengatur komponen agar berjejer rapi menyamping dari kiri ke kanan
        JPanel paneltombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 25));
        paneltombol.setOpaque(false);

        JButton tombolprev = new JButton("⏮ Prev");
        JButton tombolnext = new JButton("Next ⏭");
       

        tombolprev.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tombolnext.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tombolprev.setPreferredSize(new Dimension(120,45));
tombolnext.setPreferredSize(new Dimension(120,45));

        
        tombolprev.setBackground(new Color(52, 152, 219)); // warna tombol biru
        tombolnext.setBackground(new Color(52, 152, 219));
        tombolprev.setForeground(Color.RED);
        tombolnext.setForeground(Color.RED);
        
        tombolprev.setFocusPainted(false);
        tombolnext.setFocusPainted(false);
        tombolprev.setOpaque(true);
        tombolnext.setOpaque(true);

    paneltombol.add(tombolprev);
paneltombol.add(tombolnext);
        paneltengah.add(paneltombol, BorderLayout.SOUTH);
        panelutama.add(paneltengah, BorderLayout.CENTER);
        

        // ==========================================
        // PANEL KANAN (Daftar Antrean & Histori)
        // ==========================================
        
        // GridLayout membagi area panel sama besar. Format: (baris, kolom, jarak_X, jarak_Y)
        JPanel panelkanan = new JPanel(new GridLayout(2, 1, 0, 15));
        panelkanan.setOpaque(false);

        JPanel panelqueue = new JPanel(new BorderLayout());
        panelqueue.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), 
                " antrean lagu ", 0, 0, 
                new Font("Segoe UI", Font.BOLD, 12), Color.blue));
        
        // JList digunakan untuk menampilkan daftar item secara menurun ke bawah
        listantrean = new JList<>();
        listantrean.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        listantrean.setBackground(new Color(236, 240, 241));
        panelqueue.add(new JScrollPane(listantrean), BorderLayout.CENTER);

        JPanel panelhistori = new JPanel(new BorderLayout());
        panelhistori.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.gray), 
                " Histori ", 0, 0, 
                new Font("Segoe UI", Font.BOLD, 12), Color.BLUE));
        
        listhistori = new JList<>();
        listhistori.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        listhistori.setBackground(new Color(236, 240, 241));
        panelhistori.add(new JScrollPane(listhistori), BorderLayout.CENTER);

        panelkanan.add(panelqueue);
        panelkanan.add(panelhistori);
        panelkanan.setPreferredSize(new Dimension(280, 0));

        panelutama.add(panelkanan, BorderLayout.EAST);

        // --- ACTION TOMBOL PREV & NEXT ---
        tombolnext.addActionListener(e -> {
            mesin.next();
            syncui();
        });

        tombolprev.addActionListener(e -> {
            mesin.prev();
            syncui();
        });

        syncui(); // panggil sinkronisasi pertama kali saat program jalan
    }

    // ==========================================
    // FUNGSI LOGIKA GUI
    // ==========================================

    private void formtambahlagu() {
        // JTextField adalah kotak inputan tempat user mengetik teks
        JTextField inputjudul = new JTextField();
        JTextField inputpenyanyi = new JTextField();
        JTextField inputgenre = new JTextField();
        JTextField inputalbum = new JTextField();

        // menyusun form input dalam bentuk array object
        Object[] form = {
                "judul:", inputjudul,
                "penyanyi:", inputpenyanyi,
                "genre:", inputgenre,
                "album:", inputalbum
        };

        // memunculkan pop-up dialog bawaan Java Swing
        int hasil = JOptionPane.showConfirmDialog(this, form, "tambah lagu", JOptionPane.OK_CANCEL_OPTION);
        
        if (hasil == JOptionPane.OK_OPTION) {
            // validasi: kalau ada kolom yang belum diisi (kosong), fungsi akan berhenti
            if (inputjudul.getText().isEmpty() || inputpenyanyi.getText().isEmpty() || inputgenre.getText().isEmpty() || inputalbum.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "data tidak boleh kosong!");
                return;
            }

            // generate id otomatis dengan kombinasi huruf L dan angka acak 100-999
            String idbaru = "L" + (new Random().nextInt(900) + 100);
            lagu baru = new lagu(idbaru, inputjudul.getText(), inputpenyanyi.getText(), inputgenre.getText(), 0, inputalbum.getText());
            
            mesin.gettreedata().tambah(baru);
            refreshtree(); // perbarui tampilan pustaka musik setelah lagu ditambah
            
            JOptionPane.showMessageDialog(this, "lagu sukses masuk pustaka!");
        }
    }

    private void refreshtree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("semua lagu");
        Map<String, Map<String, Map<String, List<lagu>>>> data = mesin.gettreedata().gettree();

        // iterasi (perulangan) bersarang untuk membongkar isi HashMap bertingkat menjadi struktur pohon
        for (String genre : data.keySet()) {
            DefaultMutableTreeNode nodegenre = new DefaultMutableTreeNode(genre);
            root.add(nodegenre);
            for (String penyanyi : data.get(genre).keySet()) {
                DefaultMutableTreeNode nodepenyanyi = new DefaultMutableTreeNode(penyanyi);
                nodegenre.add(nodepenyanyi);
                for (String album : data.get(genre).get(penyanyi).keySet()) {
                    DefaultMutableTreeNode nodealbum = new DefaultMutableTreeNode(album);
                    nodepenyanyi.add(nodealbum);
                    for (lagu item : data.get(genre).get(penyanyi).get(album)) {
                        nodealbum.add(new DefaultMutableTreeNode(item));
                    }
                }
            }
        }
        
        // jika tree baru pertama kali dibuat, pasang modelnya. jika sudah ada, update (reload) saja.
        if (modeltree == null) {
            modeltree = new DefaultTreeModel(root);
            treelagu.setModel(modeltree);
        } else {
            modeltree.setRoot(root);
            modeltree.reload();
        }
    }
public void syncui() {

    if (mesin.getsekarang() != null) {

        lagu sekarang = mesin.getsekarang();

        labeljudul.setText(sekarang.getJudul());

        labelpenyanyi.setText("Penyanyi : " + sekarang.getPenyanyi());

        labelalbum.setText("Album : " + sekarang.getAlbum());

        labelgenre.setText("Genre : " + sekarang.getGenre());

    } else {

        labeljudul.setText("Belum ada lagu");

        labelpenyanyi.setText("");

        labelalbum.setText("");

        labelgenre.setText("");

    }

    listantrean.setListData(
            mesin.getqueue().getallqueueaslist().toArray(new lagu[0]));

    listhistori.setListData(
            mesin.gethistori().getallhistori().toArray(new lagu[0]));
}
}
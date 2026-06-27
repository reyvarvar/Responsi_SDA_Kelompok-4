package gui;

import builder.lagu;
import mesin.pemutar;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class mainframe extends JFrame {

    private pemutar mesin;

    private JLabel lblnowplaying;
    private JList<lagu> listqueue;
    private JList<lagu> listhistori;
    private JTree treelagu;

    public mainframe(pemutar mesin) {

        this.mesin = mesin;

        setTitle("pemutar lagu");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        //menampilkan tree lagu
        treelagu = new JTree(buildtree());
        JScrollPane scrolltree = new JScrollPane(treelagu);
        scrolltree.setPreferredSize(new Dimension(250,0));
        add(scrolltree, BorderLayout.WEST);

        //bagian tengah
        JPanel paneltengah = new JPanel(new BorderLayout());

        lblnowplaying = new JLabel("belum ada lagu", SwingConstants.CENTER);
        lblnowplaying.setFont(new Font("Arial", Font.BOLD, 22));

        paneltengah.add(lblnowplaying, BorderLayout.CENTER);

        JPanel paneltombol = new JPanel();

        JButton prev = new JButton("prev");
        JButton next = new JButton("next");

        paneltombol.add(prev);
        paneltombol.add(next);

        paneltengah.add(paneltombol, BorderLayout.SOUTH);

        add(paneltengah, BorderLayout.CENTER);

        //bagian kanan
        JPanel panelkanan = new JPanel(new GridLayout(2,1,0,10));

        JPanel panelqueue = new JPanel(new BorderLayout());
        panelqueue.add(new JLabel("antrean", SwingConstants.CENTER), BorderLayout.NORTH);

        listqueue = new JList<>();
        panelqueue.add(new JScrollPane(listqueue), BorderLayout.CENTER);

        JPanel panelhistori = new JPanel(new BorderLayout());
        panelhistori.add(new JLabel("histori", SwingConstants.CENTER), BorderLayout.NORTH);

        listhistori = new JList<>();
        panelhistori.add(new JScrollPane(listhistori), BorderLayout.CENTER);

        panelkanan.add(panelqueue);
        panelkanan.add(panelhistori);

        panelkanan.setPreferredSize(new Dimension(250,0));

        add(panelkanan, BorderLayout.EAST);

        next.addActionListener(e -> {
            mesin.next();
            syncui();
        });

        prev.addActionListener(e -> {
            mesin.prev();
            syncui();
        });

        syncui();
    }

    //mengubah data tree menjadi tampilan jtree
    private DefaultMutableTreeNode buildtree() {
              DefaultMutableTreeNode root = new DefaultMutableTreeNode("semua lagu");

        Map<String, Map<String, Map<String, List<lagu>>>> data =
                mesin.gettreedata().gettree();

        for (String genre : data.keySet()) {

            DefaultMutableTreeNode nodegenre =
                    new DefaultMutableTreeNode(genre);

            root.add(nodegenre);

            for (String penyanyi : data.get(genre).keySet()) {

                DefaultMutableTreeNode nodepenyanyi =
                        new DefaultMutableTreeNode(penyanyi);

                nodegenre.add(nodepenyanyi);

                for (String album : data.get(genre).get(penyanyi).keySet()) {

                    DefaultMutableTreeNode nodealbum =
                            new DefaultMutableTreeNode(album);

                    nodepenyanyi.add(nodealbum);

                    for (lagu lagu : data.get(genre).get(penyanyi).get(album)) {

                        nodealbum.add(new DefaultMutableTreeNode(lagu));

                    }
                }
            }
        }

        return root;
    }

    //mengubah tampilan gui sesuai kondisi data terbaru
    public void syncui() {

        if (mesin.getsekarang() != null) {

            lblnowplaying.setText(
                    mesin.getsekarang().getJudul()
                    + " - " +
                    mesin.getsekarang().getPenyanyi());

        } else {

            lblnowplaying.setText("belum ada lagu");

        }

        listqueue.setListData(
                mesin.getqueue()
                        .getallqueueaslist()
                        .toArray(new lagu[0]));

        listhistori.setListData(
                mesin.gethistori()
                        .getallhistori()
                        .toArray(new lagu[0]));
    }
}
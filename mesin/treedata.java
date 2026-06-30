package mesin;

import builder.lagu;
import java.util.*;

public class treedata {

    //menyimpan lagu berdasarkan genre penyanyi dan album
    private Map<String, Map<String, Map<String, List<lagu>>>> treedata = new HashMap<>();

    public void tambah(lagu baru) {
        treedata.putIfAbsent(baru.getGenre(), new HashMap<>());
        Map<String, Map<String, List<lagu>>> penyanyi = treedata.get(baru.getGenre());

        penyanyi.putIfAbsent(baru.getPenyanyi(), new HashMap<>());
        Map<String, List<lagu>> album = penyanyi.get(baru.getPenyanyi());

        album.putIfAbsent(baru.getAlbum(), new ArrayList<>());
        List<lagu> daftar = album.get(baru.getAlbum());

        daftar.add(baru); //lagu dimasukkan sesuai genre, penyanyi, dan album menggunakan map hashing (O(1))
    }

    public List<lagu> cari(String judul) {
        List<lagu> hasil = new ArrayList<>();
        String kunci = judul.toLowerCase();

        //mencari lagu yang judulnya sesuai dengan pencarian user dengan mengecek seluruh data (O(N))
        for (Map<String, Map<String, List<lagu>>> penyanyi : treedata.values()) {
            for (Map<String, List<lagu>> album : penyanyi.values()) {
                for (List<lagu> daftar : album.values()) {
                    for (lagu lagu : daftar) {
                        if (lagu.getJudul().toLowerCase().contains(kunci)) {
                            hasil.add(lagu);
                        }
                    }
                }
            }
        }
        return hasil;
    }

    public Map<String, Map<String, Map<String, List<lagu>>>> gettree() {
        return treedata;
    }
}
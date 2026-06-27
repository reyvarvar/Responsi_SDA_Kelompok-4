package mesin;

//untuk menyimpan lagu yang sudah pernah diputar
import builder.lagu;
import java.util.*;

public class histori {

    private Deque<lagu> histori = new ArrayDeque<>();

    public void masukan(lagu putar) {
        histori.push(putar); //lagu disimpan agar bisa diputar kembali saat previous ditekan
    }

    public lagu undo() {
        return histori.pop(); //mengambil lagu terakhir dari histori
    }

    public int size() {
        return histori.size();
    }

    public boolean kosong() {
        return histori.isEmpty();
    }

    public List<lagu> getallhistori() {
        return new ArrayList<>(histori);
    }
}
package mesin;

//untuk menyimpan lagu yang sudah pernah diputar
import builder.lagu;
import java.util.*;

public class histori {

    private Deque<lagu> stackUndo = new ArrayDeque<>();
    private List<lagu> logVisual = new ArrayList<>();

    public void masukan(lagu putar) {
        stackUndo.push(putar); //lagu disimpan agar bisa diputar kembali saat previous ditekan (O(1))
        logVisual.add(0, putar); //menyimpan lagu ke list visual agar tidak hilang dari layar gui
    }

    public lagu undo() {
        return stackUndo.pop(); //mengambil lagu terakhir dari histori (O(1))
    }

    public int size() {
        return stackUndo.size();
    }

    public boolean kosong() {
        return stackUndo.isEmpty();
    }

    public List<lagu> getallhistori() {
        return logVisual; //menampilkan histori secara utuh di gui (O(1))
    }
}
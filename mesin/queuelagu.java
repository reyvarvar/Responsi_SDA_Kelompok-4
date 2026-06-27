package mesin;

//untuk mengatur antrean lagu dengan dipermudah menggunakan library java util
import builder.lagu;
import java.util.*;

public class queuelagu {

    private Deque<lagu> antrean = new ArrayDeque<>();

    public void addantrean(lagu lagu) {
        antrean.offerLast(lagu); //lagu masuk ke belakang agar menunggu giliran diputar
    }

    public void paksasekarang(lagu pilih) {
        antrean.offerFirst(pilih); //lagu dipindahkan ke depan karena user memilih putar sekarang
    }

    public lagu putar() {
        return antrean.pollFirst(); //mengambil lagu paling depan karena sudah menjadi giliran diputar
    }

    public List<lagu> getallqueueaslist() {
        return new ArrayList<>(antrean); //mengubah antrean menjadi list agar bisa ditampilkan di gui
    }

    public int size() {
        return antrean.size(); //melihat jumlah lagu yang masih ada di antrean
    }
}
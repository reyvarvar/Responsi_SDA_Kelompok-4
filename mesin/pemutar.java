package mesin;

import builder.lagu;

public class pemutar {

    //menghubungkan seluruh proses pemutar lagu
    private treedata treedata = new treedata();
    private queuelagu antrean = new queuelagu();
    private histori histori = new histori();
    private lagu sekarang = null;

    public void pilih(lagu pilih) {

        //jika sebelumnya sudah ada lagu maka simpan dulu ke histori
        if (sekarang != null) {
            histori.masukan(sekarang);
        }

        sekarang = pilih;
    }

    public lagu next() {

        //jika antrean kosong maka lagu yang sedang diputar tetap berjalan
        if (antrean.size() == 0) {
            return sekarang;
        }

        //lagu sekarang disimpan sebelum berpindah ke lagu berikutnya
        if (sekarang != null) {
            histori.masukan(sekarang);
        }

        sekarang = antrean.putar();
        return sekarang;
    }

    public lagu prev() {

        //jika histori kosong berarti belum ada lagu sebelumnya
        if (histori.kosong()) {
            return sekarang;
        }

        //lagu sekarang dikembalikan ke antrean depan sebelum memutar lagu sebelumnya
        if (sekarang != null) {
            antrean.paksasekarang(sekarang);
        }

        sekarang = histori.undo();
        return sekarang;
    }

    public String laporan() {
        return "stack : " + histori.size() + " | queue : " + antrean.size();
    }

    public treedata gettreedata() {
        return treedata;
    }

    public queuelagu getqueue() {
        return antrean;
    }

    public histori gethistori() {
        return histori;
    }

    public lagu getsekarang() {
        return sekarang;
    }
}
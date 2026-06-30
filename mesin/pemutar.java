package mesin;

import builder.lagu;

public class pemutar {

    //menghubungkan seluruh proses pemutar lagu
    private treedata treedata = new treedata();
    private queuelagu antrean = new queuelagu();
    private histori histori = new histori();
    private lagu sekarang = null; //menyimpan lagu yang sedang dimainkan

    public void pilih(lagu pilih) {
        if (sekarang != null) {
            histori.masukan(sekarang); //simpan lagu sebelumnya ke histori (O(1))
        }
        sekarang = pilih;
    }

    public lagu next() {
        if (antrean.size() == 0) {
            return sekarang; //lagu tetap berjalan jika antrean kosong
        }

        if (sekarang != null) {
            histori.masukan(sekarang); //simpan lagu sekarang sebelum ganti lagu (O(1))
        }

        sekarang = antrean.putar(); //ambil lagu terdepan dari antrean (O(1))
        return sekarang;
    }

    public lagu prev() {
        if (histori.kosong()) {
            return sekarang; //abaikan jika belum ada histori lagu
        }

        if (sekarang != null) {
            antrean.paksasekarang(sekarang); //kembalikan lagu sekarang ke antrean terdepan (O(1))
        }

        sekarang = histori.undo(); //ambil lagu terakhir dari histori (O(1))
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
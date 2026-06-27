package builder;

//konstruktor lagu
public class lagu {

    private String id;
    private String judul;
    private String penyanyi;
    private String genre;
    private int durasi;
    private String album;

    public lagu() {
    }

    //untuk mengisi data lagu yang akan digunakan pada program
    public lagu(String id, String judul, String penyanyi, String genre, int durasi, String album) {
        this.id = id;
        this.judul = judul;
        this.penyanyi = penyanyi;
        this.genre = genre;
        this.durasi = durasi;
        this.album = album;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenyanyi() {
        return penyanyi;
    }

    public void setPenyanyi(String penyanyi) {
        this.penyanyi = penyanyi;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return judul;
    }
}
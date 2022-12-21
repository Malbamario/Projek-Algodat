public class Train {
    String kode;
    Penumpang[] penumpang = new Penumpang[10];
    Route rute;
    public Train(String kode, Route rute){
        this.kode = kode;
        this.rute = rute;
    }
}

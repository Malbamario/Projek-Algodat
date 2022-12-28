public class Train {
    String kode;
    Penumpang[] penumpang = new Penumpang[10];
    Route rute;
    NodeRoute curr,start;
    Boolean isReversed;
    public Train(String kode, Route rute){
        this.kode = kode;
        this.rute = rute;
    }
}

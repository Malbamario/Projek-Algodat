public class Ticket {
    Integer jam,tarif;
    Route rute;
    String asal,tujuan;

    public Ticket(Integer jam, Integer tarif, Route rute, String asal, String tujuan){
        this.tujuan=tujuan;
        this.asal=asal;
        this.jam=jam;
        this.rute=rute;
        this.tarif=tarif;
    }
}

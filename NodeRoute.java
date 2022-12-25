public class NodeRoute {
    String namaStasiun;
    Integer tarif;
    NodeRoute next, prev;
    NodeRoute(String namaStasiun, Integer tarif){
        this.namaStasiun = namaStasiun;
        this.tarif = tarif;
    }
}
public abstract class Route {
    class NodeRoute{
        String namaStasiun;
        int tarif;
        NodeRoute next, prev;
        NodeRoute(String namaStasiun, int tarif){
            this.namaStasiun = namaStasiun;
            this.tarif = tarif;
        }
    }
    protected String name;
    NodeRoute head, curr, tail;
    public String getName() {
        return name;
    }
    public abstract void addStation(String stationName, int rates);
}

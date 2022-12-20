public class MetroSystem {
    Node stasiunHead, stasiunTail;
    Route[] rute;

    void addStation(String nama, int qty){
        Node newStation = new Node(new Station(nama, qty));
        if(stasiunHead==null) stasiunHead = stasiunTail = newStation;
        else{
            stasiunTail.next = newStation;
            newStation.prev = stasiunTail;
            stasiunTail = stasiunTail.next;
        }
    }

    // void createRoute()

    // void addRoute(String routeName, ){
    //     Station s1 = findStation(s1Name);
    //     Station s2 = findStation(s2Name);
    //     if(s1 == null || s2 == null) return;
    //     s1.railway
    // }

    Station findStation(String sName){
        Node temp = stasiunHead;
        while(temp != null){
            if(((Station)temp.obj).getName().equals(sName)) return (Station) temp.obj;
            temp = temp.next;
        }
        System.out.println("Stasiun tidak ditemukan");
        return null;
    }
}

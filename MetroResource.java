public class MetroResource {
    Node<Station> stasiunHead, stasiunTail;
    Node<Route> ruteHead, ruteTail;
    Node<Train> trainHead, trainTail;

    void addStation(String nama, int qty){
        Node<Station> newStation = new Node<Station>(new Station(nama, qty));
        if(stasiunHead==null) stasiunHead = stasiunTail = newStation;
        else{
            stasiunTail.next = newStation;
            newStation.prev = stasiunTail;
            stasiunTail = stasiunTail.next;
        }
    }

    void addRoute(String routeName, String[] stationName, int[] rates, boolean circularOrBNF){
        Route newRoute;
        if(circularOrBNF) newRoute = new CircularRoute();
        else newRoute = new BackNForthRoute();
        for(int i=0;i<rates.length;i++){
            if(validateStation(stationName[i])){
                newRoute.addStation(stationName[i], rates[i]);
            }else return;
        }
        Node<Route> newNode = new Node<Route>(newRoute);
        ruteTail.next = newNode;
        newNode.prev = ruteTail;
        ruteTail = ruteTail.next;
    }

    boolean validateStation(String stationName){
        Node<Station> temp = stasiunHead;
        while(temp.next!=null){
            if(stationName.equals(temp.obj.getName())) return false;
            temp = temp.next;
        }
        System.out.println("Stasiun "+stationName+" tidak ada");
        return false;
    }

    void addTrain(String code, String routeName, String startStation){
        Route newRoute = dupliacteRoute(routeName, startStation);
        if(newRoute==null) return;
        Node<Train> newNode = new Node<Train>(new Train(code, newRoute));
        trainTail.next = newNode;
        newNode.prev = trainTail;
        trainTail = trainTail.next;
    }

    Route dupliacteRoute(String routeName, String startStation){
        Route foundRoute = findRoute(routeName);
        if(foundRoute==null) return null;
        Route newRoute;
        if(foundRoute.getClass().getName().equals("CircularRoute")){
            newRoute = new CircularRoute();
            String headStation = newRoute.head.namaStasiun;
            while(newRoute.head.namaStasiun!=startStation){
                newRoute.head = newRoute.head.next;
                newRoute.tail = newRoute.tail.next;
                if(newRoute.head.namaStasiun.equals(headStation)){
                    System.out.println("Stasiun tidak ditemukan");
                    return null;
                }
            }
            newRoute.curr = newRoute.head;
        }
        else{
            newRoute = new BackNForthRoute();
            do{
                if(newRoute.curr.namaStasiun.equals(startStation)) break;
                
            }while(newRoute.curr.next!=null);
        }
        newRoute.head = foundRoute.head;
        newRoute.tail = foundRoute.tail;
        return newRoute;
    }

    Route findRoute(String routeName){
        Node<Route> temp = ruteHead;
        while(temp.next!=null){
            if(routeName.equals(temp.obj.getName())) return temp.obj;
            temp = temp.next;
        }
        System.out.println("Rute tidak ditemukan");
        return null;
    }

    Station findStation(String sName){
        Node<Station> temp = stasiunHead;
        while(temp != null){
            if(((Station)temp.obj).getName().equals(sName)) return (Station) temp.obj;
            temp = temp.next;
        }
        System.out.println("Stasiun tidak ditemukan");
        return null;
    }
}

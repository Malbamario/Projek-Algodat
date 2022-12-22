public class MetroResource {
    DoubleList<Station> stasiun = new DoubleList<Station>();
    DoubleList<Route> rute = new DoubleList<Route>();
    DoubleList<Train> train = new DoubleList<Train>();
    DoubleList<DoubleList<Integer>> rates = new DoubleList<DoubleList<Integer>>();

    void addStation(String nama, Integer qty){
        Node<Station> newStation = new Node<Station>(new Station(nama, qty));
        if(stasiun.head==null) stasiun.head = stasiun.curr = stasiun.tail = newStation;
        else{
            stasiun.tail.next = newStation;
            newStation.prev = stasiun.tail;
            stasiun.tail = stasiun.tail.next;
        }
    }

    void addRoute(String routeName, String[] stationName, boolean circularOrBNF){
        Route newRoute;
        if(circularOrBNF) newRoute = new CircularRoute();
        else newRoute = new BackNForthRoute();
        newRoute.name = routeName;
        for(Integer i=0;i<stationName.length;i++){
            if(validateStation(stationName[i])){
                Integer rate = 0;
                if(newRoute.tail!=null){
                    rate = findRate(newRoute.tail.namaStasiun, stationName[i]);
                }
                newRoute.addStation(stationName[i], rate);
            }else return;
        }
        Node<Route> newNode = new Node<Route>(newRoute);
        if(rute.head==null) rute.head = rute.curr = rute.tail = newNode;
        else{
            rute.tail.next = newNode;
            newNode.prev = rute.tail;
            rute.tail = rute.tail.next;
        }
    }

    boolean validateStation(String stationName){
        Node<Station> temp = stasiun.head;
        while(temp!=null){
            if(stationName.equals(temp.obj.getName())) return true;
            temp = temp.next;
        }
        System.out.println("Stasiun "+stationName+" tidak ada");
        return false;
    }

    Integer findRate(String departure, String destination){
        rates.curr = rates.head;
        stasiun.curr = stasiun.head;
        while(rates.curr!=null){
            if(departure.equals(stasiun.curr.obj.name)){
                rates.curr.obj.curr = rates.curr.obj.head;
                Node<Station> temp = stasiun.head;
                while(rates.curr.obj.curr!=null){
                    if(destination.equals(temp.obj.name)) return rates.curr.obj.curr.obj;
                    rates.curr.obj.curr = rates.curr.obj.curr.next;
                    temp = temp.next;
                }
            }
            rates.curr = rates.curr.next;
            stasiun.curr = stasiun.curr.next;
        }
        return null;
    }

    void addTrain(String code, String routeName, String startStation){
        Route foundRoute = findRoute(routeName);
        if(foundRoute==null||!validateStation(startStation)) return;
        Node<Train> newNode = new Node<Train>(new Train(code, foundRoute));
        Boolean isInRoute = setStartStation(newNode.obj, foundRoute, startStation);
        if(!isInRoute) return;
        if(train.head==null) train.head = train.curr = train.tail = newNode;
        else{
            train.tail.next = newNode;
            newNode.prev = train.tail;
            train.tail = train.tail.next;
        }
    }

    Boolean setStartStation(Train kereta, Route route, String startStation){
        route.temp = route.head;
        if(route.getClass().getName().equals("CircularRoute")){
            String headStation = route.head.namaStasiun;
            while(!route.temp.namaStasiun.equals(startStation)){
                if(route.head.namaStasiun.equals(headStation)){
                    System.out.println("Stasiun tidak ditemukan");
                    return false;
                }
                route.temp = route.temp.next;
            }
            kereta.curr = route.temp;
            return true;
        }
        else{
            while(route.temp!=null){
                if(route.temp.namaStasiun.equals(startStation)){
                    kereta.curr = route.temp;
                    return true;
                }
                route.temp = route.temp.next;
            };
            System.out.println("Stasiun tidak ditemukan");
            return false;
        }
    }

    Route findRoute(String routeName){
        Node<Route> temp = rute.head;
        while(temp!=null){
            if(routeName.equals(temp.obj.getName())) return temp.obj;
            temp = temp.next;
        }
        System.out.println("Rute "+routeName+" tidak ditemukan");
        return null;
    }

    Station findStation(String sName){
        Node<Station> temp = stasiun.head;
        while(temp != null){
            if(((Station)temp.obj).getName().equals(sName)) return (Station) temp.obj;
            temp = temp.next;
        }
        System.out.println("Stasiun tidak ditemukan");
        return null;
    }
    
    void printStation(){
        System.out.println("Stasiun: ");
        stasiun.curr = stasiun.head;
        while(stasiun.curr!=null){
            System.out.println(stasiun.curr.obj.name+" => ");
            stasiun.curr = stasiun.curr.next;
        }
    }

    void printRoute(){
        System.out.println("Rute: ");
        rute.curr = rute.head;
        while(rute.curr!=null){
            System.out.println(rute.curr.obj.name+" => ");
            rute.curr = rute.curr.next;
        }
    }

    void printTrain(){
        System.out.println("Kereta: ");
        train.curr = train.head;
        while(train.curr!=null){
            System.out.println(train.curr.obj.kode+" => ");
            train.curr = train.curr.next;
        }
    }
}

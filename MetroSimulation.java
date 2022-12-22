public class MetroSimulation {
    MetroResource resource;
    Integer openTime, closeTime;
    NodeRoute[][] schedule;
    public MetroSimulation(MetroResource resource){
        this.resource = resource;
    }
    
    void preperation(Integer openTime, Integer closeTime){
        Integer trainCount=0;
        Node<Train> tempTrain = resource.train.head;
        while(tempTrain!=null){
            Node<Station> tempStation = resource.stasiun.head;
            while(tempStation!=null){
                String startStation = tempTrain.obj.rute.head.namaStasiun;
                if(startStation.equals(tempStation.obj.getName())){
                    for(Train kereta: tempStation.obj.kereta){
                        if(kereta==null) kereta = tempTrain.obj;
                    }
                }
                tempStation = tempStation.next;
            }
            trainCount++;
            tempTrain = tempTrain.next;
        }
        schedule = new NodeRoute[closeTime-openTime][trainCount];
        for(NodeRoute[] jam : schedule){
            tempTrain = resource.train.head;
            System.out.print("[ ");
            for(NodeRoute kereta : jam){
                kereta = tempTrain.obj.rute.temp;
                System.out.print(kereta.tarif+" ");
                tempTrain.obj.rute.move();
                tempTrain = tempTrain.next;
            }
            System.out.println("]");
        }
    }
}

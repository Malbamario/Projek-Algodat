public class MetroSimulation {
    MetroResource resource;
    Integer openTime, closeTime;
    NodeRoute[][] schedule;
    public MetroSimulation(MetroResource resource){
        this.resource = resource;
    }
    
    void preperation(Integer openTime, Integer closeTime){
        this.openTime=openTime;
        this.closeTime=closeTime;
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
        for(int i=0;i<schedule.length;i++){
            tempTrain = resource.train.head;
            // System.out.print("[ ");
            for(int j=0;j<schedule[i].length;j++){
                schedule[i][j] = tempTrain.obj.rute.temp;
                // System.out.print(schedule[i][j].namaStasiun+" ");
                tempTrain.obj.rute.move();
                tempTrain = tempTrain.next;
            }
            // System.out.println("]");
        }
    }

    void start(){
        if(resource.penumpang.head!=null){
            resource.stasiun.curr=resource.stasiun.head;
            while(resource.stasiun.curr!=null){
                resource.train.curr=resource.train.head;
                while(resource.train.curr!=null){
                    // System.out.println(resource.stasiun.curr.obj.name);
                    // Node<Train> tempTrain = resource.train.head;
                    // while(tempTrain!=null){
                    //     System.out.print(tempTrain.obj.kode);
                    //     if(tempTrain!=resource.train.tail) System.out.print(" => ");
                    //     tempTrain = tempTrain.next;
                    // }
                    // System.out.println();
                    Node<Train> tempTrain = resource.train.curr;
                    if(resource.train.curr.obj.curr.namaStasiun.equals(resource.stasiun.curr.obj.name)){
                        resource.train.curr=resource.train.curr.next;
                        Train deletedTrain=resource.train.delete(tempTrain.obj);
                        resource.stasiun.curr.obj.addKereta(deletedTrain);
                    }else resource.train.curr=resource.train.curr.next;
                }
                resource.stasiun.curr=resource.stasiun.curr.next;
            }

            for(Integer i=openTime;i<=closeTime;i++){
                resource.stasiun.curr=resource.stasiun.head;
                String time = i+".00";
                if(i<10) time = "0"+time;
                System.out.println(time);
                int nomorStasiun = 0;
                while(resource.stasiun.curr!=null){
                    nomorStasiun++;
                    Station tempStation = resource.stasiun.curr.obj;
                    resource.penumpang.curr=resource.penumpang.head;
                    while(resource.penumpang.curr!=null){
                        // Node<Penumpang> tempPenumpang = resource.penumpang.head;
                        // while(tempPenumpang!=null){
                        //     System.out.print(tempPenumpang.obj.name);
                        //     if(tempPenumpang!=resource.penumpang.tail) System.out.print(" => ");
                        //     tempPenumpang = tempPenumpang.next;
                        // }
                        // System.out.println();
                        // tempPenumpang = resource.penumpang.curr;
                        Node<Penumpang> tempPenumpang = resource.penumpang.curr;
                        // System.out.print(tempPenumpang.obj.name);
                        if(resource.penumpang.curr.obj.asal.equals(tempStation.name)&&tempPenumpang.obj.tiket.jam==i){
                            resource.penumpang.curr=resource.penumpang.curr.next;
                            // System.out.print(tempPenumpang.obj.name);
                            Penumpang deletedPenumpang=resource.penumpang.delete(tempPenumpang.obj);
                            // System.out.print(tempPenumpang.obj.name);
                            // System.out.print(deletedPenumpang.name);
                            tempStation.waiting.addTail(deletedPenumpang);
                            // resource.penumpang.delete(resource.penumpang.curr.obj);
                        } else resource.penumpang.curr=resource.penumpang.curr.next;
                    }
                    tempStation.swapPassanger();
                    System.out.println(nomorStasiun+". "+tempStation.name);
                    Node<Penumpang> tempPenumpang = tempStation.waiting.head;
                    System.out.print("    Waiting List: ");
                    while(tempPenumpang!=null){
                        System.out.print(tempPenumpang.obj.name);
                        if(tempPenumpang!=tempStation.waiting.tail) System.out.print(", ");
                        tempPenumpang = tempPenumpang.next;
                    }
                    System.out.println();
                    System.out.println("    Kereta:");
                    for(int j=0;j<tempStation.kereta.length;j++){
                        if(tempStation.kereta[j]!=null){
                            System.out.print("        "+tempStation.kereta[j].kode+": ");
                            for(int k=0;k<tempStation.kereta[j].penumpang.length;k++){
                                if(tempStation.kereta[j].penumpang[k]!=null){
                                    System.out.println(tempStation.kereta[j].penumpang[k].name);
                                    if(tempPenumpang!=tempStation.waiting.tail) System.out.print(", ");
                                }
                            } System.out.println();
                        }
                    }

                    for(int j=0;j<tempStation.kereta.length;j++){
                        if(tempStation.kereta[j]!=null){
                            tempStation.kereta[j].rute.temp = tempStation.kereta[j].curr;
                            tempStation.kereta[j].rute.move(); 
                            tempStation.kereta[j].curr = tempStation.kereta[j].rute.temp; 
                            resource.train.addTail(tempStation.kereta[j]);
                            tempStation.kereta[j]=null;
                        }
                    }
                    
                    if(resource.train.head!=null){
                        resource.train.curr = resource.train.head;
                        while(resource.train.curr!=null){
                            Train tempKereta = resource.train.curr.obj;
                            if(tempKereta.curr.namaStasiun.equals(tempStation.name)){
                                Train deletedKereta = resource.train.delete(tempKereta);
                                tempStation.addKereta(deletedKereta);
                            }
                            resource.train.curr = resource.train.curr.next;
                        }
                    }
                    resource.stasiun.curr=resource.stasiun.curr.next;
                }

            }
        }else System.out.println("Beli tiket terlebih dahulu");
    }
}

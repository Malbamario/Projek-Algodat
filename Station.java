public class Station {
    protected String name;
    Train[] kereta;
    DoubleList<Penumpang> waiting;

    public Station(String nama, Integer qty){
        this.kereta = new Train[qty];
        this.name = nama;
    }

    public String getName() {
        return name;
    }

    public Train getTrainByCode(String trainCode){
        for(Train kereta : kereta){
            if(kereta.kode==trainCode) return kereta;
        }
        System.out.println("Kereta tidak ditemukan");
        return null;
    }

    public Train getTrainByNextDest(String destination){
        for(Train kereta : kereta){
            if(kereta.rute.temp.next.namaStasiun==destination) return kereta;
        }
        System.out.println("Kereta tidak ditemukan");
        return null;
    }

    void swapPassanger(){
        for(Train kereta: kereta){
            for(Integer i=0;i<kereta.penumpang.length;i++){
                if(kereta.penumpang[i].rute.temp.next==null) kereta.penumpang[i] = null;
                else if(kereta.penumpang[i].rute.temp.next!=kereta.rute.temp.next){
                    Node<Penumpang> newNode = new Node<Penumpang>(kereta.penumpang[i]);
                    waiting.tail.next = newNode;
                    newNode.prev = waiting.tail;
                    waiting.tail = waiting.tail.next;
                    kereta.penumpang[i] = null;
                }
            }

            Node<Penumpang> waitingTemp = waiting.head;
            while(waitingTemp!=null){
                if(kereta.rute.temp.next==waitingTemp.obj.rute.temp.next){
                    for(Penumpang p : kereta.penumpang){
                        if(p==null){
                            p = (Penumpang) waitingTemp.obj;
                            waitingTemp.prev.next = waitingTemp.next;
                            waitingTemp.next.prev = waitingTemp.prev;
                            break;
                        }
                    }

                }
                waitingTemp = waitingTemp.next;
            }
        }
    }
}

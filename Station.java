public class Station {
    protected String name;
    Train[] kereta;
    Node<Penumpang> waitingHead, waitingTail;

    public Station(String nama, int qty){
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
            if(kereta.rute.curr.next.namaStasiun==destination) return kereta;
        }
        System.out.println("Kereta tidak ditemukan");
        return null;
    }

    void swapPassanger(){
        for(Train kereta: kereta){
            for(int i=0;i<kereta.penumpang.length;i++){
                if(kereta.penumpang[i].rute.curr.next==null) kereta.penumpang[i] = null;
                else if(kereta.penumpang[i].rute.curr.next!=kereta.rute.curr.next){
                    Node<Penumpang> newNode = new Node<Penumpang>(kereta.penumpang[i]);
                    waitingTail.next = newNode;
                    newNode.prev = waitingTail;
                    waitingTail = waitingTail.next;
                    kereta.penumpang[i] = null;
                }
            }

            Node<Penumpang> waitingTemp = waitingHead;
            while(waitingTemp!=null){
                if(kereta.rute.curr.next==waitingTemp.obj.rute.curr.next){
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

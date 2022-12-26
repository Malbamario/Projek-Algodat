public class Station {
    protected String name;
    Train[] kereta;
    DoubleList<Penumpang> waiting = new DoubleList<Penumpang>();

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
        for(Train k: kereta){
            if(k!=null){
                for(Integer i=0;i<k.penumpang.length;i++){
                    Penumpang thisPenumpang = k.penumpang[i];
                    if(thisPenumpang!=null){
                        if(k.curr.namaStasiun.equals(thisPenumpang.tujuan)) k.penumpang[i] = null;
                        // else if(thisPenumpang.rute.temp.next!=k.rute.temp.next){
                        //     waiting.addTail(thisPenumpang);
                        //     k.penumpang[i] = null;
                        // }
                    }
                }
    
                Node<Penumpang> waitingTemp = waiting.head;
                while(waitingTemp!=null){
                    if(k.rute.name.equals(waitingTemp.obj.tiket.rute.name)){
                        for(int i=0;i<k.penumpang.length;i++){
                            if(k.penumpang[i]==null){
                                k.penumpang[i] = waiting.delete(waitingTemp.obj);
                            }
                        }
    
                    }
                    waitingTemp = waitingTemp.next;
                }
            }
        }
    }

    void addKereta(Train kereta){
        for(int i=0;i<this.kereta.length;i++){
            if(this.kereta[i]==null){
                this.kereta[i]=kereta;
                // System.out.println("Kereta "+kereta.kode+" berhasil ditambah!");
                return;
            }
        }
        System.out.println("Peron stasiun penuh!");
    }
}

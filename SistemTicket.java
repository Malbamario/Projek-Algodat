import java.util.*;

public class SistemTicket {
    MetroResource mr;
    MetroSimulation ms;
    int openTime,closeTime;
    DoubleList<Ticket> daftarTicket=new DoubleList<Ticket>();
    Scanner input = new Scanner(System.in);
    
    public SistemTicket(MetroResource mr, MetroSimulation ms, int open, int close){
        this.mr=mr;
        this.ms=ms;
        this.openTime=open;
        this.closeTime=close;
    }

    public void beliTiket(){
        System.out.println("Jam Operasional: "+MetroSimulation.printTime(openTime)+" - "+MetroSimulation.printTime(closeTime-1));
        mr.printStation();
        System.out.print("Masukkan nama: ");
        String namaPenumpang=input.nextLine();
        boolean isCorrect = false;
        String asal="", tujuan="";
        Integer jam=0;
        while(!isCorrect){
            System.out.print("Masukkan stasiun asal: ");
            asal=input.nextLine();
            isCorrect = mr.validateStation(asal);
        }
        isCorrect = false;
        while(!isCorrect){
            System.out.print("Masukkan stasiun tujuan: ");
            tujuan=input.nextLine();
            isCorrect = mr.validateStation(tujuan);
        }
        isCorrect = false;
        while(!isCorrect){
            System.out.print("Masukkan jam keberangkatan: ");
            jam=Integer.parseInt(input.nextLine());
            if(jam<openTime||jam>=closeTime){
                System.out.println("Jam keberangkatan di luar jam operasional");
            } else isCorrect = true;
        }
        Main.clearScreen();
        
        daftarTicket = new DoubleList<Ticket>();
        for(int i=0;i<ms.schedule.length;i++){
            mr.train.curr = mr.train.head;
            Integer now=i+openTime;
            if(jam==now){
                DoubleList<Train> foundTrain=new DoubleList<Train>();
                for(int j=0;j<ms.schedule[i].length;j++){
                    if(ms.schedule[i][j].namaStasiun.toLowerCase().equals(asal.toLowerCase())){
                        boolean isDestFound=false;
                        for(int k=0;k<ms.schedule.length;k++){
                            if(ms.schedule[k][j].namaStasiun.toLowerCase().equals(tujuan.toLowerCase())){
                                isDestFound=true;
                            }
                        }
                        if(isDestFound) foundTrain.addTail(mr.train.curr.obj);
                    }
                    mr.train.curr=mr.train.curr.next;
                }
                while(foundTrain.curr!=null){
                    Integer tarif=0;
                    foundTrain.curr.obj.rute.temp=foundTrain.curr.obj.rute.head;
                    do{
                        tarif+=foundTrain.curr.obj.rute.temp.tarif;
                        foundTrain.curr.obj.rute.move();
                    }
                    while(foundTrain.curr.obj.rute.temp!=foundTrain.curr.obj.rute.head);
                    daftarTicket.addTail(new Ticket(now, tarif, foundTrain.curr.obj.rute, asal, tujuan));
                    foundTrain.curr=foundTrain.curr.next;
                }
                break;
            }
        }
        
        sortByTarif();
        Integer nomor=0;
        if(daftarTicket.head!=null){
            System.out.println("Daftar Tiket:");
            // Integer nomor=0;
            while(daftarTicket.curr!=null){
                nomor++;
                System.out.println(nomor+") "+daftarTicket.curr.obj.rute.name+": Rp. "+daftarTicket.curr.obj.tarif+".000");
                daftarTicket.curr=daftarTicket.curr.next;
            }
            System.out.println();
            while(true){
                isCorrect=true;
                System.out.print("Masukkan nomor rute: ");
                Integer nomorTiket=Integer.parseInt(input.nextLine());
                Integer i = 0;
                daftarTicket.curr=daftarTicket.head;
                while(isCorrect){
                    i++;
                    if(i==nomorTiket){
                        mr.penumpang.addTail(new Penumpang(namaPenumpang, asal, tujuan, daftarTicket.curr.obj));
                        System.out.println("Penumpang berhasil ditambah!");
                        input.nextLine();
                        Main.clearScreen();
                        break;
                    }else if(nomorTiket<1||nomorTiket>nomor||daftarTicket.curr==null||i>nomor){
                        System.out.println("Rute yang dipilih tidak terdaftar");
                        isCorrect=false;
                    }else daftarTicket.curr=daftarTicket.curr.next;             
                }
                if(daftarTicket.curr!=null&&isCorrect) break;
            }

        }else{
            System.out.println("Tiket tidak ada atau telah habis");
            System.out.println("1. Masukkan ulang");
            System.out.println("2. Kembali");
            isCorrect=false;
            while(!isCorrect){
                System.out.print("Pilih: ");
                Integer pilihan=Integer.parseInt(input.nextLine());
                System.out.println();
        
                switch (pilihan) {
                case 1:
                    isCorrect=true;
                    Main.clearScreen();
                    beliTiket();
                    break;
                case 2:
                    isCorrect=true;
                    return;
                default:
                    System.out.println("Masukkan input yang benar!");
                }
            }
        }
    }

    void sortByTarif(){ //quicksort
        quicksort(daftarTicket.head, daftarTicket.tail);
    }

    int rekursif = 0;
    void quicksort(Node<Ticket> head, Node<Ticket> tail){
        // System.out.print("\nsorting ke-"+rekursif+": ");
        if(head==null||tail==null||tail==head)return;
        Node<Ticket> pivot = head, temp = tail;
        Object pivotObj = pivot.obj;
        // System.out.print(((Barang)pivot.obj).nama+" & "+((Barang)temp.obj).nama);
        do{
            if((pivot.obj).tarif>(temp.obj).tarif) swap(pivot, temp);
            if(pivot.obj == pivotObj)temp=temp.prev;
            else pivot=pivot.next;
        } while(pivot!=temp);
        Node<Ticket> kananHead = pivot.next;
        Node<Ticket> kiriTail = pivot.prev;
        if(kananHead != null) kananHead.prev = null;
        if(kiriTail != null) kiriTail.next = null;
        quicksort(head, kiriTail);
        quicksort(kananHead, tail);
        if(kananHead != null) kananHead.prev = pivot;
        if(kiriTail != null) kiriTail.next = pivot;
    }

    void swap(Node<Ticket> node1,Node<Ticket> node2){
        Node<Ticket> newNode = new Node<Ticket>(node1.obj);
        node1.obj = node2.obj;
        node2.obj= newNode.obj;
    }
}


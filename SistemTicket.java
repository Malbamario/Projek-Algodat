import java.util.*;

public class SistemTicket {
    MetroResource mr;
    MetroSimulation ms;
    int openTime,closeTime;
    
    public SistemTicket(MetroResource mr, MetroSimulation ms, int open, int close){
        this.mr=mr;
        this.ms=ms;
        this.openTime=open;
        this.closeTime=close;
    }

    public void beliTiket(){
        Scanner sd = new Scanner(System.in);
        Scanner sx = new Scanner(System.in);
        System.out.print("Masukkan nama: ");
        String namaPenumpang=sd.nextLine();
        System.out.print("Masukkan stasiun asal: ");
        String asal=sx.nextLine();
        System.out.print("Masukkan stasiun tujuan: ");
        String tujuan=sx.nextLine();
        System.out.print("Masukkan jam keberangkatan: ");
        Integer jam=Integer.parseInt(sx.nextLine());

        System.out.println("Daftar Tiket:");
        DoubleList<Ticket> daftarTicket=new DoubleList<Ticket>();
        for(int i=0;i<ms.schedule.length;i++){
            mr.train.curr = mr.train.head;
            Integer now=i+openTime;
            if(jam==now){
                DoubleList<Train> foundTrain=new DoubleList<Train>();
                for(int j=0;j<ms.schedule[i].length;j++){
                    if(ms.schedule[i][j].namaStasiun.equals(asal)){
                        boolean isDestFound=false;
                        for(int k=0;k<ms.schedule.length;k++){
                            if(ms.schedule[k][j].namaStasiun.equals(tujuan)){
                                isDestFound=true;
                            }
                        }
                        if(isDestFound){
                            foundTrain.addTail(mr.train.curr.obj);
                            System.out.println(ms.schedule[i][j].namaStasiun);
                        }
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
        Integer nomor=0;
        // sorting by tarif
        while(daftarTicket.curr!=null){
            nomor++;
            System.out.println(nomor+") "+daftarTicket.curr.obj.jam+".00");
            System.out.println(daftarTicket.curr.obj.rute.name);
            System.out.println(daftarTicket.curr.obj.tarif);
            daftarTicket.curr=daftarTicket.curr.next;
        }
        
        Scanner st = new Scanner(System.in);
        System.out.println("1. Pilih Tiket");
        System.out.println("2. Masukkan ulang");
        System.out.println("3. Kembali");
        System.out.print("Pilih: ");
        int input=st.nextInt();

        switch (input) {
        case 1:
            Scanner sy = new Scanner(System.in);
            System.out.print("Masukkan Nama Rute:");
            String namaRute=sy.nextLine();
            daftarTicket.curr=daftarTicket.head;
            while(daftarTicket.curr!=null){
                if(namaRute.equals(daftarTicket.curr.obj.rute.name)){
                    mr.penumpang.addTail(new Penumpang(namaPenumpang, asal, tujuan, daftarTicket.curr.obj));
                    System.out.println("Penumpang berhasil ditambah!");
                }
                daftarTicket.curr=daftarTicket.curr.next;
            }
            break;
        case 2:
            this.beliTiket();
            break;
        case 3:
            return;
        default:
            System.out.println("Masukkan input yang benar!");
            this.beliTiket();
        }
    }
}


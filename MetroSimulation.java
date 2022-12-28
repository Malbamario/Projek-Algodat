import java.util.Scanner;

public class MetroSimulation {
    MetroResource resource;
    static Integer openTime, closeTime;
    NodeRoute[][] schedule;
    Scanner input = new Scanner(System.in);
    public MetroSimulation(MetroResource resource){
        this.resource = resource;
    }
    
    void preperation(Integer openTime, Integer closeTime){
        MetroSimulation.openTime=openTime;
        MetroSimulation.closeTime=closeTime;
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
            System.out.print("[ ");
            for(int j=0;j<schedule[i].length;j++){
                schedule[i][j] = tempTrain.obj.rute.temp;
                System.out.print(schedule[i][j].namaStasiun+" ");
                // tempTrain.obj.rute.move();
                tempTrain = tempTrain.next;
            }
            System.out.print("]");
            tempTrain = resource.train.head;
            System.out.print("[ ");
            
            for(int j=0;j<schedule[i].length;j++){
                schedule[i][j] = tempTrain.obj.rute.temp;
                System.out.print(schedule[i][j].tarif+" ");
                tempTrain.obj.rute.move();
                tempTrain = tempTrain.next;
            }
            System.out.println("]");
        }
    }

    void start(){
        if(resource.penumpang.head!=null){
            for(Integer i=openTime;i<closeTime;i++){
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
                            if(deletedTrain==null) System.out.println("Kereta tidak ditemukan!");
                            resource.stasiun.curr.obj.addKereta(deletedTrain);
                        }else resource.train.curr=resource.train.curr.next;
                    }
                    resource.stasiun.curr=resource.stasiun.curr.next;
                }
                resource.stasiun.curr=resource.stasiun.head;

                int nomorStasiun = 0;
                String time = MetroSimulation.printTime(i);
                MetroSimulation.printVerticalLine(i-1);
                System.out.println("╔═══════╗");
                if(i==openTime) System.out.print("┌─");
                else if(i==closeTime-1) System.out.print("└─");
                else System.out.print("├─");
                System.out.println("║ "+time+" ║");
                MetroSimulation.printVerticalLine(i);
                System.out.println("╚═══════╝");
                while(resource.stasiun.curr!=null){
                    nomorStasiun++;
                    Station tempStation = resource.stasiun.curr.obj;
                    MetroSimulation.printVerticalLine(i);
                    System.out.println(" "+nomorStasiun+". Stasiun "+tempStation.name);
                    
                    resource.penumpang.curr=resource.penumpang.head;
                    while(resource.penumpang.curr!=null){
                        Node<Penumpang> tempPenumpang = resource.penumpang.curr;
                        if(resource.penumpang.curr.obj.asal.equals(tempStation.name)&&tempPenumpang.obj.tiket.jam==i){
                            resource.penumpang.curr=resource.penumpang.curr.next;
                            Penumpang deletedPenumpang=resource.penumpang.delete(tempPenumpang.obj);
                            if(deletedPenumpang==null) System.out.println("Penumpang tidak ditemukan!");
                            tempStation.waiting.addTail(deletedPenumpang);
                        } else resource.penumpang.curr=resource.penumpang.curr.next;
                    }
                    tempStation.swapPassanger(i);
                    
                    Node<Penumpang> tempPenumpang = tempStation.waiting.head;
                    if(tempPenumpang!=null){
                        MetroSimulation.printVerticalLine(i);
                        System.out.print("    Waiting List: ");
                        while(tempPenumpang!=null){
                            System.out.print(tempPenumpang.obj.name);
                            if(tempPenumpang!=tempStation.waiting.tail) System.out.print(", ");
                            tempPenumpang = tempPenumpang.next;
                        }
                        System.out.println();
                    }
                    if(tempStation.kereta[0]!=null){
                        MetroSimulation.printVerticalLine(i);
                        System.out.println("    Kereta:");
                        for(int j=0;j<tempStation.kereta.length;j++){
                            Train keretaJ = tempStation.kereta[j];
                            if(keretaJ!=null){
                                MetroSimulation.printVerticalLine(i);
                                System.out.print("       "+keretaJ.kode);
                                if(keretaJ.penumpang[0]!=null){
                                    System.out.print(": ");
                                    for(int k=0;k<keretaJ.penumpang.length;k++){
                                        if(keretaJ.penumpang[k]!=null){
                                            System.out.print(keretaJ.penumpang[k].name);
                                            if(k+1!=keretaJ.penumpang.length){
                                                if(keretaJ.penumpang[k+1]!=null) System.out.print(", ");
                                            }
                                        }
                                    } 
                                } System.out.println();
                            }
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
                                if(deletedKereta==null) System.out.println("Kereta tidak ditemukan!");
                                tempStation.addKereta(deletedKereta);
                            }
                            resource.train.curr = resource.train.curr.next;
                        }
                    }
                    resource.stasiun.curr=resource.stasiun.curr.next;
                }
                MetroSimulation.printVerticalLine(i);
                System.out.println();
                MetroSimulation.printVerticalLine(i);
                System.out.println();
            }
        }else System.out.println("Beli tiket terlebih dahulu");
        System.out.println("Press enter to conitnue ...");
        input.nextLine();
        Main.clearScreen();
    }
    static void printVerticalLine(Integer i){
        if(i<closeTime-1&&i>=openTime) System.out.print("│ ");
        else System.out.print("  ");
    }

    static String printTime(Integer i){
        String time = i+".00";
        if(i<10) time = "0"+time;
        return time;
    }
}

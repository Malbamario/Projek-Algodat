import java.util.*;

public class Main{
    static MetroResource resource = new MetroResource();
    static MetroSimulation simulation = new MetroSimulation(resource);
    static SistemTicket ticketing = new SistemTicket(resource,simulation,6,22);
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        scenario();
        simulation.preperation(6, 23);
        menu();
    }

    public static void scenario(){
        String[] stationArr = {"A", "B", "C", "D", "E"};
        DoubleList<String> station = new DoubleList<String>();
        station.addTail(stationArr);
        
        Integer[] qtyArr = {3, 3, 3, 4, 3};
        DoubleList<Integer> qty = new DoubleList<Integer>();
        qty.addTail(qtyArr);

        Integer[][] ratesArr = {
            {0, 7, 5, 5, 0},
            {7, 0, 0, 4, 6},
            {5, 0, 0, 6, 6},
            {5, 4, 6, 0, 5},
            {0, 6, 6, 5, 0}
        };

        String[] routesNameArr = {"Palapa-Red Line", "Palapa-White Line", "Sabang-Merauke Line", "Mangias-Rote Line"};
        DoubleList<String> routesName = new DoubleList<String>();
        routesName.addTail(routesNameArr);
        Boolean[] isCircular = {true, true, false, false};

        String[][] routeStationArr = {
            {stationArr[0], stationArr[1], stationArr[4], stationArr[2]},
            {stationArr[2], stationArr[4], stationArr[1], stationArr[0]},
            {stationArr[0], stationArr[3], stationArr[4]},
            {stationArr[1], stationArr[3], stationArr[2]},
        };

        String[] trainCodeArr = {"PR-01", "PW-03", "SM-00", "MR-41"};
        DoubleList<String> trainCode = new DoubleList<String>();
        trainCode.addTail(trainCodeArr);
        Integer[] trainRouteArr = {0, 1, 2, 3};
        DoubleList<Integer> trainRoute = new DoubleList<Integer>();
        trainRoute.addTail(trainRouteArr);
        Integer[] trainStartArr = {0, 2, 0, 3};
        DoubleList<Integer> trainStart = new DoubleList<Integer>();
        trainStart.addTail(trainStartArr);
        
        while(station.curr!=null){
            resource.addStation(station.curr.obj, qty.curr.obj);
            station.curr = station.curr.next;
            qty.curr = qty.curr.next;
        }

        for(Integer[] rate: ratesArr){
            DoubleList<Integer> newNode = new DoubleList<Integer>();
            newNode.addTail(rate);
            resource.rates.addTail(newNode);
        }

        for(Integer i=0;i<routeStationArr.length;i++){
            resource.addRoute(routesName.curr.obj, routeStationArr[i], isCircular[i]);
            routesName.curr = routesName.curr.next;
        }

        while(trainCode.curr!=null){
            resource.addTrain(
                trainCode.curr.obj, 
                routesNameArr[trainRoute.curr.obj],
                stationArr[trainStart.curr.obj]
            );
            trainCode.curr = trainCode.curr.next;
            trainRoute.curr = trainRoute.curr.next;
            trainStart.curr = trainStart.curr.next;
        }
    }

    public static void menu(){
        boolean finish=false;
        System.out.println("Menu");
        System.out.println("1. Beli Tiket");
        System.out.println("2. Simulasi");
        System.out.println("3. Keluar");
        System.out.print("Pilih Menu: ");
        Integer pilihan=Integer.parseInt(input.nextLine());
        System.out.println();
        Main.clearScreen();
        
        switch (pilihan) {
        case 1:
            ticketing.beliTiket();
            break;
        case 2:
            simulation.start();
            break;
        case 3:
            finish=true;
            break;
        default:
            System.out.println("Masukkan input yang benar!");
            menu();
        }
        if(!finish){
            menu();
        }
    }

    static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
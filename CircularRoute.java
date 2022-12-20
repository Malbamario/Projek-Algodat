public class CircularRoute extends Route {
    void addStation(String stationName, int rates){
        NodeRoute newNode = new NodeRoute(stationName, rates);
        if(head==null) this.head = this.tail = newNode;
        else{
            tail.next = newNode;
            tail = tail.next;
            tail.next = head;
        }
    }
}

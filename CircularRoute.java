public class CircularRoute extends Route {
    public void addStation(String stationName, Integer rates){
        NodeRoute newNode = new NodeRoute(stationName, rates);
        if(head==null) this.head = this.tail = newNode;
        else{
            tail.next = newNode;
            tail = tail.next;
            tail.next = head;
        }
    }
}

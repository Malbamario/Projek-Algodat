public class CircularRoute extends Route {
    public void addStation(String stationName, Integer rates){
        NodeRoute newNode = new NodeRoute(stationName, rates);
        if(head==null) this.head = this.tail = this.temp = newNode;
        else{
            tail.next = newNode;
            tail = tail.next;
            tail.next = head;
        }
    }
    public void move(){
        temp = temp.next;
    }

    public NodeRoute move(Train t){
        return t.curr.next;
    }
}

public class BackNForthRoute extends Route {
    public void addStation(String stationName, int rates){
        NodeRoute newNode = new NodeRoute(stationName, rates);
        if(head==null) this.head = this.tail = this.curr = newNode;
        else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = tail.next;
        }
    }
}

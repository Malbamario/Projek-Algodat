public class BackNForthRoute extends Route {
    Boolean isReversed = false;
    public void addStation(String stationName, Integer rates){
        NodeRoute newNode = new NodeRoute(stationName, rates);
        if(head==null) this.head = this.tail = this.temp = newNode;
        else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = tail.next;
        }
    }
    public void move(){
        if(temp==tail) isReversed = true;
        else if(temp==head) isReversed = false;
        if(!isReversed) temp = temp.next;
        else temp = temp.prev;
    }
}

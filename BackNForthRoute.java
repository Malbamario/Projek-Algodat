public class BackNForthRoute extends Route {
    public void addStation(String stationName, Integer rates){
        NodeRoute newNode = new NodeRoute(stationName, rates);
        if(head==null) this.head = this.tail = this.temp = newNode;
        else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = tail.next;
        }
    }

    public NodeRoute move(Train t){
        if(t.curr==tail) t.isReversed = true;
        else if(t.curr==head) t.isReversed = false;
        if(!t.isReversed) return t.curr.next;
        else return t.curr.prev;
    }
}

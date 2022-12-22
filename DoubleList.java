public class DoubleList<T>{
    Node<T> head, curr, tail;
    void addTail(T obj){
        Node<T> newNode = new Node<T>(obj);
        if(head==null) head = curr = tail = newNode;
        else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = tail.next;
        }
    }

    void addTail(T[] objects){
        for(T obj : objects) addTail(obj);
    }
}

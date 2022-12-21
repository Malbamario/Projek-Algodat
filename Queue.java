public class Queue<T> {
    Node<T> head, tail;

    void enQueue(T obj) {
        Node<T> newNode = new Node<T>(obj);
        tail.next = newNode;
        newNode.prev = tail;
        tail = tail.next;
    }

    Object deQueue(){
        Node<T> temp = head;
        temp.prev.next = null;
        head = temp.next;
        temp.next = null;
        return temp.obj;
    }
}

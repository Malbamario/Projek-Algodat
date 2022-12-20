public class Queue {
    Node head, tail;

    void enQueue(Object obj) {
        Node newNode = new Node(obj);
        tail.next = newNode;
        newNode.prev = tail;
        tail = tail.next;
    }

    Object deQueue(){
        Node temp = head;
        temp.prev.next = null;
        head = temp.next;
        temp.next = null;
        return temp.obj;
    }
}

public class DoubleList<T>{
    //script ini adalah class yang bernama DoubleList yang dimana class ini berfungsi sebagai node dari double linked list, class ini merupakan
    //jenis
    Node<T> head, curr, tail;
    //script ini adalah variabel yang bertipe data Node yang memiliki nilai index T
    void addTail(T obj){
        //script ini adalah method yang memiliki parameter variabel obj dengan tipe data index T, method ini berfungsi untuk menambahkan data
        Node<T> newNode = new Node<T>(obj);
        //script ini adalah mendeklarasikan variabel newNode yang bertipe data Node dengan index T yang dimana node newNode ini adalah node baru yang berisi data
        if(head==null) head = curr = tail = newNode;
        //script ini adalah statement jika data awal atau head adalah kosong, maka data awal atau head, node curr dan node tail adalah data baru newnode
        else{
            //script ini adalah statement jika if tidak terpenuhi
            tail.next = newNode;
            //script ini adalah menginisialisasi node tail selanjutnya adalah node newNode
            newNode.prev = tail;
            //script ini adalah menginisialisasi node newNode sebelumnya adalah node tail
            tail = tail.next;
            //script ini adalah menginisialisasi node tail adalah node tail selanjutnya
        }
    }

    void addTail(T[] objects){
        for(T obj : objects) addTail(obj);
    }
}

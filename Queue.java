public class Queue<T> {
    //script ini adalah pembuatan class Queue yang diamana class ini bersifat public yang dapat diakses
    //oleh class lainnya, class ini bertipe data generic yang memiliki nilai index T
    Node<T> head, tail;
    //script ini adalah variabel head dan tail yang bertipe data Node 

    void enQueue(T obj) {
        //script ini adlalah method yang berfungsi untuk menambahkan data inputan yang memiliki parameter
        //variabel obj yang bertipe data index T
        Node<T> newNode = new Node<T>(obj);
        //script ini adalah menginisialisasikan variabel newNode sebagai node baru yang berisi data dari variabel obj
        tail.next = newNode;
        //script ini menginisialisasi node tail selanjutna sebagai node newnode
        newNode.prev = tail;
         //script ini menginisialisasi node newNode sebelumnya sebagai tail
        tail = tail.next;
        //script ini adalah adalah menginisialisasi tail sebagai tail selanjutna
    }

    Object deQueue(){
        //script ini adalah variabel deQueue dengan tipe data object yang dimana befungsi untuk menghapus
        //data yang paling awal masuk
        Node<T> temp = head;
        //script ini adlaah menginisialisasikan variabel temp sebagai head
        temp.prev.next = null;
        //script ini adlaah memberikan  nilai temp sebelum dan selanjutnya adalah kosong
        head = temp.next;
        //script ini adalah node head adalah node temp selanjutnya
        temp.next = null;
        //script ini adalah memberikan nilai node temp selanjutnya adalah kosong
        return temp.obj;
        //script ini adalah mengembalikan nilai temp yang berisi nilai variabel obj
    }
}

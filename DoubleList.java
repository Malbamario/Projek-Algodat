public class DoubleList<T>{
    //script ini adalah class DoubleList yang dimana class ini bertipe data 
    //generic dengan nilai index T.
    Node<T> head, curr, tail;
    //script ini adalah variabel head, curr dan tail yang bertipe data Node dengan  nilai index T
    //sebagai tipe data genericnya
    void addTail(T obj){
        //script ini adalah method yang berfungsi untuk menginputkan data pada bagian akhir dari data, method ini memiliki parameter v
        //variabel obj dengan tipe data generic index T
        Node<T> newNode = new Node<T>(obj);
        //script ini adalah variabel newNode dengan tipe data generic Node yang di inisialisasikan sebagai node baru yang
        //berisi nilai dari variabel obj
        if(head==null) head = curr = tail = newNode;
        //script ini adalah statement jika data awal kosong, maka head, curr dan tail adalah data baru node newNode
        else{
            //script ini adalah statement selain dari statment if
            tail.next = newNode;
            //script ini adalah menginisialisasikan node tail selanjutnya adalah node newNode
            newNode.prev = tail;
            //script ini adalah mebginisialisasikan newNode sebelumnya adalah node tail
            tail = tail.next;
            //script ini menginisialisasikan node tail adalah tail selanjutnyah
        }
    }

    void addTail(T[] objects){
        for(T obj : objects) addTail(obj);
    }
}

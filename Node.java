public class Node<T> {
    //script ini adalah class Node yang bertipe data generic dengan index T
    T obj;
    //script ini adalah varaiabel obj dengan tipe data generic dengan index T
    Node<T> next, prev;
    //script ini adalah membuat pointer next dan prev yang bertipe datakan Node dengan nilai index T
    public Node(T obj){
        //script ini adalah constructor Node dengan parameter di dalamnya yaitu variabel objek yang bertipe data index T
        this.obj = obj;
        //script ini adalah menginisialisasi variabel obj yang bernilai obj
    }
}

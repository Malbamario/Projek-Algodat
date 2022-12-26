/*script ini adalah class stasuiun yang dimana terdapat tipe data yang berjenis protected, Train yang bertipe array dan DoubleList dengan
 * index data Penumpang, kemudian terdapat constructur yang memiliki parameter variabel yang bertipedata String dan qty yang bertipe data integer, di dalam constructor akan dilakukan penginisialisasian variabel kereta yang bertpe data Train tipe array yang adalah Train dengan 
 * index data array berdasarkan jumlah qty dan menginisialisasikan variabel nama pada class Station pada variabel nama yang ada ada pada parameter constructor
 */
public class Station {
    protected String name;
    Train[] kereta;
    DoubleList<Penumpang> waiting;

    public Station(String nama, Integer qty) {
        this.kereta = new Train[qty];
        this.name = nama;
    }
    /*script ini adalah method getName yang bertipe data String yang di dalamnya akan mengembalikan nilai pada variabel name */
    public String getName() {
        return name;
    }

    /*
     * Method ini adalah method yang berfungsi untuk mendapatkan kereta berdasarkan
     * kode kereta, dengan menggunakan script "for" yang berfungsi
     * untuk mencera kereta dengan melakukan perulangan sesuai dengan jumlah
     * keretanya dengan variabel kereta yang bertipe data Train, kemudian
     * di dalam perulangan terdapat statement jika kereta yang berisi kode adalah
     * variabel darai parameter trainCode maka mengembalikan nilai pada variabel
     * kereta yang bertipe data Train, kemudian jika tidak ditemukan maka akan
     * menampilkan kalimat "Kereta tidak ditemukan" dan akan mengembalikan nilai
     * kosong
     */
    public Train getTrainByCode(String trainCode) {
        for (Train kereta : kereta) {
            if (kereta.kode == trainCode)
                return kereta;
        }
        System.out.println("Kereta tidak ditemukan");
        return null;
    }

    /*
     * Method ini adalah method yang berfungsi untuk mendapatkan kereta berdasarkan
     * tujuan kereta selanjutnya
     * melalukan perulangan dengan script for dengan looping sebanyak jumlah kereta,
     * didalam loop terdapat statement jika variabel kereta yang bertipe data Train
     * yang mememiliki nilai rute selanjutnya yang memiliki nilai nama stasiun
     * adalah variabel dari parameter destination
     * kemudian jika didapatkan datanya akan mengembalikan nilai pada variabel
     * kereta dengan tipe data Train, jika tidak maka akan menampilkan
     * "Kereta tidak ditemukan" dan akan mengembalikan nilai kosong.
     */
    public Train getTrainByNextDest(String destination) {
        for (Train kereta : kereta) {
            if (kereta.rute.temp.next.namaStasiun == destination)
                return kereta;
        }
        System.out.println("Kereta tidak ditemukan");
        return null;
    }

    /* script ini adalah method swap penumpang yang dimana jika penumpang telah memenuhi tujuannya maka penumpang yang ada pada kereta
     * akan berkurang dan dilakukan pengecekan pada stasiun yang dikunjungi apakah tujuan dari penumpang yang ada pada stasiun itu sesuai denagn tujuan kereta maka kereta akan menambah jumlah penumpang yang ada pada stasiun yang dikunjungi
    */
    void swapPassanger() {
        for (Train kereta : kereta) {
            for (Integer i = 0; i < kereta.penumpang.length; i++) {
                if (kereta.penumpang[i].rute.temp.next == null)
                    kereta.penumpang[i] = null;
                else if (kereta.penumpang[i].rute.temp.next != kereta.rute.temp.next) {
                    Node<Penumpang> newNode = new Node<Penumpang>(kereta.penumpang[i]);
                    waiting.tail.next = newNode;
                    newNode.prev = waiting.tail;
                    waiting.tail = waiting.tail.next;
                    kereta.penumpang[i] = null;
                }
            }

            Node<Penumpang> waitingTemp = waiting.head;
            while (waitingTemp != null) {
                if (kereta.rute.temp.next == waitingTemp.obj.rute.temp.next) {
                    for (Penumpang p : kereta.penumpang) {
                        if (p == null) {
                            p = (Penumpang) waitingTemp.obj;
                            waitingTemp.prev.next = waitingTemp.next;
                            waitingTemp.next.prev = waitingTemp.prev;
                            break;
                        }
                    }

                }
                waitingTemp = waitingTemp.next;
            }
        }
    }
}

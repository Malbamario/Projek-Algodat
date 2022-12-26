/*
 * class Penumpang adalah class yang berfungsi untuk menambah data penumpang untuk di kereta maupun stasiun, terdapat constructor penumpang yang berfungsi untuk menambah data penumpang yang berisi name tujuan dan asal dengan tipe data String
 */
public class Penumpang {
   Ticket tiket;
   String name, tujuan, asal;
   Penumpang(String name, String asal, String tujuan, Ticket tiket) {
      this.name = name;
      this.tujuan = tujuan;
      this.asal = asal;
      this.tiket=tiket;
   }
}
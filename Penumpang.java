/*
 * class Penumpang adalah class yang berfungsi untuk menambah data penumpang untuk di kereta maupun stasiun, terdapat constructor penumpang yang berfungsi untuk menambah data penumpang yang berisi name tujuan dan asal dengan tipe data String
 */
public class Penumpang {
   Route rute;
   String name, tujuan, asal;
   Penumpang(String name, String tujuan, String asal) {
      this.name = name;
      this.tujuan = tujuan;
      this.asal = asal;
   }
}
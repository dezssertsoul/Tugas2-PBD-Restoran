import java.util.Scanner;

class Menu {
    String nama;
    double harga;
    String kategori;

    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
}

public class Main {
    static Scanner input = new Scanner(System.in);
    
    // Buat array kapasitas 100 buat nampung menu dinamis
    static Menu[] daftarMenu = new Menu[100]; 
    static int jumlahMenu = 0;

    // Array untuk simpan data transaksi keranjang belanja
    static int[] keranjangMenuId = new int[100];
    static int[] keranjangQty = new int[100];
    static int totalItemDipesan = 0;

    public static void main(String[] args) {
        // Load data awal menu restoran
        daftarMenu[0] = new Menu("Nasi Goreng", 25000, "Makanan");
        daftarMenu[1] = new Menu("Mie Ayam", 15000, "Makanan");
        daftarMenu[2] = new Menu("Ayam Bakar", 30000, "Makanan");
        daftarMenu[3] = new Menu("Sate Kambing", 45000, "Makanan");
        daftarMenu[4] = new Menu("Es Teh Manis", 5000, "Minuman");
        daftarMenu[5] = new Menu("Jus Jeruk", 12000, "Minuman");
        daftarMenu[6] = new Menu("Kopi Hitam", 10000, "Minuman");
        daftarMenu[7] = new Menu("Es Kelapa", 15000, "Minuman");
        jumlahMenu = 8;

        // Jalankan menu utama bertingkat
        menuUtama();
    }

    public static void menuUtama() {
        while (true) {
            System.out.println("\n======================================");
            System.out.println("         RESTORAN MAMAH INAH          ");
            System.out.println("======================================");
            System.out.println("1. Pesan Makanan/Minuman");
            System.out.println("2. Kelola Menu Restoran (Owner)");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu (1-3): ");
            
            String pilih = input.nextLine();
            if (pilih.equals("1")) {
                prosesPemesanan();
            } else if (pilih.equals("2")) {
                kelolaMenuAdmin();
            } else if (pilih.equals("3")) {
                System.out.println("Aplikasi ditutup. Terima kasih!");
                break;
            } else {
                System.out.println("Pilihan tidak ada! Masukkan angka 1-3.");
            }
        }
    }

    public static void cetakDaftarMenu() {
        System.out.println("\n=== KATEGORI MAKANAN ===");
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].kategori.equalsIgnoreCase("Makanan")) {
                System.out.println("[" + i + "] " + daftarMenu[i].nama + " - Rp" + (int)daftarMenu[i].harga);
            }
        }
        System.out.println("\n=== KATEGORI MINUMAN ===");
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].kategori.equalsIgnoreCase("Minuman")) {
                System.out.println("[" + i + "] " + daftarMenu[i].nama + " - Rp" + (int)daftarMenu[i].harga);
            }
        }
    }

    public static void prosesPemesanan() {
        totalItemDipesan = 0; // Reset keranjang setiap ada transaksi baru
        
        while (true) {
            cetakDaftarMenu();
            System.out.println("\n*Ketik 'selesai' jika sudah cukup memesan.");
            System.out.print("Pilih nomor menu: ");
            String kodeMenu = input.nextLine();

            if (kodeMenu.equalsIgnoreCase("selesai")) {
                if (totalItemDipesan == 0) {
                    System.out.println("Keranjang masih kosong!");
                    return;
                }
                break;
            }

            // Validasi input menu biar ga crash kalau user typo huruf
            int idMenu = -1;
            try {
                idMenu = Integer.parseInt(kodeMenu);
                if (idMenu < 0 || idMenu >= jumlahMenu) {
                    System.out.println("Nomor menu salah atau tidak terdaftar!");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Input salah! Masukkan nomor indeks menu atau ketik 'selesai'.");
                continue;
            }

            // Input jumlah porsi
            int qty = 0;
            while (true) {
                System.out.print("Mau pesan berapa porsi " + daftarMenu[idMenu].nama + "? : ");
                String jmlPorsi = input.nextLine();
                try {
                    qty = Integer.parseInt(jmlPorsi);
                    if (qty <= 0) {
                        System.out.println("Jumlah porsi minimal 1!");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Error: Masukkan jumlah porsi dalam bentuk angka!");
                }
            }

            // Masukin data ke keranjang belanja belanjaan
            keranjangMenuId[totalItemDipesan] = idMenu;
            keranjangQty[totalItemDipesan] = qty;
            totalItemDipesan++;
            System.out.println("-> Berhasil menambah " + qty + " " + daftarMenu[idMenu].nama);
        }

        hitungStrukAkhir();
    }
    
    //Hitung total akhir
    public static void hitungStrukAkhir() {
        double subtotal = 0;
        System.out.println("\n======================================");
        System.out.println("        NOTA RESTORAN MAMA INAH        ");
        System.out.println("======================================");
        
        for (int i = 0; i < totalItemDipesan; i++) {
            int id = keranjangMenuId[i];
            int q = keranjangQty[i];
            double totalPerItem = daftarMenu[id].harga * q;
            subtotal += totalPerItem;
            System.out.printf("%-18s x%d = Rp%,.0f\n", daftarMenu[id].nama, q, totalPerItem);
        }

        // Hitung promo potongan dan bonus
        double diskon = 0;
        if (subtotal > 100000) {
            diskon = subtotal * 0.10;
        }

        String bonusMinum = "Tidak Dapat";
        if (subtotal > 50000) {
            bonusMinum = "Beli 1 Gratis 1 Minuman (Aktif)";
        }

        double nominalPajak = subtotal * 0.10;
        double serviceCharge = 20000;
        double grandTotal = (subtotal - diskon) + nominalPajak + serviceCharge;

        // Print Struk
        System.out.println("--------------------------------------");
        System.out.printf("Subtotal         : Rp%,.0f\n", subtotal);
        if (diskon > 0) {
            System.out.printf("Diskon (10%%)     : -Rp%,.0f\n", diskon);
        }
        System.out.printf("Pajak 10%%        : Rp%,.0f\n", nominalPajak);
        System.out.printf("Biaya Pelayanan  : Rp%,.0f\n", serviceCharge);
        System.out.println("--------------------------------------");
        System.out.printf("TOTAL AKHIR      : Rp%,.0f\n", grandTotal);
        System.out.println("Promo Khusus     : " + bonusMinum);
        System.out.println("======================================");
        System.out.println("Tekan Enter untuk kembali...");
        input.nextLine();
    }

    // Manajemen Menu Aplikasi
    public static void kelolaMenuAdmin() {
        while (true) {
            System.out.println("\n--- MENU PENGELOLAAN OWNER ---");
            System.out.println("1. Tambah Menu Baru");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali");
            System.out.print("Pilih opsi (1-4): ");
            String opsi = input.nextLine();

            if (opsi.equals("1")) {
                tambahMenu();
            } else if (opsi.equals("2")) {
                editHarga();
            } else if (opsi.equals("3")) {
                deleteMenu();
            } else if (opsi.equals("4")) {
                break;
            } else {
                System.out.println("Opsi salah! Masukkan angka 1-4.");
            }
        }
    }

    // Opsi tambah menu
    public static void tambahMenu() {
        System.out.print("Nama menu baru: ");
        String nama = input.nextLine();
        
        double harga = 0;
        while (true) {
            System.out.print("Harga menu: ");
            try {
                harga = Double.parseDouble(input.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Masukkan nominal harga yang valid!");
            }
        }

        String kat = "";
        while (true) {
            System.out.print("Kategori (Makanan/Minuman): ");
            kat = input.nextLine();
            if (kat.equalsIgnoreCase("Makanan") || kat.equalsIgnoreCase("Minuman")) {
                break;
            }
            System.out.println("Salah input! Pilih Makanan atau Minuman.");
        }

        daftarMenu[jumlahMenu] = new Menu(nama, harga, kat);
        jumlahMenu++;
        System.out.println("Sukses! Menu baru berhasil dimasukkan.");
    }

    // Opsi edit harga menu
    public static void editHarga() {
        cetakDaftarMenu();
        System.out.print("\nMasukkan nomor menu yang mau diganti harganya: ");
        int idx = -1;
        try {
            idx = Integer.parseInt(input.nextLine());
            if (idx < 0 || idx >= jumlahMenu) {
                System.out.println("Nomor tidak ditemukan!");
                return;
            }
        } catch (Exception e) {
            System.out.println("Input bermasalah!");
            return;
        }

        System.out.print("Harga baru untuk " + daftarMenu[idx].nama + ": ");
        double hrgBaru = Double.parseDouble(input.nextLine());

        System.out.print("Yakin ingin mengubah harga? (Ya/Tidak): ");
        String cek = input.nextLine();

        if (cek.equalsIgnoreCase("Ya")) {
            daftarMenu[idx].harga = hrgBaru;
            System.out.println("Harga berhasil diperbarui.");
        } else {
            System.out.println("Perubahan dibatalkan.");
        }
    }

    // Opsi hapus menu
    public static void deleteMenu() {
        cetakDaftarMenu();
        System.out.print("\nMasukkan nomor menu yang mau dihapus: ");
        int idx = -1;
        try {
            idx = Integer.parseInt(input.nextLine());
            if (idx < 0 || idx >= jumlahMenu) {
                System.out.println("Nomor salah!");
                return;
            }
        } catch (Exception e) {
            System.out.println("Input bermasalah!");
            return;
        }

        System.out.print("Yakin mau hapus " + daftarMenu[idx].nama + " dari aplikasi? (Ya/Tidak): ");
        String cek = input.nextLine();

        if (cek.equalsIgnoreCase("Ya")) {
            // Algoritma geser array manual
            for (int i = idx; i < jumlahMenu - 1; i++) {
                daftarMenu[i] = daftarMenu[i + 1];
            }
            daftarMenu[jumlahMenu - 1] = null;
            jumlahMenu--;
            System.out.println("Menu sukses dihapus.");
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }
}

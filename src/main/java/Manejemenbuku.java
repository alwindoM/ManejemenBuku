import java.util.*;
import java.util.Scanner;

// Kelas untuk merepresentasikan buku
class Buku 
{
    private String judul;
    private String pengarang;
    private int tahunTerbit;
    private String isbn;

    public Buku(String judul, String pengarang, int tahunTerbit, String isbn) 
    {
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.isbn = isbn;
    }

    public String getJudul() 
    {
        return judul;
    }

    public String getPengarang() 
    {
        return pengarang;
    }

    public int getTahunTerbit() 
    {
        return tahunTerbit;
    }

    public String getIsbn() 
    {
        return isbn;
    }

    @Override
    public String toString() 
    {
        return "Buku{" +
                "judul='" + judul + '\'' +
                ", pengarang='" + pengarang + '\'' +
                ", tahunTerbit=" + tahunTerbit +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}

// Kelas untuk sistem manajemen perpustakaan
class SistemPerpustakaan 
{
    private List<Buku> koleksiBuku = new ArrayList<>();
    private List<Buku> bukuDipinjam = new ArrayList<>();

    // Menambah buku ke koleksi
    public void tambahBuku(Buku buku) 
    {
        for (Buku b : koleksiBuku) 
        {
            if (b.getIsbn().equals(buku.getIsbn())) 
            {
                System.out.println("Buku dengan ISBN yang sama sudah ada!");
                return;
            }
        }
        koleksiBuku.add(buku);
        System.out.println("Buku berhasil ditambahkan.");
    }

    // Mencari buku berdasarkan judul atau pengarang
    public void cariBuku(String keyword) 
    {
        for (Buku b : koleksiBuku) 
        {
            if (b.getJudul().toLowerCase().contains(keyword.toLowerCase()) ||
                    b.getPengarang().toLowerCase().contains(keyword.toLowerCase())) 
            {
                System.out.println(b);
            }
        }
    }

    // Menghapus buku
    public void hapusBuku(String isbn) 
    {
        Iterator<Buku> iterator = koleksiBuku.iterator();
        while (iterator.hasNext()) 
        {
            Buku b = iterator.next();
            if (b.getIsbn().equals(isbn)) 
            {
                iterator.remove();
                System.out.println("Buku berhasil dihapus.");
                return;
            }
        }
        System.out.println("Buku tidak ditemukan.");
    }

    // Mengurutkan buku berdasarkan kriteria tertentu
    public void urutkanBuku(String kriteria) 
    {
        switch (kriteria.toLowerCase()) 
        {
            case "tahun":
                koleksiBuku.sort(Comparator.comparingInt(Buku::getTahunTerbit));
                break;
            case "pengarang":
                koleksiBuku.sort(Comparator.comparing(Buku::getPengarang));
                break;
            default:
                System.out.println("Kriteria tidak valid.");
                return;
        }
        System.out.println("Buku berhasil diurutkan.");
        for (Buku b : koleksiBuku) 
        {
            System.out.println(b);
        }
    }

    // Menampilkan daftar buku yang sedang dipinjam
    public void daftarBukuDipinjam() 
    {
        if (bukuDipinjam.isEmpty()) 
        {
            System.out.println("Tidak ada buku yang sedang dipinjam.");
        } else 
        {
            for (Buku b : bukuDipinjam) 
            {
                System.out.println(b);
            }
        }
    }

    // Meminjam buku
    public void pinjamBuku(String isbn) 
    {
        for (Buku b : koleksiBuku) 
        {
            if (b.getIsbn().equals(isbn)) 
            {
                bukuDipinjam.add(b);
                koleksiBuku.remove(b);
                System.out.println("Buku berhasil dipinjam.");
                return;
            }
        }
        System.out.println("Buku tidak ditemukan.");
    }
}

public class Manejemenbuku 
{
    public static void main(String[] args) 
    {
        SistemPerpustakaan perpustakaan = new SistemPerpustakaan();
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("\n=== Sistem Manajemen Perpustakaan ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Cari Buku");
            System.out.println("3. Hapus Buku");
            System.out.println("4. Urutkan Buku");
            System.out.println("5. Daftar Buku Dipinjam");
            System.out.println("6. Pinjam Buku");
            System.out.println("7. Keluar");
            System.out.print("Pilihan: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Konsumsi newline

            switch (pilihan) 
            {
                case 1:
                    System.out.print("Masukkan Judul: ");
                    String judul = scanner.nextLine();
                    System.out.print("Masukkan Pengarang: ");
                    String pengarang = scanner.nextLine();
                    System.out.print("Masukkan Tahun Terbit: ");
                    int tahun = scanner.nextInt();
                    scanner.nextLine(); // Konsumsi newline
                    System.out.print("Masukkan ISBN: ");
                    String isbn = scanner.nextLine();
                    perpustakaan.tambahBuku(new Buku(judul, pengarang, tahun, isbn));
                    break;
                    
                case 2:
                    System.out.print("Masukkan kata kunci pencarian: ");
                    String keyword = scanner.nextLine();
                    perpustakaan.cariBuku(keyword);
                    break;
                    
                case 3:
                    System.out.print("Masukkan ISBN buku yang akan dihapus: ");
                    String isbnHapus = scanner.nextLine();
                    perpustakaan.hapusBuku(isbnHapus);
                    break;
                    
                case 4:
                    System.out.print("Urutkan berdasarkan (tahun/pengarang): ");
                    String kriteria = scanner.nextLine();
                    perpustakaan.urutkanBuku(kriteria);
                    break;
                    
                case 5:
                    perpustakaan.daftarBukuDipinjam();
                    break;
                    
                case 6:
                    System.out.print("Masukkan ISBN buku yang akan dipinjam: ");
                    String isbnPinjam = scanner.nextLine();
                    perpustakaan.pinjamBuku(isbnPinjam);
                    break;
                    
                case 7:
                    System.out.println("Keluar dari sistem.");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}

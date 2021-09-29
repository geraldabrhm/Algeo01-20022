import java.text.NumberFormat.Style;
import java.io.*;
import java.util.*;

public class mainFile {
    public static Scanner scan = new Scanner(System.in);
    
    public static int CheckInteger(int min, int max, String message){
        while (true){
            try{
                System.out.print(message);
                int a = scan.nextInt();
                if((a >= min) && (a <= max)){
                    return a;
                }else{
                    System.out.println("Integer di luar range.");
                }
            }
            catch (InputMismatchException e){
                scan.next();
                System.out.println("Input bukan integer.");
            }
        }
    }
    
    public static int whatMethod(int numb){
        int a = -1;
        switch(numb){
            case 1:
                System.out.println("Ada 4 metode penyelesaian yang tersedia, silahkan pilih dengan mengetikkan nomor yang sesuai");
                System.out.println("Metode Penyelesaian: ");
                System.out.println("1. Metode Eliminasi Gauss");
                System.out.println("2. Metode Eliminasi Gauss Jordan");
                System.out.println("3. Metode Matriks Balikan");
                System.out.println("4. Kaidah Crammer");
                a = CheckInteger(1, 4, "Metode Penyelesaian: ");                
                break;
            case 2:
                System.out.println("Ada 2 metode penyelesaian yang tersedia, silahkan pilih dengan mengetikkan nomor yang sesuai");
                System.out.println("Metode Penyelesaian: ");                
                System.out.println("1. Metode Reduksi Baris");
                System.out.println("2. Ekspansi Kofaktor");
                a = CheckInteger(1, 2, "Metode Penyelesaian: ");                
                break;
            case 3:
                System.out.println("Ada 2 metode penyelesaian yang tersedia, silahkan pilih dengan mengetikkan nomor yang sesuai");
                System.out.println("Metode Penyelasaian: ");
                System.out.println("1. Metode Eliminasi Gauss Jordan");
                System.out.println("2. Matriks Adjoin");
                a = CheckInteger(1, 2, "Metode Penyelesaian: ");                
                break;
        }
        
        return a;
    }

    public static int whatInput(){
        System.out.println("Ada 2 metode input yang tersedia, silahkan pilih dengan mengetikkan nomor yang sesuai");
        System.out.println("Metode Input: ");
        System.out.println("1. Terminal");
        System.out.println("2. File");
        
        int a = CheckInteger(1, 2, "Metode Input: ");
        return a;
    }

    public static void main(String[] args) {
        double[][] problem;
        int getservice = -1;
        int getmethod = -1;
        int getinput = -1;
        int ncol , nrow;
        double xtaksir;
        
        System.out.println();
        System.out.println("--------Welcome to Linear Algebra Solver--------- ");
        System.out.println();
        
        System.out.println("Ditempat ini, kami akan membantumu memecahkan persoalan aljabar linier :)");
        System.out.println();
        
        System.out.println("How? My dear customer, kamu bisa memilih 1 dari servis yang tersedia di bawah ini");
        System.out.println("Setelah itu, kamu harus melengkapi spesifikasinya, yaitu metode penyelesaian, \n metode input, dan input yang sesuai");
        System.out.println();

        System.out.println("Ada 6 servis yang tersedia, silahkan pilih dengan mengetikkan nomor yang sesuai");        
        System.out.println("Servis tersedia: ");
        System.out.println("1. Memecahkan Sistem Persamaan Linear");
        System.out.println("2. Mencari Determinan Matriks");
        System.out.println("3. Menentukan Balikan Matriks");
        System.out.println("4. Memecahkan Interpolasi Polinom");
        System.out.println("5. Memecahkan Regresi Linear Berganda");
        System.out.println("6. Exit Program");
        
        System.out.println();
        getservice = CheckInteger(1, 6, "Servis: ");
        
        switch(getservice){
            case 1:
                System.out.println("Selamat datang di Sistem Persamaan Linear");
                getmethod = whatMethod(1);
                getinput = whatInput();
                break;
            case 2:
                System.out.println("Selamat datang di Determinan Matriks");
                getmethod = whatMethod(2);
                getinput = whatInput();
                break;
            case 3:
                System.out.println("Selamat datang di Sistem Persamaan Linear");
                getmethod = whatMethod(1);
                getinput = whatInput();
                break;
            case 4:
                System.out.println("Selamat datang di Interpolasi Polinom");
                getinput = whatInput();
                getmethod = -1;
                break;
            case 5:
                System.out.println("Selamat datang di Regresi LInear Berganda");
                getinput = whatInput();
                getmethod = -1;
                break;
            case 6:
                System.out.println("Terima kasih sudah menggunakan Linear Algebra Solver");
                System.out.println("Have a nice day :)");
                System.exit(0);
        }
        
        if(getinput == 1){
            switch(getservice){
                case 1:
                    nrow = scan.nextInt();
                    ncol = scan.nextInt();
                    double[][] copy1 = new double[nrow][ncol];

                    for(int i = 0; i < nrow; i ++){
                        for(int j = 0; j < ncol; j ++){
                            copy1[i][j] = scan.nextDouble();
                        }
                    }

                    problem = copy1;
                    break;

                case 2,3:
                    nrow = scan.nextInt();
                    double[][] copy2 = new double[nrow][nrow];

                    for(int i = 0; i < nrow; i ++){
                        for(int j = 0; j < nrow; j++){
                            copy2[i][j] = scan.nextDouble();
                        }
                    }
                    problem = copy2;
                    break;
            }
        }
        else{

        }

    }
}

import java.io.*;
import java.util.*;
import java.lang.Math;

public class mainFile{
    public static Scanner scan = new Scanner(System.in);
    public static double[][] problem; // Matriks Augmented siap diolah
    public static int ncol , nrow;
    public static double[][] solution;
    public static double[] problem2;
    public static double[] b_spl;
    public static int xfile = 0;
    public static double xpred;


    public static void main(String[] args) {
        scan.useLocale(Locale.US);

        boolean canSolve = true;
        String solInstring = "";
        String filename = "";
        int getservice = -1;
        int getmethod = -1;
        int getinput = -1;
        int getoutput = -1;


        System.out.println();
        System.out.println("--------Welcome to Linear Algebra Solver--------- ");
        System.out.println();
        
        System.out.println("Ditempat ini, kami akan membantumu memecahkan persoalan aljabar linier :)");
        System.out.println();
        
        System.out.println("How? My dear customer, kamu bisa memilih 1 dari servis yang tersedia di bawah ini");
        System.out.println("Setelah itu, kamu harus melengkapi spesifikasinya, yaitu metode penyelesaian, \n metode input, dan input yang sesuai");

        while(true){
            getservice = mainInOut.whatService();
            System.out.println();
            
            switch(getservice){
                case 1:
                    System.out.println("Selamat datang di Sistem Persamaan Linear");
                    getmethod = mainInOut.whatMethod(1);
                    getinput = mainInOut.whatInput();
                    break;
                case 2:
                    System.out.println("Selamat datang di Determinan Matriks");
                    getmethod = mainInOut.whatMethod(2);
                    getinput = mainInOut.whatInput();
                    break;
                case 3:
                    System.out.println("Selamat datang di Matriks Balikan");
                    getmethod = mainInOut.whatMethod(3);
                    getinput = mainInOut.whatInput();
                    break;
                case 4:
                    System.out.println("Selamat datang di Interpolasi Polinom");
                    getinput = mainInOut.whatInput();
                    getmethod = -1;
                    break;
                case 5:
                    System.out.println("Selamat datang di Regresi LInear Berganda");
                    getinput = mainInOut.whatInput();
                    getmethod = -1;
                    break;
                case 6:
                    System.out.println("Terima kasih sudah menggunakan Linear Algebra Solver");
                    System.out.println("Have a nice day :)");
                    System.exit(0);
            }
    
            if(getinput == 1){
                switch(getservice){
                    // Asumsi masukan m n --> nrow ncol
                    case 1:
                        nrow = scan.nextInt();
                        ncol = scan.nextInt();
                        problem = new double[nrow][ncol];
                        b_spl=new double[nrow];
                        for(int i = 0; i < nrow; i ++){
                            for(int j = 0; j < ncol; j ++){
                                problem[i][j] = scan.nextDouble();
                                if(j == ncol - 1){
                                    b_spl[i] = problem[i][j];
                                }
                            }
                        }
                        break;
    
                    case 2,3:
                
                        nrow = scan.nextInt();
                        problem = new double[nrow][nrow];
    
                        for(int i = 0; i < nrow; i ++){
                            for(int j = 0; j < nrow; j++){
                                problem[i][j] = scan.nextDouble();
                            }
                        }
                        break;
                    
                    // Asumsi matriks tiap barisnya dimulai dari 1, x, x^2, sampai x^(nrow-1)  diikuti y
                    case 4:
                        nrow = scan.nextInt();
                        problem = new double[nrow][nrow + 1];
    
                        for(int i = 0; i < nrow; i ++){
                            double x1 = scan.nextDouble();
                            double y1 = scan.nextDouble();
                            for(int j = 0; j < (nrow + 1); j++){
                                if(j != nrow){
                                    problem[i][j] = Math.pow(x1, j);
                                    
                                }else{
                                    problem[i][j] = y1;
                                }
                            }
                        }
                        
                        xpred = scan.nextDouble();
                        break;
                    
                    case 5:
                        //Gery
                        nrow = scan.nextInt(); // banyak data 
                        ncol = scan.nextInt(); // banyak peubah                
                        int i, j;
                        System.out.println("Masukan variabel bebas: ");
                        for(i = 0; i < nrow; i++) {
                            for(j = 0; j < ncol; j++) {
                                problem[i][j] = scan.nextDouble();
                            }
                        }
                        System.out.println("Masukan variabel terikat: ");
                        for(j = 0; j < nrow; j++) {
                            problem2[j] = scan.nextDouble();
                        }
                        System.out.print("Masukan x taksir: ");
                        xpred = scan.nextDouble();
                        break;
                }
            }
            else{
                System.out.println("Untuk input dari file, akan diminta nama dari file (tanpa menyertakan ekstensi) dan akan dicari file tersebut di directory test.");
                System.out.println("Apabila file tidak ditemukan, akan dibuat file kosong dan program diakhiri.");
                System.out.print("Nama file: ");
                filename = scan.next();
                
                try{
                    File infile = new File("test/input/" + filename + ".txt");
                    
                    if(infile.createNewFile()){
                        System.out.println("Dibuat File baru, File masih kosong");
                        canSolve = false;
                    }else{
                        System.out.println("File ada, akan dilakukan pembacaan");
                        mainUtil.inputFile(infile, getservice);
                    }
                }
                catch (IOException e){
                    System.out.println("Terjadi error.");
                    e.printStackTrace();
                }
            }
            
            switch(getservice){
                case 1:
                    solInstring = mainSolver.LinearEq(getmethod);
                    break;
                case 2:
                    solInstring = mainSolver.DeterminanMat(getmethod);
                    break;
                case 3:
                    mainSolver.InverseMat(getmethod);
                    break;
                case 4:
                    solInstring=mainSolver.Interpolasi();
                    break;
                case 5:
                //Gery
                    break;
            }

            if(canSolve){
                getoutput = mainInOut.whatOutput();
                
                switch(getservice){
                    case 1, 2, 4, 5:
                        mainInOut.outputFinalString(getoutput, solInstring, filename);
                        break;
                    case 3:
                        mainInOut.outputFinalMatrix(getoutput, filename);
                        break;
                }
            }
        }
    }

}
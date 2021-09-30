import java.text.NumberFormat.Style;
import java.io.*;
import java.util.*;
import java.lang.Math;

public class mainFile{
    public static Scanner scan = new Scanner(System.in);
    public static double[][] problem; // Matriks Augmented siap diolah
    public static int ncol , nrow;
    public static double[][] solution;

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

    public static int whatOutput(){
        System.out.println("Ada 2 metode output yang tersedia, silahkan pilih dengan mengetikkan nomor yang sesuai");
        System.out.println("Metode Output: ");
        System.out.println("1. Terminal");
        System.out.println("2. File");
        
        int a = CheckInteger(1, 2, "Metode Output: ");
        return a;
    }

    public static void inputFile(File input, int service) throws FileNotFoundException{
        Scanner scanfile = new Scanner(input);
        ncol = 0;
        nrow = 0;
        switch(service){
            case 1:
                while(scanfile.hasNextLine()){
                    nrow ++;
                    Scanner colScan = new Scanner(scanfile.nextLine());
                    while(colScan.hasNextDouble()){
                        ncol ++;
                        colScan.nextDouble();
                    }
                }
                scanfile.close();
                problem = new double[nrow][ncol];
                scanfile = new Scanner(input);

                for(int i = 0; i < nrow; i ++){
                    for(int j = 0; j < ncol; i ++){
                        problem[i][j] = scanfile.nextDouble();
                    }
                }
                scanfile.close();
                break;
            
            case 2, 3:
                while(scanfile.hasNextLine()){
                    nrow ++;
                }
                scanfile.close();

                problem = new double[nrow][ncol];
                scanfile = new Scanner(input);

                for(int i = 0; i < nrow; i ++){
                    for(int j = 0; j < nrow; i ++){
                        problem[i][j] = scanfile.nextDouble();
                    }
                }
                scanfile.close();
                break;
            case 4:
                while(scanfile.hasNextLine()){
                    nrow ++;
                }
                scanfile.close();
                problem = new double[nrow][nrow + 1];
                
                scanfile = new Scanner(input);

                for(int i = 0; i < nrow; i ++){
                    double x1 = scanfile.nextDouble();
                    double y1 = scanfile.nextDouble();
                    for(int j = 0; j < (nrow + 1); j++){
                        if(j != nrow){
                            problem[i][j] = Math.pow(x1, j);
                            
                        }else{
                            problem[i][j] = y1;
                        }
                    }
                }
                scanfile.close();
                break;
            case 5:
                break;
        }
    }

    public static void LinearEq(int getmethod){
        switch(getmethod){
            case 1:
                GaussMatrix gauss = new GaussMatrix(problem, nrow, ncol);
                solution = gauss.getResult(problem, nrow, ncol);
                break;
            case 2:
                GaussJordanMatrix gaussjordan = new GaussJordanMatrix(problem, nrow, ncol);
                solution = gaussjordan.getResult(problem, nrow, ncol);
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    public static void DeterminanMat(int getmethod){
        switch(getmethod){
            case 1:
                break;
            case 2:
                break;
        }
    }

    public static void InverseMat(int getmethod){
        switch(getmethod){
            case 1:
                break;
            case 2:
                break;
        }
    }


    public static void outputFinalString(int getoutput, String hasil, String filename){
        switch(getoutput){
            case 1:
                System.out.println(hasil);
                break;
            case 2:
                try {
                    FileWriter myWriter = new FileWriter("test/output/" + filename +"_output.txt");
                    myWriter.write(hasil);
                    myWriter.close();
                    System.out.println("Hasil dituliskan dalam file " + filename + "_output.txt");
                } 
                catch (IOException e) {
                    System.out.println("Terjadi error.");
                    e.printStackTrace();
                }
                break;
        }
    }

    public static void outputFinalMatrix(int getoutput, String filename){
        switch(getoutput){
            case 1:
                for(int i = 0; i < nrow; i ++){
                    for(int j = 0; j < nrow; j ++){
                        if(j == nrow -1){
                            System.out.println(solution[i][j]);
                        }else{
                            System.out.print(solution[i][j]);
                            System.out.print(" ");
                        }
                    }
                }
                break;
            case 2:
                FileOutputStream fileOS = null;
                BufferedOutputStream bufferOS = null;
                DataOutputStream dataOS = null;
                try{
                    fileOS = new FileOutputStream("test/output/" + filename +"_output.txt");
                    bufferOS = new BufferedOutputStream(fileOS);
                    dataOS = new DataOutputStream(bufferOS);
                    for(int i = 0; i < nrow; i ++){
                        for(int j = 0; j < nrow; j ++){
                            if(j == nrow -1){
                                dataOS.writeDouble(solution[i][j]);
                                dataOS.writeChars("\n");
                            }else{  
                                dataOS.writeDouble(solution[i][j]);
                                dataOS.writeChars(" ");
                            }
                        }
                    }
                    System.out.println("Hasil dituliskan dalam file " + filename + "_output.txt");
                }catch(IOException e){
                    System.out.println("Terjadi error.");
                    e.printStackTrace();
                }
        }
    }

    public static void main(String[] args) {
        scan.useLocale(Locale.US);

        String solInstring = "";
        String filename = "";
        double[][] b_spl;
        int getservice = -1;
        int getmethod = -1;
        int getinput = -1;
        int getoutput = -1;
        int ncol , nrow;
        double xtaksir;


        System.out.println();
        System.out.println("--------Welcome to Linear Algebra Solver--------- ");
        System.out.println();
        
        System.out.println("Ditempat ini, kami akan membantumu memecahkan persoalan aljabar linier :)");
        System.out.println();
        
        System.out.println("How? My dear customer, kamu bisa memilih 1 dari servis yang tersedia di bawah ini");
        System.out.println("Setelah itu, kamu harus melengkapi spesifikasinya, yaitu metode penyelesaian, \n metode input, dan input yang sesuai");

        while(true){
            System.out.println();
            System.out.println("Ada 6 servis yang tersedia, silahkan pilih dengan mengetikkan nomor yang sesuai");        
            System.out.println("Servis tersedia: ");
            System.out.println("1. Memecahkan Sistem Persamaan Linear");
            System.out.println("2. Mencari Determinan Matriks");
            System.out.println("3. Menentukan Balikan Matriks");
            System.out.println("4. Memecahkan Interpolasi Polinom");
            System.out.println("5. Memecahkan Regresi Linear Berganda");
            System.out.println("6. Exit Program");
            
            getservice = CheckInteger(1, 6, "Servis: ");
            System.out.println();
            
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
                    System.out.println("Selamat datang di Matriks Balikan");
                    getmethod = whatMethod(3);
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
                    // Asumsi masukan m n --> nrow ncol
                    case 1:
                        nrow = scan.nextInt();
                        ncol = scan.nextInt();
                        problem = new double[nrow][ncol];
                        b_spl = new double[nrow][1];
                        for(int i = 0; i < nrow; i ++){
                            for(int j = 0; j < ncol; j ++){
                                problem[i][j] = scan.nextDouble();
                                if(j == ncol - 1){
                                    b_spl[i][0] = problem[i][j];
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
                        
                        xtaksir = scan.nextDouble();
                        break;
                    
                    case 5:
                        nrow = scan.nextInt(); 
                        ncol = scan.nextInt();                 
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
                    }else{
                        System.out.println("File ada, akan dilakukan pembacaan");
                        inputFile(infile, getservice);
                    }
                }
                catch (IOException e){
                    System.out.println("Terjadi error.");
                    e.printStackTrace();
                }
            }
            
            switch(getservice){
                case 1:
                    LinearEq(getmethod);
                    break;
                case 2:
                    DeterminanMat(getmethod);
                    break;
                case 3:
                    InverseMat(getmethod);
                    break;
                case 4:
                    LinearEq(2);
                    break;
                case 5:
                    break;
            }

            getoutput = whatOutput();
            
            switch(getservice){
                case 1, 2, 4, 5:
                    outputFinalString(getoutput, solInstring, filename);
                    break;
                case 3:
                    outputFinalMatrix(getoutput, filename);
            }
        }
    }

}

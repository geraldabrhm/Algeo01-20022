import java.text.NumberFormat.Style;
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
                ncol /= nrow;
                problem = new double[nrow][ncol];
                b_spl = new double[nrow];
                scanfile = new Scanner(input);

                for(int i = 0; i < nrow; i ++){
                    for(int j = 0; j < ncol; j ++){
                        problem[i][j] = scanfile.nextDouble();
                        if(j == ncol -1){
                            b_spl[i] = problem[i][j];
                        }
                    }
                }
                scanfile.close();
                break;
            
            case 2, 3:
                while(scanfile.hasNextLine()){
                    nrow ++;
                    scanfile.nextLine();
                }
                scanfile.close();

                problem = new double[nrow][nrow];
                scanfile = new Scanner(input);

                for(int i = 0; i < nrow; i ++){
                    for(int j = 0; j < nrow; j ++){
                        problem[i][j] = scanfile.nextDouble();
                    }
                }
                scanfile.close();
                break;
            case 4:
                double rangemin = -1; 
                double rangemax = -1;
                while(scanfile.hasNextLine()){
                    nrow ++;
                    scanfile.nextLine();
                }
                scanfile.close();
                problem = new double[nrow][nrow + 1];
                
                scanfile = new Scanner(input);

                for(int i = 0; i < nrow; i ++){
                    double x1 = scanfile.nextDouble();
                    double y1 = scanfile.nextDouble();

                    if(i == 0){
                        rangemin = x1;
                        rangemax = x1;
                    }else{
                        if(x1 < rangemin){
                            rangemin = x1;
                        }
                        
                        if(x1 > rangemax){
                            rangemax = x1;
                        }
                    }

                    for(int j = 0; j < (nrow + 1); j++){
                        if(j != nrow){
                            problem[i][j] = Math.pow(x1, j);
                            
                        }else{
                            problem[i][j] = y1;
                        }
                    }
                }
                scanfile.close();
                Random random = new Random();
                double xpred = rangemin + (rangemax - rangemin) * random.nextDouble();
                break;
            case 5:
            //Gery
                break;
        }
    }

    public static String LinearEq(int getmethod){
        String hasil="";
        switch(getmethod){
            case 1:
                GaussMatrix gauss = new GaussMatrix(problem, nrow, ncol);
                solution = gauss.getResult(gauss.getGaussMatrix(), nrow, ncol);
                hasil="";
                if(gauss.numParams()<0){
                    hasil=hasil+"Persamaan tidak memiliki solusi\n";
                }
                else{
                    hasil="Hasil =\n";
                    int numparam=gauss.numParams();
                    for(int i=0;i<ncol-1;i++){
                        boolean isAllZero=true;
                        int nums=i+1;
                        hasil+="x"+nums+" = ";
                        for(int j=0;j<numparam;j++){
                            if(solution[i][j]==0)continue;
                            int k=j+1;
                            if(isAllZero && solution[i][j]!=0){
                                if(solution[i][j]==1){
                                    hasil+="t"+k;
                                }else if(solution[i][j]==-1){
                                    hasil+="-t"+k;
                                }else{
                                    hasil+=solution[i][j]+"t"+k;
                                }
                                isAllZero=false;
                            }else{
                                if(solution[i][j]>0){
                                    if(solution[i][j]==1){
                                        hasil+=" + "+"t"+k;
                                    }else{
                                        hasil+=" + "+solution[i][j]+"t"+k;
                                    }
                                }else{
                                    double kof=solution[i][j];
                                    kof*=(-1d);
                                    if(solution[i][j]==-1){
                                        hasil+=" - "+"t"+k;
                                    }else{
                                        hasil+=" - "+kof+"t"+k;
                                    }
                                }
                            }
                        }
                        if(isAllZero){
                            hasil+=solution[i][numparam];
                        }else{
                            if(solution[i][numparam]!=0){
                                if(solution[i][numparam]>0){
                                    hasil+=" + "+solution[i][numparam];
                                }else{
                                    double kof=solution[i][numparam];
                                    kof*=(-1d);
                                    hasil+=" - "+kof;
                                }
                            }
                        }
                        hasil+="\n";
                    }
                }
                break;
            case 2:
                GaussJordanMatrix gaussjordan = new GaussJordanMatrix(problem, nrow, ncol);
                solution = gaussjordan.getResult(gaussjordan.getJordanMatrix(), nrow, ncol);
                hasil="";
                if(gaussjordan.numParams()<0){
                    hasil=hasil+"Persamaan tidak memiliki solusi\n";
                }
                else{
                    hasil="Hasil =\n";
                    int numparam=gaussjordan.numParams();
                    for(int i=0;i<ncol-1;i++){
                        boolean isAllZero=true;
                        int nums=i+1;
                        hasil+="x"+nums+" = ";
                        for(int j=0;j<numparam;j++){
                            if(solution[i][j]==0)continue;
                            int k=j+1;
                            if(isAllZero && solution[i][j]!=0){
                                if(solution[i][j]==1){
                                    hasil+="t"+k;
                                }else if(solution[i][j]==-1){
                                    hasil+="-t"+k;
                                }else{
                                    hasil+=solution[i][j]+"t"+k;
                                }
                                isAllZero=false;
                            }else{
                                if(solution[i][j]>0){
                                    if(solution[i][j]==1){
                                        hasil+=" + "+"t"+k;
                                    }else{
                                        hasil+=" + "+solution[i][j]+"t"+k;
                                    }
                                }else{
                                    double kof=solution[i][j];
                                    kof*=(-1d);
                                    if(solution[i][j]==-1){
                                        hasil+=" - "+"t"+k;
                                    }else{
                                        hasil+=" - "+kof+"t"+k;
                                    }
                                }
                            }
                        }
                        if(isAllZero){
                            hasil+=solution[i][numparam];
                        }else{
                            if(solution[i][numparam]!=0){
                                if(solution[i][numparam]>0){
                                    hasil+=" + "+solution[i][numparam];
                                }else{
                                    double kof=solution[i][numparam];
                                    kof*=(-1d);
                                    hasil+=" - "+kof;
                                }
                            }
                        }
                        hasil+="\n";
                    }
                }
                break;
            case 4:
                //Gery
                double[] resultt;
                hasil = "";
                int m = 1;
                double[][] matriksNew = new double[nrow][nrow];
                if(nrow == (ncol - 1)) {
                    for(int i = 0; i < nrow; i++) {
                        for(int j = 0; j < nrow; j++) {
                            matriksNew[i][j] = problem[i][j];
                        }
                    }
                    if(inversMatriks.DetbyKofaktor(matriksNew) != 0) {
                        resultt = SPLCrammer.Crammer(matriksNew, b_spl);
                        for(int i = 0; i < nrow; i++) {
                            m = i + 1;
                            hasil += "x" + m + " = " + resultt[i] + "\n";
                        }
                    }
                    else{
                        hasil += "SPL tidak memiliki solusi unik";
                    }
                }
                break;
            case 3:
                double[] resulttt;
                hasil = "";
                int mm = 1;
                double[][] matriksNeww = new double[nrow][nrow];
                if(nrow == (ncol - 1)) {
                    for(int i = 0; i < nrow; i++) {
                        for(int j = 0; j < nrow; j++) {
                            matriksNeww[i][j] = problem[i][j];
                        }
                    }
                    if(inversMatriks.DetbyKofaktor(matriksNeww) != 0) {
                        double[][] tempo = inversMatriks.inversebyKofaktor(matriksNeww);
                        resulttt = regresiLinear.multiplication(tempo, b_spl);
                        for(int i = 0; i < nrow; i++) {
                            mm = i + 1;
                            hasil += "x" + mm + " = " + resulttt[i] + "\n";
                        }
                    }
                    else{
                        hasil += "SPL tidak memiliki solusi unik";
                    }
                }
                break;
        }
        return hasil;
    }

    public static String DeterminanMat(int getmethod){
        String hasil="Hasil Determinan = ";
        //Gery
        switch(getmethod){
            case 1:
                DeterminanOBE detByOBE=new DeterminanOBE();
                double solDetOBE=detByOBE.calcDeterminanOBE(problem, nrow, nrow);
                hasil+=solDetOBE;
                break;
            case 2:
                double solutionDet = inversMatriks.DetbyKofaktor(problem);
                hasil+=solutionDet;
                break;
        }
        return hasil;
    }

    public static void InverseMat(int getmethod){
        //Gery
        switch(getmethod){
            case 1:
                solution = inversMatriks.inversebyGaussJordan(problem);
                break;
            case 2:
                solution = inversMatriks.inversebyKofaktor(problem);
                break;
        }
    }

    public static String Interpolasi(){
        String hasil="Hasil Interpolasi =\ny = ";
        GaussJordanMatrix gaussjordan = new GaussJordanMatrix(problem, nrow, nrow+1);
        solution=gaussjordan.getResult(gaussjordan.getJordanMatrix(), nrow, nrow+1);
        int k;
        if(solution[nrow-1][0]==1){
            k=nrow-1;
            hasil+="x^"+k;
        }else if(solution[nrow-1][0]==-1){
            k=nrow-1;
            hasil+="-x^"+k;
        }else{
            k=nrow-1;
            hasil+=solution[ncol-1][0]+"x^"+k;
        }
        for(int i=nrow-2;i>0;i--){
            if(solution[i][0]==0)continue;
            if(solution[i][0]>0){
                if(solution[i][0]==1){
                    hasil+=" + x^"+i;
                }else{
                    hasil+=" + "+solution[i][0]+"x^"+k;
                }
            }else{
                if(solution[i][0]==-1){
                    hasil+=" - x^"+i;
                }else{
                    double koef=solution[i][0]*(-1d);
                    hasil+=" - "+koef+"x^"+k;
                }
            }
        }
        if(solution[0][0]>0){
            hasil+=" + "+solution[0][0];
        }else if(solution[0][0]!=0){
            double koef=solution[0][0]*(-1d);
            hasil+=" - "+koef;
        }
        hasil+="\n";

        double yres=0;

        for(int i=nrow-1;i>=0;i--){
            yres+=solution[i][0]*Math.pow(xpred,i);
        }
        
        hasil+="Hasil prediksi dari x = "+yres+"\n";


        return hasil;
    }

    public static String RegresiSolver(double xpred){
        String hasil = "Hasil regresi = \ny = ";
        double[] hasilReg;
        double resultRRR = 0;
        hasilReg = regresiLinear.multiReg(problem, problem2);
        int i;
        int k=1;
        if(hasilReg[0]>0){
            if(hasilReg[0]==1){
                hasil+="x"+k; 
            }else{
                hasil+=hasilReg[0]+"x"+k;
            }
        }else{
            if(hasilReg[0]<0){
                if(hasilReg[0]==-1){
                    hasil+="-x"+k;
                }else{
                    double koef=hasilReg[0]*(-1d);
                    hasil+=koef+"x"+k;
                }
            }
        }
        for(i = 1; i < hasilReg.length; i++) {
            if(hasilReg[i]==0)continue;
            k=i+1;
            if(hasilReg[i]>0){
                if(hasilReg[i]==1){
                    hasil+=" + x"+k; 
                }else{
                    hasil+=" + "+hasilReg[i]+"x"+k;
                }
            }else{
                if(hasilReg[i]<0){
                    if(hasilReg[i]==-1){
                        hasil+=" - x"+k;
                    }else{
                        double koef=hasilReg[i]*(-1d);
                        hasil+=" - "+koef+"x"+k;
                    }
                }
            }
        }
        for(i = 0; i < hasilReg.length; i++) {
            if(i == 0) {
                resultRRR += hasilReg[0];
            }
            else {
                resultRRR += hasilReg[i] * xpred;
            }
        }
        hasil+="\n";
        hasil+="Hasil prediksi = "+resultRRR;
        return hasil;
    }

    public static void outputFinalString(int getoutput, String hasil, String filename){
        switch(getoutput){
            case 1:
                System.out.println(hasil);
                break;
            case 2:
                try {
                    String outName;
                
                    if(filename.length() == 0){
                        String xcomp = String.valueOf(xfile);
                        outName = "term" +  xcomp + "_output.txt";
                        xfile ++;
                    }else{
                        outName = filename + "_output.txt";
                    }
                    FileWriter myWriter = new FileWriter("test/output/" + outName);
                    myWriter.write(hasil); 
                    myWriter.close();

                    System.out.println("Hasil dituliskan dalam file " + outName);
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
                        if((solution[i][j] < 1e-14 ) && (solution[i][j] > -1e-14)){
                            solution[i][j] = 0;    
                        }

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
                try{
                    String outName;
                
                    if(filename.length() == 0){
                        String xcomp = String.valueOf(xfile);
                        outName = "term" +  xcomp + "_output.txt";
                        xfile ++;
                    }else{
                        outName = filename + "_output.txt";
                    }

                    FileWriter myWriter = new FileWriter("test/output/" + outName);
                    for(int i = 0; i < nrow; i ++){
                        for(int j = 0; j < nrow; j ++){
                            String doublevalue = Double.toString(solution[i][j]);
                            if(j == nrow -1){
                                myWriter.write(doublevalue + "\n");
                            }else{  
                                myWriter.write(doublevalue + " ");
                            }
                        }
                    }

                    myWriter.close();
                    System.out.println("Hasil dituliskan dalam file " + outName);
                }catch(IOException e){
                    System.out.println("Terjadi error.");
                    e.printStackTrace();
                }
        }
    }

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
                    solInstring = LinearEq(getmethod);
                    break;
                case 2:
                    solInstring = DeterminanMat(getmethod);
                    break;
                case 3:
                    InverseMat(getmethod);
                    break;
                case 4:
                    solInstring=Interpolasi();
                    break;
                case 5:
                //Gery
                    break;
            }

            if(canSolve){
                getoutput = whatOutput();
                
                switch(getservice){
                    case 1, 2, 4, 5:
                        outputFinalString(getoutput, solInstring, filename);
                        break;
                    case 3:
                        outputFinalMatrix(getoutput, filename);
                        break;
                }
            }
        }
    }

}
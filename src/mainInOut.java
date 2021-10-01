import java.io.*;

public class mainInOut {
    
    //Method untuk mengontol input dan pilihan
    public static int whatService(){
        int a;
        System.out.println();
        System.out.println("Ada 6 servis yang tersedia, silahkan pilih dengan mengetikkan nomor yang sesuai");        
        System.out.println("Servis tersedia: ");
        System.out.println("1. Memecahkan Sistem Persamaan Linear");
        System.out.println("2. Mencari Determinan Matriks");
        System.out.println("3. Menentukan Balikan Matriks");
        System.out.println("4. Memecahkan Interpolasi Polinom");
        System.out.println("5. Memecahkan Regresi Linear Berganda");
        System.out.println("6. Exit Program");
        
        a = mainUtil.CheckInteger(1, 6, "Servis: ");

        return a;
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
                a = mainUtil.CheckInteger(1, 4, "Metode Penyelesaian: ");                
                break;
            case 2:
                System.out.println("Ada 2 metode penyelesaian yang tersedia, silahkan pilih dengan mengetikkan nomor yang sesuai");
                System.out.println("Metode Penyelesaian: ");                
                System.out.println("1. Metode Reduksi Baris");
                System.out.println("2. Ekspansi Kofaktor");
                a = mainUtil.CheckInteger(1, 2, "Metode Penyelesaian: ");                
                break;
            case 3:
                System.out.println("Ada 2 metode penyelesaian yang tersedia, silahkan pilih dengan mengetikkan nomor yang sesuai");
                System.out.println("Metode Penyelasaian: ");
                System.out.println("1. Metode Eliminasi Gauss Jordan");
                System.out.println("2. Matriks Adjoin");
                a = mainUtil.CheckInteger(1, 2, "Metode Penyelesaian: ");                
                break;
        }
        
        return a;
    }

    
    public static int whatInput(){
        System.out.println("Ada 2 metode input yang tersedia, silahkan pilih dengan mengetikkan nomor yang sesuai");
        System.out.println("Metode Input: ");
        System.out.println("1. Terminal");
        System.out.println("2. File");
        
        int a = mainUtil.CheckInteger(1, 2, "Metode Input: ");
        return a;
    }

    public static int whatOutput(){
        System.out.println("Ada 2 metode output yang tersedia, silahkan pilih dengan mengetikkan nomor yang sesuai");
        System.out.println("Metode Output: ");
        System.out.println("1. Terminal");
        System.out.println("2. File");
        
        int a = mainUtil.CheckInteger(1, 2, "Metode Output: ");
        return a;
    }


    //Method untuk output
    public static void outputFinalString(int getoutput, String hasil, String filename){
        switch(getoutput){
            case 1:
                System.out.println(hasil);
                break;
            case 2:
                try {
                    String outName;
                
                    if(filename.length() == 0){
                        String xcomp = String.valueOf(mainFile.xfile);
                        outName = "term" +  xcomp + "_output.txt";
                        mainFile.xfile ++;
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
                for(int i = 0; i < mainFile.nrow; i ++){
                    for(int j = 0; j < mainFile.nrow; j ++){
                        if((mainFile.solution[i][j] < 1e-14 ) && (mainFile.solution[i][j] > -1e-14)){
                            mainFile.solution[i][j] = 0;    
                        }

                        if(j == mainFile.nrow -1){
                            System.out.println(mainFile.solution[i][j]);
                        }else{
                            System.out.print(mainFile.solution[i][j]);
                            System.out.print(" ");
                        }
                    }
                }
                break;
            case 2:
                try{
                    String outName;
                
                    if(filename.length() == 0){
                        String xcomp = String.valueOf(mainFile.xfile);
                        outName = "term" +  xcomp + "_output.txt";
                        mainFile.xfile ++;
                    }else{
                        outName = filename + "_output.txt";
                    }

                    FileWriter myWriter = new FileWriter("test/output/" + outName);
                    for(int i = 0; i < mainFile.nrow; i ++){
                        for(int j = 0; j < mainFile.nrow; j ++){
                            String doublevalue = Double.toString(mainFile.solution[i][j]);
                            if(j == mainFile.nrow -1){
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

}

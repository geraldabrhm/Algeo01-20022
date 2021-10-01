import java.util.*;
import java.io.*;

public class mainUtil {

    public static int CheckInteger(int min, int max, String message){
        while (true){
            try{
                System.out.print(message);
                int a = mainFile.scan.nextInt();
                if((a >= min) && (a <= max)){
                    return a;
                }else{
                    System.out.println("Integer di luar range.");
                }
            }
            catch (InputMismatchException e){
                mainFile.scan.next();
                System.out.println("Input bukan integer.");
            }
        }
    }   

    public static void generateXPred(double max, double min){
        Random random = new Random();
        mainFile.xpred = min + (max - min) * random.nextDouble();
    }
    
    public static void inputFile(File input, int service) throws FileNotFoundException{
        Scanner scanfile = new Scanner(input);
        mainFile.ncol = 0;
        mainFile.nrow = 0;
        switch(service){
            case 1:
                while(scanfile.hasNextLine()){
                    mainFile.nrow ++;
                    Scanner colScan = new Scanner(scanfile.nextLine());
                    while(colScan.hasNextDouble()){
                        mainFile.ncol ++;
                        colScan.nextDouble();
                    }
                }
                scanfile.close();
                mainFile.ncol /= mainFile.nrow;
                mainFile.problem = new double[mainFile.nrow][mainFile.ncol];
                mainFile.b_spl = new double[mainFile.nrow];
                scanfile = new Scanner(input);

                for(int i = 0; i < mainFile.nrow; i ++){
                    for(int j = 0; j < mainFile.ncol; j ++){
                        mainFile.problem[i][j] = scanfile.nextDouble();
                        if(j == mainFile.ncol -1){
                            mainFile.b_spl[i] = mainFile.problem[i][j];
                        }
                    }
                }
                scanfile.close();
                break;
            
            case 2, 3:
                while(scanfile.hasNextLine()){
                    mainFile.nrow ++;
                    scanfile.nextLine();
                }
                scanfile.close();

                mainFile.problem = new double[mainFile.nrow][mainFile.nrow];
                scanfile = new Scanner(input);

                for(int i = 0; i < mainFile.nrow; i ++){
                    for(int j = 0; j < mainFile.nrow; j ++){
                        mainFile.problem[i][j] = scanfile.nextDouble();
                    }
                }
                scanfile.close();
                break;
            case 4:
                double rangemin = -1; 
                double rangemax = -1;
                while(scanfile.hasNextLine()){
                    mainFile.nrow ++;
                    scanfile.nextLine();
                }
                scanfile.close();
                mainFile.problem = new double[mainFile.nrow][mainFile.nrow + 1];
                
                scanfile = new Scanner(input);

                for(int i = 0; i < mainFile.nrow; i ++){
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

                    for(int j = 0; j < (mainFile.nrow + 1); j++){
                        if(j != mainFile.nrow){
                            mainFile.problem[i][j] = Math.pow(x1, j);
                            
                        }else{
                            mainFile.problem[i][j] = y1;
                        }
                    }
                }
                scanfile.close();
                mainUtil.generateXPred(rangemax, rangemin);
                break;
            case 5:
                mainFile.nrow = scanfile.nextInt();
                mainFile.ncol = scanfile.nextInt();
                
                mainFile.problem = new double[mainFile.nrow][mainFile.ncol];
                for(int i = 0; i < mainFile.nrow; i++) {
                    for(int j = 0; j < mainFile.ncol; j++) {
                        mainFile.problem[i][j] = scanfile.nextDouble();
                    }
                }
                mainFile.problem2 = new double[mainFile.nrow];
                
                for(int j = 0; j < mainFile.nrow; j++) {
                    mainFile.problem2[j] = scanfile.nextDouble();
                }
                mainFile.varxpred = new double[mainFile.ncol];
                
                for(int i=0;i < mainFile.ncol; i++){
                    mainFile.varxpred[i]= scanfile.nextDouble();
                }
                break;
        }
    }
}

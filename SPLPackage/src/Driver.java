import java.util.Scanner;


public class Driver{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        double[][] inputmatrix = new double[100][100];
        int nrow=in.nextInt();
        int ncol=in.nextInt();
        for(int i=0;i<nrow;i++){
            for(int j=0;j<ncol;j++){
                inputmatrix[i][j]=in.nextDouble();
            }
        }
        System.out.println("\nGauss Matrix :");
        GaussMatrix cek1=new GaussMatrix(inputmatrix, nrow, ncol);
        for(int i=0;i<nrow;i++){
            for(int j=0;j<ncol;j++){
                System.out.print(cek1.getGaussMatrix()[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("div total = "+cek1.divVal);

        System.out.println("\nGauss Jordan Matrix :");
        GaussJordanMatrix cek2=new GaussJordanMatrix(inputmatrix, nrow, ncol);
        for(int i=0;i<nrow;i++){
            for(int j=0;j<ncol;j++){
                System.out.print(cek2.getJordanMatrix()[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("div total = "+cek2.getDivVal());
    }
}
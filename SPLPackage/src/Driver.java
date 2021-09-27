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

        double[][] res=new double[101][101];
        res=cek1.getResult(cek1.getGaussMatrix(), nrow, ncol);
        int nvariable=cek1.numParams();
        System.out.println("\nEliminasi Gauss");
        for(int i=0;i<ncol-1;i++){
            for(int j=0;j<=nvariable;j++){
                System.out.print(res[i][j]+" ");
            }
            System.out.println();
        }

        double[][] result=new double[101][101];
        result=cek2.getResult(cek2.getJordanMatrix(), nrow, ncol);
        int numvariable=cek2.numParams();
        System.out.println("\nEliminasi Gauss Jordan");
        for(int i=0;i<ncol-1;i++){
            for(int j=0;j<=numvariable;j++){
                System.out.print(result[i][j]+" ");
            }
            System.out.println();
        }
    }
}
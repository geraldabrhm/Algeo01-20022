import java.util.*;

// javac Driver.java LinearPrimitive.java GaussMatrix.java GaussJordanMatrix.java


public class GaussJordanMatrix extends LinearPrimitive{
    ArrayList<ArrayList<Double>> jordan;
    ArrayList<Integer> notZeroElmt;
    double divVal;
    GaussJordanMatrix(ArrayList<ArrayList<Double>> inputMatrix, int nrow, int ncol){
        super(inputMatrix, ncol, nrow);

        // get Gauss Matrix of inputmatrix
        GaussMatrix Gauss = new GaussMatrix(super.doubleMatrix, super.nrow, super.ncol);

        // copy gauss matrix to doubleMatrix and copy notZeroElmt
        this.jordan = new ArrayList<>();
        this.notZeroElmt = new ArrayList<>();
        for(int i=0;i<super.nrow;i++){
            ArrayList<Double> temp = new ArrayList<>();
            this.notZeroElmt.add(i,Gauss.notZeroElmt.get(i));
            for(int j=0;j<super.ncol;j++){
                temp.add(j,Gauss.getGaussMatrix().get(i).get(j));
            }
            super.doubleMatrix.set(i,temp);
        }

        // begin OBE
        for(int i=super.nrow-2;i>=0;i--){
            for(int j=i+1;j<super.nrow;j++){
                if(this.notZeroElmt.get(j)==super.ncol)continue;
                double koef=super.doubleMatrix.get(i).get(this.notZeroElmt.get(j))/super.doubleMatrix.get(j).get(this.notZeroElmt.get(j));
                super.rowOperation(j, i, koef, false);
            }
        }

        // copy result matrix to class variable
        for(int i=0;i<super.nrow;i++){
            this.jordan.add(i,super.doubleMatrix.get(i));
        }
        this.divVal=Gauss.divVal;
        this.formattingZero();
    }
    ArrayList<ArrayList<Double>> getJordanMatrix(){
        return this.jordan;
    }

    double getDivVal(){
        return this.divVal;
    }

    void formattingZero(){
        for(int i=0;i<super.nrow;i++){
            for(int j=0;j<super.ncol;j++){
                if(this.jordan.get(i).get(j)==0d){
                    this.jordan.get(i).set(j,0d);
                }
            }
        }
    }
}

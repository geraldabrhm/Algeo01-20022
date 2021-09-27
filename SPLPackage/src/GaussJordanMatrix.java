
// javac Driver.java LinearPrimitive.java GaussMatrix.java GaussJordanMatrix.java


public class GaussJordanMatrix extends LinearPrimitive{
    double[][] jordan = new double[100][100];
    double divVal;
    int swapCount;
    int nparamater=-1;;
    GaussJordanMatrix(double[][] inputMatrix, int nrow, int ncol){
        super(inputMatrix, ncol, nrow);
        this.transformToGaussJordan();
    }
    void transformToGaussJordan(){
        // get Gauss Matrix
        GaussMatrix Gauss = new GaussMatrix(super.matrix, super.nrow, super.ncol);

        // copy gauss matrix to doubleMatrix and copy notZeroElmt
        for(int i=0;i<super.nrow;i++){
            for(int j=0;j<super.ncol;j++){
                super.matrix[i][j] = Gauss.getGaussMatrix()[i][j];
            }
        }

        // begin OBE
        for(int i=super.nrow-2;i>=0;i--){
            for(int j=i+1;j<super.nrow;j++){
                if(Gauss.notZeroElmt[j]==super.ncol)continue;
                double koef=super.matrix[i][Gauss.notZeroElmt[j]]/super.matrix[j][Gauss.notZeroElmt[j]];
                super.rowOperation(j, i, koef, false);
            }
        }

        this.divVal=Gauss.divVal;
        this.swapCount=Gauss.swapCount;
        this.formattingZero();
    }
    double[][] getJordanMatrix(){
        return super.matrix;
    }

    double getDivVal(){
        return this.divVal;
    }

    int getSwapCount(){
        return this.swapCount;
    }

    void formattingZero(){
        for(int i=0;i<super.nrow;i++){
            for(int j=0;j<super.ncol;j++){
                if(super.matrix[i][j]<1e-14  && super.matrix[i][j]>-1e-14){
                    super.matrix[i][j] = 0d;
                }
            }
        }
    }

    double[][] getResult(double[][] inputMat, int nrow, int ncol){
        double[][] dummy=new double[100][100];
        GaussMatrix Gauss=new GaussMatrix(dummy, nrow, ncol);
        double[][] result=Gauss.getResult(inputMat, nrow, ncol);
        this.nparamater=Gauss.numParams();
        return result;
    }

    int numParams(){
        return this.nparamater;
    }
}

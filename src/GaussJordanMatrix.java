public class GaussJordanMatrix{
    int nrow,ncol;
    double[][] jordan = new double[100][100];
    double[][] matrix = new double[100][100];
    double divVal;
    int swapCount;
    int nparamater=-1;;
    GaussJordanMatrix(double[][] inputMatrix, int nrow, int ncol){
        matrix=inputMatrix;
        this.nrow=nrow;
        this.ncol=ncol;
        this.transformToGaussJordan();
    }
    void transformToGaussJordan(){
        // get Gauss Matrix
        GaussMatrix Gauss = new GaussMatrix(this.matrix, this.nrow, this.ncol);

        // copy gauss matrix to doubleMatrix and copy notZeroElmt
        for(int i=0;i<this.nrow;i++){
            for(int j=0;j<this.ncol;j++){
                this.matrix[i][j] = Gauss.getGaussMatrix()[i][j];
            }
        }

        // begin OBE
        for(int i=this.nrow-2;i>=0;i--){
            for(int j=i+1;j<this.nrow;j++){
                if(Gauss.notZeroElmt[j]==this.ncol)continue;
                double koef=this.matrix[i][Gauss.notZeroElmt[j]]/this.matrix[j][Gauss.notZeroElmt[j]];
                this.rowOperation(j, i, koef, false);
            }
        }

        this.divVal=Gauss.divVal;
        this.swapCount=Gauss.swapCount;
        this.formattingZero();
    }
    double[][] getJordanMatrix(){
        return this.matrix;
    }

    double getDivVal(){
        return this.divVal;
    }

    int getSwapCount(){
        return this.swapCount;
    }

    void formattingZero(){
        for(int i=0;i<this.nrow;i++){
            for(int j=0;j<this.ncol;j++){
                if(this.matrix[i][j]<1e-14  && this.matrix[i][j]>-1e-14){
                    this.matrix[i][j] = 0d;
                }
            }
        }
    }

    //Give argument true to parameter plus, if we need to do addition
    void rowOperation(int rowfirst, int rowsecond, double multiplier, Boolean plus){
        for(int i = 0; i < this.ncol; i ++){
            double newVal;
            if(plus){
                newVal=this.matrix[rowsecond][i] + this.matrix[rowfirst][i]*multiplier;
            }else{
                newVal=this.matrix[rowsecond][i] - this.matrix[rowfirst][i]*multiplier;
            }
            this.matrix[rowsecond][i]=newVal;
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

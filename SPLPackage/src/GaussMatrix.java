// javac Driver.java LinearPrimitive.java GaussMatrix.java

public class GaussMatrix extends LinearPrimitive{
    int[] notZeroElmt = new int[100]; // contain index of first not zero number for each row
    int swapCount = 0;
    double divVal = 1d;
    int nparamater=-1;;
    GaussMatrix(double[][] inputMatrix, int nrow, int ncol){
        super(inputMatrix,ncol,nrow);
        this.transformToGauss();
    }
    void transformToGauss(){
        // initialize notZeroElmt array
        for(int i=0;i<super.nrow;i++){
            this.notZeroElmt[i]=0;
        }

        // begin OBE
        for(int i=0;i<super.nrow;i++){
            // update value of notZeroElmt array
            for(int j=0;j<super.nrow;j++){
                int k=0;
                while(k<ncol-1 && super.matrix[j][k]==0d){
                    k++;
                }
                if(super.matrix[j][k]==0d){
                    this.notZeroElmt[j]=k+1;
                }else{
                    this.notZeroElmt[j]=k;
                }
            }
            // sort matrix based on notZeroElmt array
            this.sortMatrixRow();

            // substract matrix row by another row
            int j=i+1;
            while(j<super.nrow && this.notZeroElmt[i]==this.notZeroElmt[j] && this.notZeroElmt[j]!=super.ncol){
                double koef=super.matrix[j][this.notZeroElmt[j]]/super.matrix[i][this.notZeroElmt[i]];
                super.rowOperation(i, j, koef, false);
                j++;
            }
            this.formattingZero();
        }

        // change element in main diagonal to 1
        for(int i=0;i<super.nrow;i++){
            if(this.notZeroElmt[i]==super.ncol)continue;
            double multiplier=1d/super.matrix[i][this.notZeroElmt[i]];
            super.multiplyRow(i, multiplier);
            this.divVal/=multiplier;
        }

        this.formattingZero();

    }
    public void sortMatrixRow(){
        // initalize indexRow array
        int[] indexRow = new int[100];
        for(int i=0;i<super.nrow;i++){
            indexRow[i]=i;
        }

        // sort indexrow array with bubble sort
        for(int i=0;i<super.nrow;i++){
            for(int j=0;j<super.nrow-i-1;j++){
                int firstRow=indexRow[j];
                int secRow=indexRow[j+1];
                if(this.notZeroElmt[firstRow]<=this.notZeroElmt[secRow])continue;
                indexRow[j]=secRow;
                indexRow[j+1]=firstRow;
                this.swapCount++;
            }
        }

        // make copy of matrix and notZeroElmt
        double[][] matrixClone =  new double[100][100];
        for(int i=0;i<super.nrow;i++){
            for(int j=0;j<super.ncol;j++){
                matrixClone[i][j]=super.matrix[i][j];
            }
        }
        int[] notZeroElmtClone = new int[100];
        for(int i=0;i<super.nrow;i++){
            notZeroElmtClone[i]=this.notZeroElmt[i];
        }

        // update matrix and notZeroElmt array based on sorted indexRow
        for(int i=0;i<super.nrow;i++){
            this.notZeroElmt[i]=notZeroElmtClone[indexRow[i]];
            for(int j=0;j<super.ncol;j++){
                super.matrix[i][j]=matrixClone[indexRow[i]][j];
            }
        }
    }
    int getSwapCount(){
        return this.swapCount;
    }

    double getDivVal(){
        return this.divVal;
    }

    double[][] getGaussMatrix(){
        return super.matrix;
    }

    void formattingZero(){
        for(int i=0;i<super.nrow;i++){
            for(int j=0;j<super.ncol;j++){
                if(super.matrix[i][j]<1e-14  && super.matrix[i][j]>-1e-14){
                    super.matrix[i][j]=0d;
                }
            }
        }
    }

    void displayMat(){
        for(int i=0;i<super.nrow;i++){
            for(int j=0;j<super.ncol;j++){
                System.out.print(super.matrix[i][j]+ " ");
            }
            System.out.println();
        }  
    }

    double[][] getResult(double[][] inputMat,int nrow,int ncol){
        // initialize result matrix
        double[][] result=new double[101][101];
        for(int i=0;i<101;i++){
            for(int j=0;j<101;j++){
                result[i][j]=0;
            }
        }
        // check if there is no solution
        boolean noSolution=false;
        boolean checkZero=true;;
        for(int i=nrow-1;i>=0;i--){
            if(!checkZero || noSolution)break;
            else if(inputMat[i][ncol-1]==0)continue;
            for(int j=ncol-2;j>=0;j--){
                if(inputMat[i][j]!=0){
                    checkZero=false;
                    break;
                }
                noSolution=true;
            }
        }
        if(noSolution){
            for(int i=0;i<101;i++){
                for(int j=0;j<101;j++){
                    result[i][j]=-1;
                }
            }
            return result;
        }

        // initialize list params
        int[] listparams=new int[100];
        int[] isparams=new int[100];
        for(int i=0;i<100;i++){
            isparams[i]=1;
            listparams[i]=-1;
        }

        // count non zero number for each row
        int[] nonzero=new int[100];
        for(int i=0;i<nrow;i++){
            nonzero[i]=0;
            for(int j=0;j<ncol-1;j++){
                if(inputMat[i][j]!=0)nonzero[i]++;
            }
        }

        // determine which variable to be a params
        int nparams=0;
        for(int i=nrow-1;i>=0;i--){
            int counter=0;
            for(int j=ncol-2;j>=0;j--){
                if(inputMat[i][j]!=0){
                    if(isparams[j]==1){
                        if(counter==nonzero[i]-1){
                            isparams[j]=2;
                        }else{
                            isparams[j]=0;
                            nparams++;
                        }
                    }
                    counter++;
                }
            }
        }
        int iter=0;
        for(int i=ncol-2;i>=0;i--){
            if(isparams[i]==0){
                listparams[i]=iter;
                iter++;
            }
        }

        // assign variable params to result
        boolean[] isChecked=new boolean[100];
        int nvariable=ncol-1;
        for(int i=0;i<nvariable;i++){
            if(isparams[i]==0){
                result[i][listparams[i]]=1;
                isChecked[i]=true;
            }
        }

        // assign variable non params to result
        for(int i=nrow-1;i>=0;i--){
            for(int j=ncol-2;j>=0;j--){
                if(isparams[j]==0 || inputMat[i][j]==0)continue;
                else if(isChecked[j])continue;
                result[j][nparams]+=inputMat[i][ncol-1];
                isChecked[j]=true;
                for(int k=j+1;k<ncol-1;k++){
                    if(inputMat[i][k]==0)continue;
                    for(int l=0;l<=nparams;l++){
                        result[j][l]-=inputMat[i][k]*result[k][l];
                    }
                }
                for(int k=0;k<=nparams;k++){
                    result[j][k]/=inputMat[i][j];
                }
            }
        }
        // formatting zero
        for(int i=0;i<101;i++){
            for(int j=0;j<101;j++){
                if(result[i][j]>-1e-14 && result[i][j]<1e-14){
                    result[i][j]=0;
                }
            }
        }
        this.nparamater=nparams;
        return result;
    }

    boolean isNoSolution(double[][] inputRes){
        for(int i=0;i<101;i++){
            for(int j=0;j<101;j++){
                if(inputRes[i][j]!=-1){
                    return false;
                }
            }
        }
        return true;
    }

    int numParams(){
        return this.nparamater;
    }
}
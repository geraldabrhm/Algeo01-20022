// javac Driver.java LinearPrimitive.java GaussMatrix.java

public class GaussMatrix extends LinearPrimitive{
    int[] notZeroElmt = new int[100]; // contain index of first not zero number for each row
    int swapCount = 0;
    double divVal = 1d;
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
}
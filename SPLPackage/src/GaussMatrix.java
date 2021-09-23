import java.util.ArrayList;

// javac Driver.java LinearPrimitive.java GaussMatrix.java

public class GaussMatrix extends LinearPrimitive{
    ArrayList<Integer>notZeroElmt;
    int swapCount;
    double divVal;
    GaussMatrix(ArrayList<ArrayList<Double>> inputMatrix, int nrow, int ncol){
        super(inputMatrix,ncol,nrow);
        this.notZeroElmt=new ArrayList<>(); // contain index of first not zero number for each row
        this.swapCount=0;
        this.divVal=1d;

        // initialize notZeroElmt array
        for(int i=0;i<nrow;i++){
            notZeroElmt.add(i,0);
        }

        // begin OBE
        for(int i=0;i<super.nrow;i++){
            // update value of notZeroElmt array
            for(int j=0;j<super.nrow;j++){
                int k=0;
                while(k<ncol-1 && super.doubleMatrix.get(j).get(k)==0d){
                    k++;
                }
                if(super.doubleMatrix.get(j).get(k)==0d){
                    this.notZeroElmt.set(j,k+1);
                }else{
                    this.notZeroElmt.set(j,k);
                }
            }

            // sort matrix based on notZeroElmt array
            this.sortMatrixRow();

            // substract matrix row by another row
            int j=i+1;
            while(j<super.nrow && this.notZeroElmt.get(i)==this.notZeroElmt.get(j) && this.notZeroElmt.get(j)!=super.ncol){
                double koef=super.doubleMatrix.get(j).get(this.notZeroElmt.get(j))/super.doubleMatrix.get(i).get(this.notZeroElmt.get(i));
                super.rowOperation(i, j, koef, false);
                j++;
            }
        }

        // change element in main diagonal to 1
        for(int i=0;i<super.nrow;i++){
            if(this.notZeroElmt.get(i)==super.ncol)continue;
            double multiplier=1d/super.doubleMatrix.get(i).get(this.notZeroElmt.get(i));
            super.multiplyRow(i, multiplier);
            this.divVal/=multiplier;
        }

        // remove -0 and change to 0
        this.formattingZero();
    }
    public void sortMatrixRow(){
        // initalize indexRow array
        ArrayList<Integer>indexRow = new ArrayList<>();
        for(int i=0;i<super.nrow;i++){
            indexRow.add(i,i);
        }

        // sort indexrow array with bubble sort
        for(int i=0;i<super.nrow;i++){
            for(int j=0;j<super.nrow-i-1;j++){
                int firstRow=indexRow.get(j);
                int secRow=indexRow.get(j+1);
                if(this.notZeroElmt.get(firstRow)<=this.notZeroElmt.get(secRow))continue;
                indexRow.set(j,secRow);
                indexRow.set(j+1,firstRow);
                this.swapCount++;
            }
        }

        // make copy of matrix and notZeroElmt
        ArrayList<ArrayList<Double>> matrixClone =  new ArrayList<>();
        for(int i=0;i<super.nrow;i++){
            ArrayList<Double> temp = new ArrayList<>();
            for(int j=0;j<super.ncol;j++){
                temp.add(super.doubleMatrix.get(i).get(j));
            }
            matrixClone.add(i,temp);
        }
        ArrayList<Integer> notZeroElmtClone = new ArrayList<>();
        for(int i=0;i<super.nrow;i++){
            notZeroElmtClone.add(i,this.notZeroElmt.get(i));
        }

        // update matrix and notZeroElmt array based on sorted indexRow
        for(int i=0;i<super.nrow;i++){
            this.notZeroElmt.set(i,notZeroElmtClone.get(indexRow.get(i)));
            for(int j=0;j<super.ncol;j++){
                super.doubleMatrix.get(i).set(j,matrixClone.get(indexRow.get(i)).get(j));
            }
        }
    }
    int getSwapCount(){
        return this.swapCount;
    }

    ArrayList<ArrayList<Double>> getGaussMatrix(){
        return super.doubleMatrix;
    }

    void formattingZero(){
        for(int i=0;i<super.nrow;i++){
            for(int j=0;j<super.ncol;j++){
                if(super.doubleMatrix.get(i).get(j)==0d){
                    super.doubleMatrix.get(i).set(j,0d);
                }
            }
        }
    }
}
// My english so bad wkwkwk, sekalian belajar yak wkwkw

// Need to discuss
// 1. Kind of matrix -> we need two or just one (which is double because it's more flexible i think)
// 2. Which one better -> ArrayList (dynamic array) or Double[][] (static array))
// 3. Still confused, should I add determinant for 2x2 array?

public class LinearPrimitive {
    public
        // Variable for matrix ncolumn and nrow
        int ncol, nrow;
    
    public
        // We don't know what kind of matrix are needed. In worst cases scenario, double is the best one
        double[][] matrix = new double[100][100];
        //Construcor
        LinearPrimitive(double[][]mat, int ncol, int nrow){
            this.nrow = nrow;
            this.ncol = ncol;
            for(int i=0;i<nrow;i++){
                for(int j=0;j<ncol;j++){
                    this.matrix[i][j]=mat[i][j];
                }
            }
        }

        //Swicth two Rowasd
        void switchRow(int rowfirst, int rowsecond){
            for(int i = 0; i < this.ncol; i ++){
                double temp = this.matrix[rowfirst][i];
                this.matrix[rowfirst][i]=this.matrix[rowsecond][i];
                this.matrix[rowsecond][i]=temp;
            }
        }
        
        //Swicth two column
        //Why we need this method?:v I forget it lol wkwkwk
        void switchColumn(int colfirst, int colsecond){
            for(int i = 0; i < this.nrow; i ++){
                double temp = this.matrix[i][colfirst];
                this.matrix[i][colfirst]=this.matrix[i][colsecond];
                this.matrix[i][colsecond]=temp;
            }
        }

        //Add or subtract (based on String opt) row with index rowfirst with row with index row times multiplier
        // If we want divide, give multiplier betwwen -1 and 1
        
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

        // Multiply a row with specified multiplier
        void multiplyRow(int row, double multiplier){
            for(int i = 0; i < this.ncol; i ++){
                this.matrix[row][i] *= multiplier;
            }
        }
        
        //Transpose Matrix
        void transposeMatrix(){
            for(int i=0;i<this.nrow;i++){
                for(int j=i+1;j<this.ncol;j++){
                    double temp=this.matrix[i][j];
                    this.matrix[i][j]=this.matrix[j][i];
                    this.matrix[j][i]=temp;
                }
            }
        }

        // Multiply matrix with a scalar
        void scalarMultiplier(double scalar){
            for(int i = 0; i < this.ncol; i ++){
                for(int j = 0; j < this.nrow; j ++){
                    this.matrix[i][j] *= scalar;
                }
            }
        }

        //Overloading for scalarMultiplier -> for case if we want to multiply another matrix besides this.doubleMatrix
        double[][] scalarMultiplier(double scalar, double mat[][], int nrow, int ncol){
            for(int i = 0; i < nrow; i ++){
                for(int j = 0; j < ncol ; j ++){
                    mat[i][j]*=scalar;
                }
            }
            return mat;
        }

        //Create cofactor -> For example, if we want to find cofactor 
        double[][] createCofactor(int rowDelete, int colDelete){
            double[][] cofactor = new double[100][100];

            int k=0,l=0;
            for(int i=0;i<this.nrow;i++){
                if(i==rowDelete)continue;
                for(int j=0;j<this.ncol;j++){
                    if(j==colDelete)continue;
                    cofactor[k][l]=this.matrix[i][j];
                    l++;
                }
                k++;
            }
            if(rowDelete + colDelete % 2 ==1){
                return scalarMultiplier(-1,cofactor,this.nrow-1,this.ncol-1);
            }else{
                return cofactor;
            }
        }
        // Case we want to fint determinant
        double determinantTwo(){
            return this.matrix[0][0]*this.matrix[1][1]-this.matrix[0][1]*this.matrix[1][0];
        }

        double determinantTwo(double mat[][]){
            return mat[0][0]*mat[1][1]-mat[0][1]*mat[1][0];
        }
}   

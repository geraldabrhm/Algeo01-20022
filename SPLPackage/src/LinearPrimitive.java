import java.util.ArrayList;

// My english so bad wkwkwk, sekalian belajar yak wkwkw

// Need to discuss
// 1. Kind of matrix -> we need two or just one (which is double because it's more flexible i think)
// 2. Which one better -> ArrayList (dynamic array) or Double[][] (static array))
// 3. Still confused, should I add determinant for 2x2 array?

public class LinearPrimitive {
    private
        // Variable for matrix ncolumn and nrow
        int ncol, nrow;
    
    public
        // We don't know what kind of matrix are needed. In worst cases scenario, double is the best one
        ArrayList<ArrayList<Double>> doubleMatrix;
        //Construcor
        LinearPrimitive(ArrayList<ArrayList<Double>> inputMatrix, int ncol, int nrow){
            this.doubleMatrix = inputMatrix;
            this.nrow = nrow;
            this.ncol = ncol;
        }

        //Swicth two Rowasd
        void switchRow(int rowfirst, int rowsecond){
            for(int i = 0; i < this.ncol; i ++){
                double temp = this.doubleMatrix.get(rowfirst).get(i);
                this.doubleMatrix.get(rowfirst).set(i, this.doubleMatrix.get(rowsecond).get(i));
                this.doubleMatrix.get(rowsecond).set(i, temp);
            }
        }
        
        //Swicth two column
        //Why we need this method?:v I forget it lol wkwkwk
        void switchColumn(int colfirst, int colsecond){
            for(int i = 0; i < this.nrow; i ++){
                double temp = this.doubleMatrix.get(i).get(colfirst);
                this.doubleMatrix.get(i).set(colfirst, this.doubleMatrix.get(colsecond).get(i));
                this.doubleMatrix.get(i).set(colsecond, temp);
            }
        }

        //Add or subtract (based on String opt) row with index rowfirst with row with index row times multiplier
        // If we want divide, give multiplier betwwen -1 and 1
        
        //Give argument true to parameter plus, if we need to do addition
        void rowOperation(int rowfirst, int rowsecond, double multiplier, Boolean plus){
            for(int i = 0; i < this.ncol; i ++){
                double newVal;
                if(plus){
                    newVal = this.doubleMatrix.get(rowfirst).get(i) + (this.doubleMatrix.get(rowsecond).get(i) * multiplier);
                }else{
                    newVal = this.doubleMatrix.get(rowfirst).get(i) - (this.doubleMatrix.get(rowsecond).get(i) * multiplier);
                }
                this.doubleMatrix.get(rowsecond).set(i, newVal);
            }
        }

        // Multiply a row with specified multiplier
        void multiplyRow(int row, double multiplier){
            for(int i = 0; i < this.ncol; i ++){
                double newVal =this.doubleMatrix.get(row).get(i) * multiplier;
                this.doubleMatrix.get(row).set(i, newVal);
            }
        }
        
        //Transpose Matrix
        void transposeMatrix(){
            ArrayList<ArrayList<Double>> temp = new ArrayList<ArrayList<Double>>();

            for(int i = 0; i < this.ncol; i++){
                ArrayList<Double>in_temp = new ArrayList<Double>();
                for(int j = 0; j < this.nrow; j++){
                    in_temp.add(this.doubleMatrix.get(i).get(j));
                }
                temp.add(in_temp);
            }

            this.doubleMatrix = temp;
        }

        // Multiply matrix with a scalar
        void scalarMultiplier(double scalar){
            for(int i = 0; i < this.ncol; i ++){
                for(int j = 0; j < this.nrow; j ++){
                    double newVal = this.doubleMatrix.get(i).get(j) * scalar;
                    this.doubleMatrix.get(i).set(j, newVal);
                }
            }
        }

        //Overloading for scalarMultiplier -> for case if we want to multiply another matrix besides this.doubleMatrix
        void scalarMultiplier(double scalar, ArrayList<ArrayList<Double>>inMatrix){
            for(int i = 0; i < inMatrix.size(); i ++){
                for(int j = 0; j < inMatrix.get(0).size() ; j ++){
                    double newVal = inMatrix.get(i).get(j) * scalar;
                    inMatrix.get(i).set(j, newVal);
                }
            }
        }

        //Create cofactor -> For example, if we want to find cofactor 
        ArrayList<ArrayList<Double>> createCofactor(int rowDelete, int colDelete){
            ArrayList<ArrayList<Double>> cofactor = new ArrayList<ArrayList<Double>>();

            for(int i = 0; i < this.ncol; i ++){
                ArrayList<Double> in_cofactor = new ArrayList<Double>();
                if(i != rowDelete){
                    for(int j = 0; j < this.nrow; j++){
                        if(j != colDelete){
                            in_cofactor.add(this.doubleMatrix.get(i).get(j));
                        }
                        else{
                            continue;
                        }
                    }
                    cofactor.add(in_cofactor);
                }
                else{
                    continue;
                }
            }
            
            // Multiply with -1 
            if(rowDelete + colDelete % 2 == 1 ){
                scalarMultiplier(-1, cofactor);
            }

            return cofactor;
        }

        // Case we want to fint determinant of this.doubleMatrix
        double determinantTwo(){
            double firstVal = this.doubleMatrix.get(0).get(0) * this.doubleMatrix.get(1).get(1);
            double secondVal = this.doubleMatrix.get(0).get(1) * this.doubleMatrix.get(1).get(0);
            
            return firstVal - secondVal;
        }

        double determinantTwo(ArrayList<ArrayList<Double>>inMatrix){
            double firstVal = inMatrix.get(0).get(0) * inMatrix.get(1).get(1);
            double secondVal = inMatrix.get(0).get(1) * inMatrix.get(1).get(0);
            
            return firstVal - secondVal;
        }
}   

public class inversMatriks {

    public static double[][] minor(double[][] inputMatriks) {
        // Matriks terdefinisi dan merupakan matriks persegi n * n, n > 2, menghasilkan matriks minor
        double[][] subMatriks = new double[inputMatriks.length - 1][inputMatriks.length - 1];
        double[][] result = new double[inputMatriks.length][inputMatriks.length];
        int a, b, i, j, counter0 = 0, counter1 = 0;
        for(a = 0; a < inputMatriks.length; a++) {
            for(b = 0; b < inputMatriks.length; b++) {
                boolean condition = true;
                for(i = 0; i < (inputMatriks.length); i++) {
                    for(j = 0; j < (inputMatriks.length); j++) {
                        if(i != a && j != b && condition) {
                            subMatriks[counter0][counter1] = inputMatriks[i][j];
                            counter1++;
                            if(counter1 == (inputMatriks.length - 1)) {
                                counter0++;
                                if(counter0 != (inputMatriks.length - 1)) {
                                    counter1 = 0;
                                }
                                else {
                                    counter0 = 0;
                                    counter1 = 0;
                                    condition = false;
                                }
                            }
                        }
                    }
                }
                result[a][b] = DetbyKofaktor(subMatriks);
            }
        }
        return result;
    }

    public static double[][] kofaktor(double[][] inputnya) {
        // Matriks terdefinisi dan merupakan matriks persegi n * n, n > 2, menghasilkan matriks kofaktor
        double[][] m = new double[inputnya.length][inputnya.length];
        double[][] result = new double[inputnya.length][inputnya.length];
        m = minor(inputnya);
        int i, j;
        for(i = 0; i < m.length; i++) {
            for(j = 0; j < m.length; j++) {
                if ((i + j) % 2 == 0) {
                    result[i][j] = m[i][j];
                }
                else { //mod 2 = 1 (odd number)
                    result[i][j] = -(m[i][j]);
                }
            }
        }
        return result;
    }

    public static double[][] adjoin (double[][] inputan) {
        // Matriks terdefinisi dan merupakan matriks persegi n * n, n > 2, menghasilkan matriks adjoin
        return transposeLocal(kofaktor(inputan));
    }

    public static double[][] transposeLocal (double[][] inputan_m) {
        // Matriks terdefinisi dan merupakan matriks persegi n * n, n >= 2, menghasilkan matriks transpose
        int i, j;
        double[][] result = new double[inputan_m.length][inputan_m.length];
        for(i = 0; i < inputan_m.length; i++) {
            for(j = 0; j < inputan_m.length; j++) {
                result[i][j] = inputan_m[j][i];
            }
        }
        return result;
    }

    public static  double DetbyKofaktor(double[][] inputan) {
        if(inputan.length > 2) {
            double[][] subMatriksX = new double[inputan.length - 1][inputan.length - 1];
            double detResult = 0;
            int h, i, j, k, counter1 = 0, counter2 = 0;
            for(h = 0; h < 1; h++) { // using first row
                for(i = 0; i < inputan[0].length; i++) {
                    boolean condition = true;
                    for(j = 0; j < inputan[0].length; j++) {
                        for(k = 0; k < inputan[0].length; k++) {
                            if(h != j && i != k && condition) {
                                subMatriksX[counter1][counter2] = inputan[j][k];
                                counter2++;
                                if(counter2 == (inputan[0].length - 1)) {
                                    counter1++;
                                    if(counter1 != (inputan[0].length - 1)) {
                                        counter2 = 0;
                                    }
                                    else {
                                        counter1 = 0;
                                        counter2 = 0;
                                        condition = false;
                                    }
                                }
                            }
                        }
                    }
                    if(i % 2 == 0){
                        if ((inputan.length - 1) == 2) {
                            detResult += inputan[0][i]*((subMatriksX[0][0]*subMatriksX[1][1])- (subMatriksX[0][1]*subMatriksX[1][0]));
                        }
                        else {
                            detResult += inputan[0][i]*DetbyKofaktor(subMatriksX);
                        }
                    }
                    else {
                        if ((inputan.length - 1) == 2) {
                            detResult -= inputan[0][i]*((subMatriksX[0][0]*subMatriksX[1][1])- (subMatriksX[0][1]*subMatriksX[1][0]));
                        }
                        else {
                            detResult -= inputan[0][i]*DetbyKofaktor(subMatriksX);
                        }
                    }
                }
            }
            return detResult;
        }
        else {
            double determi2Res = (inputan[0][0]*inputan[1][1]) - (inputan[0][1]*inputan[1][0]);
            return determi2Res;
        }
    }

    public static double[][] inversebyKofaktor(double[][] inputan) {
        if (inputan.length > 3) {
            // Matriks terdefinisi dan merupakan matriks persegi n * n, n > 2, menghasilkan matriks invers
            double[][] result = new double[inputan.length][inputan.length];
            double faktorPengali = 1 / DetbyKofaktor(inputan);
            double[][] matrixAdjoin = new double[inputan.length][inputan.length];
            matrixAdjoin = adjoin(inputan);
            int i, j;
            for(i = 0; i < inputan.length; i++) {
                for(j = 0; j < inputan.length; j++) {
                    result[i][j] = faktorPengali * matrixAdjoin[i][j];
                }
            }
            return result;
        }
        else { // untuk matriks 2 * 2 and 3 * 3
            if(inputan.length == 2) {
                int i, j;
                double det2x2 = 1/((inputan[0][0]*inputan[1][1]) - (inputan[0][1]*inputan[1][0]));
                double[][] adjoin2 = {{inputan[1][1], -inputan[0][1]}, {-inputan[1][0], inputan[0][0]}};
                double[][] result2 = new double[2][2];
                for(i = 0; i < 2; i++) {
                    for(j = 0; j < 2; j++) {
                        result2[i][j] = det2x2 * adjoin2[i][j];
                    }
                }
                return result2;
            }
            else { // untuk matriks 3 * 3 --basis
                double[][] temp3 = new double[inputan.length][inputan.length];
                double faktorKali = 1/(DetbyKofaktor(inputan));
                int i, j;
                temp3[0][0] = (inputan[1][1]*inputan[2][2]) - (inputan[1][2]*inputan[2][1]);
                temp3[0][1] = -((inputan[1][0]*inputan[2][2]) - (inputan[1][2]*inputan[2][0]));
                temp3[0][2] = (inputan[1][0]*inputan[2][1]) - (inputan[1][1]*inputan[2][0]);
                temp3[1][0] = -((inputan[0][1]*inputan[2][2]) - (inputan[0][2]*inputan[2][1]));
                temp3[1][1] = (inputan[0][0]*inputan[2][2]) - (inputan[0][2]*inputan[2][0]);
                temp3[1][2] = -((inputan[0][0]*inputan[2][1]) - (inputan[0][1]*inputan[2][0]));
                temp3[2][0] = (inputan[0][1]*inputan[1][2]) - (inputan[1][1]*inputan[0][2]);
                temp3[2][1] = -((inputan[0][0]*inputan[1][2]) - (inputan[1][0]*inputan[0][2]));
                temp3[2][2] = (inputan[0][0]*inputan[1][1]) - (inputan[1][0]*inputan[0][1]);
                temp3 = transposeLocal(temp3);
                for(i = 0; i < temp3.length; i++) {
                    for(j = 0; j < temp3.length; j++) {
                        temp3[i][j] *= faktorKali;
                    }
                }
                return temp3;
            }
        }
    }
    /*
    public static double[][] inversebyGaussJordan (double[][]) {
        
    }
    */
    public static double[][] inversebyGaussJordan(double[][] inputan){
        int colreal = (inputan.length) * 2;
        int aa, bb;
        int cc = 0;
        int dd = inputan.length;
        double[][] inputanPlusIdentitas = new double[inputan.length][colreal];
        double[][] resultInvGaussJordan = new double[inputan.length][inputan.length];

        for(aa = 0; aa < inputan.length; aa++){
            for(bb = 0; bb < colreal; bb++){
                if(bb < inputan.length) {
                    inputanPlusIdentitas[aa][bb] = inputan[aa][bb];
                }
                else{
                    if(aa == cc && bb == dd) {
                        inputanPlusIdentitas[aa][bb] = 1;
                        cc++;
                        dd++;
                    }
                    else {
                        inputanPlusIdentitas[aa][bb] = 0;
                    }
                }
            }
        }

        GaussJordanMatrix GAU = new GaussJordanMatrix(inputanPlusIdentitas, inputan.length, colreal);

        double[][] resultUpdate = GAU.getJordanMatrix();

        for(aa = 0; aa < inputan.length; aa++) {
            for(bb = 0; bb < inputan.length; bb++) {
                resultInvGaussJordan[aa][bb] = resultUpdate[aa][bb + inputan.length];
            }
        }
        return resultInvGaussJordan;
    }

    /* Testing on 3 x 3 matrix on InversbyGaussJordan and InversbyKofaktor 
    public static void main(String[] args) {
        double[][] matCheck = {{1, 4, -3}, {2, 2, 6}, {9, -4, 2}};
        //System.out.println(DetbyKofaktor(matCheck));
        
        double[][] result, result2;
        int i, j;
        result = inversebyKofaktor(matCheck);
        for(i = 0; i < matCheck.length; i++) {
            for(j = 0; j < matCheck[i].length; j++) {
                System.out.print(result[i][j] + "\t");
            }
            System.out.printf("\n");
        }
        System.out.printf("\n");
        result2 = inversebyGaussJordan(matCheck);
        for(i = 0; i < matCheck.length; i++) {
            for(j = 0; j < matCheck[i].length; j++) {
                System.out.print(result2[i][j] + "\t");
            }
            System.out.printf("\n");
        } 
    }
    */
    
    /* Testing Last
    public static void main(String[] args) {
        double[][] inpa = {{1, 2, 3}, {4, 5, 5}, {1, 4, 9}};
        double hasil = DetbyKofaktor(inpa);
        System.out.print(hasil);
    }
    */
}
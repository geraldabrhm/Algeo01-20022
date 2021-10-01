public class regresiLinear {
    // Regresi linear untuk n | n > 2 variabel dependen (>= 3 variabel bebas dan 1 variabel terikat)
    static double[] multiReg(double[][] varDep, double[] varIkat) {
        // length kiri dan kiri harus sama
        int a, b, i, j, inc = 0, k, l;
        double tempAccumul, tempAccumul2;
        double[][] matKiriResult = new double[varDep[0].length + 1][varDep[0].length + 1];
        double[] resultKanan = new double[varDep[0].length + 1];
        double[][] inversKiri;
        double[] resultReg;


        for(i = 0; i < matKiriResult.length; i++) {
            for(j = 0; j < matKiriResult.length; j++) {
                tempAccumul = 0;
                if (i == 0 && j == 0) {
                    matKiriResult[i][j] = varDep.length;
                }
                else if((i == 0 || j == 0) && (i != 0 || j != 0)) {
                    for(a = 0; a < varDep.length; a++) {
                        tempAccumul += varDep[a][inc];
                    }
                    inc++;
                    if(inc == varDep[0].length) {
                        inc = 0;
                    }
                    matKiriResult[i][j] = tempAccumul;
                }
                else {
                    for(b = 0; b < varDep.length; b++) {
                        tempAccumul += ((varDep[b][i - 1]) * (varDep[b][j - 1])); 
                    }
                    matKiriResult[i][j] = tempAccumul;
                }
            }
        }

        for(k = 0; k < resultKanan.length; k++) {
            tempAccumul2 = 0;
            if(k == 0) {
                for(l = 0; l < varIkat.length; l++) {
                    tempAccumul2 += varIkat[l];
                }
                resultKanan[k] = tempAccumul2;
            }
            else {
                for(l = 0; l < varIkat.length; l++) {
                    tempAccumul2 += varDep[l][k - 1] * varIkat[l];
                }
                resultKanan[k] = tempAccumul2;
            }
        }
        
        inversKiri = inversMatriks.inversebyKofaktor(matKiriResult);
        for(i = 0; i < inversKiri.length; i++) {
            for(j = 0; j < inversKiri.length; j++) {
            }
        }
        resultReg = multiplication(inversKiri, resultKanan);
        return resultReg;
    }

    static double[] multiplication(double[][] kiri, double[] kanan) {
        int j, k;
        double temp;
        double[] result = new double[kiri.length];
        
        for(j = 0; j < kiri.length; j++) {
            temp = 0;
            for(k = 0; k < kanan.length; k++) {
                temp += kiri[j][k] * kanan[k];
            }
            result[j] = temp;
        }
        return result;
    }

    /* Testing 1
    public static void main(String args[]) {
        double[][] DataKiri = {{40, 25}, {45, 20}, {38,30}, {50, 30},{50, 30}, {48, 28},{55, 30}, {53, 34}, {55, 36}, {58, 32}, {40, 34}, {55, 38}, {48, 28}, {45, 30}, {55, 36}, {60, 34}, {60, 38}, {60, 42}, {65, 38}, {50, 34}, {58, 38}};
        double[] DataKanan = {1, 2, 1, 3, 2, 3, 3, 4, 4, 3, 5, 3, 3, 2, 4, 5, 5, 5, 4, 3};
    }
    */
    /* Testing 2 
    public static void main(String args[]) {
        double[][] DataKiri = {{2, 1, -1, -1}, {3, -1, 1, 2}, {6, -1, -1, 1}, {2, 2, -1, -3}, {1, 0, 1, 3}};
        double[] DataKanan = {2, 6, 4, 3, 9};
        double[] resultRegg;
        resultRegg = multiReg(DataKiri, DataKanan);
        int i;

        for(i = 0; i < resultRegg.length; i++) {
            System.out.println("Hasil "+ (i + 1) + " " + resultRegg[i]);
        }
    }
    */
    
    /* Testing 3
    public static void main(String[] args) {
        double[][] DataKiri = {{40, 25, 14}, {45, 20, 2}, {38, 30, 3}, {50, 30, 3}, {48, 28, 3}};
        double[] DataKanan = {1, 2, 1, 3, 2};
        multiReg(DataKiri, DataKanan);        
    }
    */
}
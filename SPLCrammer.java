public class SPLCrammer {

    public static double[] Crammer(double[][] leftMatriks, double[] rightMatriks) {
        // Crammer hanya bisa pada SPL yang leftMatriksnya Matriks Persegi
        double[][] leftMatriksModified = new double[leftMatriks.length][leftMatriks.length];
        double[][] temp = new double[leftMatriks.length][leftMatriks.length];
        temp = leftMatriks;
        double[] result = new double[rightMatriks.length];

        int i, j, k = 1, l, m;
        double detBase = inversMatriks.DetbyKofaktor(leftMatriks);
        for(i = 0; i < leftMatriks.length; i++) {
            for(l = 0; l < leftMatriks.length; l++) {
                for(m = 0; m < leftMatriks.length; m++) {
                    leftMatriksModified[l][m] = temp[l][m];
                }
            }
            for(j = 0; j < leftMatriks.length; j++) {
                leftMatriksModified[j][i] = rightMatriks[j];
            }

            result[i] = inversMatriks.DetbyKofaktor(leftMatriksModified) / detBase;
            k++;
        }
        return result;
    }
    /* Testing on 4 x 4 matriks 
    public static void main(String[] args) {
        double[][] testcasekiri = {{2, 1, -1, -1}, {3, -1, 1, 2}, {6, -1, -1, 1}, {2, 2, -1, -3}};
        double[] testcasekanan = {2, 6, 4, 0};
        double[] ha = Crammer(testcasekiri, testcasekanan);
        int i;
        for(i = 0; i < ha.length; i++) {
            System.out.print(ha[i] + " ");
        }
    }
    */
}